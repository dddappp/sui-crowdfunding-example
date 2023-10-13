module sui_crowdfunding_example::project_withdraw_logic {
    use sui::balance::Balance;
    use sui::clock::Clock;
    use sui::sui::SUI;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::vault_withdrawn;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify(
        amount: u64,
        clock: &Clock,
        project: &project::Project,
        ctx: &TxContext,
    ): project::VaultWithdrawn {
        project::new_vault_withdrawn(
            project,
            0,//todo amount
        )
    }

    public(friend) fun mutate(
        vault_withdrawn: &project::VaultWithdrawn,
        project: &mut project::Project,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<SUI> {
        let amount = vault_withdrawn::amount(vault_withdrawn);
        let id = project::id(project);
        // ...
        //
        sui::balance::zero()//todo
    }

}
