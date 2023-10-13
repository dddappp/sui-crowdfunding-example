module sui_crowdfunding_example::project_start_logic {
    use sui::clock::Clock;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::donation::{Self, Donation};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_started;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify(
        clock: &Clock,
        project: &project::Project,
        ctx: &TxContext,
    ): project::ProjectStarted {
        project::new_project_started(
            project,
        )
    }

    public(friend) fun mutate(
        project_started: &project::ProjectStarted,
        project: project::Project,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): project::Project {
        let id = project::id(&project);
        // ...
        //
        project
    }

}
