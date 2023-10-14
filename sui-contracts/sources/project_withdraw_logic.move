module sui_crowdfunding_example::project_withdraw_logic {
    use sui::balance;
    use sui::balance::Balance;
    use sui::clock;
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::project;

    friend sui_crowdfunding_example::project_aggregate;

    const NOT_STARTED: u64 = 0;
    const EPROJECT_NOT_STARTED: u64 = 182;
    const EPROJECT_DEADLINE_NOT_REACHED: u64 = 184;
    const EPROJECT_TARGET_NOT_REACHED: u64 = 185;
    const ENOT_PROJECT_OWNER: u64 = 186;

    public(friend) fun verify<T>(
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::VaultWithdrawn {
        assert!(project::deadline(project) != NOT_STARTED, EPROJECT_NOT_STARTED);
        assert!(clock::timestamp_ms(clock) > project::deadline(project), EPROJECT_DEADLINE_NOT_REACHED);
        assert!(balance::value(project::borrow_vault(project)) >= project::target(project), EPROJECT_TARGET_NOT_REACHED);
        assert!(tx_context::sender(ctx) == project::owner(project), ENOT_PROJECT_OWNER);

        project::new_vault_withdrawn(
            project,
            balance::value(project::borrow_vault(project)), // withdraw all
        )
    }

    public(friend) fun mutate<T>(
        _vault_withdrawn: &project::VaultWithdrawn,
        project: &mut project::Project<T>,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<T> {
        let balance = project::borrow_mut_vault(project);
        let amount = balance::value(balance); // withdraw all
        sui::balance::split(balance, amount)
    }

}
