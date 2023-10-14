module sui_crowdfunding_example::project_donate_logic {
    use sui::balance::Balance;
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_received;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify<T>(
        amount: &Balance<T>,
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::DonationReceived {
        project::new_donation_received(
            project,
            0,//todo amount,
        )
    }

    public(friend) fun mutate<T>(
        donation_received: &project::DonationReceived,
        amount: Balance<T>,
        project: &mut project::Project<T>,
        ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        //todo let amount = donation_received::amount(donation_received);
        //let id = project::id(&project);
        // ...
        //
        let vault = project::borrow_mut_vault(project);
        sui::balance::join(vault, amount);
    }

}
