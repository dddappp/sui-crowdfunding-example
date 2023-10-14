module sui_crowdfunding_example::project_refund_logic {
    use sui::balance;
    use sui::balance::Balance;
    use sui::clock::Clock;
    use sui::coin;
    use sui::coin::balance;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_refunded;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify<T>(
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::DonationRefunded {
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
