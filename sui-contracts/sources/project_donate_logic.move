module sui_crowdfunding_example::project_donate_logic {
    use sui::balance::Balance;
    use sui::sui::SUI;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::donation_received;
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify(
        amount: &Balance<SUI>,
        project: &project::Project,
        ctx: &TxContext,
    ): project::DonationReceived {
        project::new_donation_received(
            project,
            0,//todo amount,
        )
    }

    public(friend) fun mutate(
        donation_received: &project::DonationReceived,
        amount: Balance<SUI>,
        project: &mut project::Project,
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
