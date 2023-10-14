module sui_crowdfunding_example::project_donate_logic {
    use sui::balance;
    use sui::balance::Balance;
    use sui::clock;
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_received;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    const NOT_STARTED: u64 = 0;
    const EPROJECT_NOT_STARTED: u64 = 182;
    const EPROJECT_DEADLINE_REACHED: u64 = 183;

    public(friend) fun verify<T>(
        amount: &Balance<T>,
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::DonationReceived {
        assert!(project::deadline(project) != NOT_STARTED, EPROJECT_NOT_STARTED);
        assert!(clock::timestamp_ms(clock) < project::deadline(project), EPROJECT_DEADLINE_REACHED);

        project::new_donation_received(
            project,
            tx_context::sender(ctx),
            balance::value(amount),
        )
    }

    public(friend) fun mutate<T>(
        donation_received: &project::DonationReceived,
        amount: Balance<T>,
        project: &mut project::Project<T>,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        let donator = donation_received::donator(donation_received);
        let donation_amount = balance::value(&amount);
        if (project::donations_contains(project, donator)) {
            let donation = project::borrow_mut_donation(project, donator);
            let sum = donation::amount(donation) + donation_amount;
            donation::set_amount(donation, sum);
        } else {
            project::add_donation(project, donation::new_donation(donator, donation_amount));
        };

        let vault = project::borrow_mut_vault(project);
        sui::balance::join(vault, amount);
    }
}
