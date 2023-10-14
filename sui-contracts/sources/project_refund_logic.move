module sui_crowdfunding_example::project_refund_logic {
    use sui::balance;
    use sui::balance::Balance;
    use sui::clock::Clock;
    use sui::coin;
    use sui::coin::balance;
    use sui::sui::SUI;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_refunded;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify(
        clock: &Clock,
        project: &project::Project,
        ctx: &TxContext,
    ): project::DonationRefunded {
        project::new_donation_refunded(
            project,
            balance::value(project::borrow_vault(project)),
        )
    }

    public(friend) fun mutate(
        donation_refunded: &project::DonationRefunded,
        project: &mut project::Project,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<SUI> {
        let id = project::id(project);
        // ...
        //
        sui::balance::zero()//todo
    }

}
