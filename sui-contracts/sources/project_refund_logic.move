module sui_crowdfunding_example::project_refund_logic {
    use sui::balance;
    use sui::balance::Balance;
    use sui::clock;
    use sui::clock::Clock;
    use sui::coin;
    use sui::coin::balance;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_refunded;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    const NOT_STARTED: u64 = 0;
    const EPROJECT_NOT_STARTED: u64 = 182;
    const EPROJECT_DEADLINE_NOT_REACHED: u64 = 184;
    const EPROJECT_TARGET_REACHED: u64 = 185;

    public(friend) fun verify<T>(
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::DonationRefunded {
        assert!(project::deadline(project) != NOT_STARTED, EPROJECT_NOT_STARTED);
        assert!(clock::timestamp_ms(clock) > project::deadline(project), EPROJECT_DEADLINE_NOT_REACHED);
        assert!(balance::value(project::borrow_vault(project)) < project::target(project), EPROJECT_TARGET_REACHED);

        project::new_donation_refunded(
            project,
            balance::value(project::borrow_vault(project)),
        )
    }

    public(friend) fun mutate<T>(
        donation_refunded: &project::DonationRefunded,
        project: &mut project::Project<T>,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<T> {
        let id = project::id(project);
        // ...
        //
        sui::balance::zero()//todo
    }

}
