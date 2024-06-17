module sui_crowdfunding_example::project_start_logic {
    use sui::clock;
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_started;

    friend sui_crowdfunding_example::project_aggregate;

    const ENOT_PROJECT_OWNER: u64 = 186;

    //const FIFTEEN_DAYS_IN_MS: u64 = 15 * 24 * 60 * 60 * 1000; // <- In a production environment, use this
    const FIFTEEN_DAYS_IN_MS: u64 = 1 * 60 * 1000; // <- Only 1 minutes for testing!!!
    //const FIFTEEN_DAYS_IN_MS: u64 = 5 * 60 * 1000; // <- Only 5 minutes for testing!!!
    //const FIFTEEN_DAYS_IN_MS: u64 = 60 * 1000; // <- Only 60 seconds for testing!!!

    public(friend) fun verify<T>(
        clock: &Clock,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::ProjectStarted {
        assert!(tx_context::sender(ctx) == project::owner(project), ENOT_PROJECT_OWNER);

        let deadline = clock::timestamp_ms(clock) + FIFTEEN_DAYS_IN_MS;
        project::new_project_started(
            project,
            deadline,
        )
    }

    public(friend) fun mutate<T>(
        project_started: &project::ProjectStarted,
        project: &mut project::Project<T>,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        let deadline = project_started::deadline(project_started);
        project::set_deadline(project, deadline);
    }
}
