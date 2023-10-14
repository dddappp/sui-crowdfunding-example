module sui_crowdfunding_example::project_withdraw_logic {
    use sui::balance::Balance;
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::vault_withdrawn;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify<T>(
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::VaultWithdrawn {
        project::new_vault_withdrawn(
            project,
            0,//todo amount
        )
    }

    public(friend) fun mutate<T>(
        vault_withdrawn: &project::VaultWithdrawn,
        project: &mut project::Project<T>,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<T> {
        let amount = vault_withdrawn::amount(vault_withdrawn);
        let id = project::id(project);
        // ...
        //
        sui::balance::zero()//todo
    }

}
