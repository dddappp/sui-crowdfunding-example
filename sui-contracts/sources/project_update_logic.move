module sui_crowdfunding_example::project_update_logic {
    use std::string::String;
    use sui::tx_context;
    use sui::tx_context::TxContext;
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_updated;

    friend sui_crowdfunding_example::project_aggregate;

    const NOT_STARTED: u64 = 0;
    const EPROJECT_ALREADY_STARTED: u64 = 181;
    const ENOT_PROJECT_OWNER: u64 = 186;

    public(friend) fun verify<T>(
        title: String,
        description: String,
        target: u64,
        image: String,
        project: &project::Project<T>,
        ctx: &TxContext,
    ): project::ProjectUpdated {
        assert!(project::deadline(project) == NOT_STARTED, EPROJECT_ALREADY_STARTED);
        assert!(tx_context::sender(ctx) == project::owner(project), ENOT_PROJECT_OWNER);

        project::new_project_updated(
            project,
            title,
            description,
            target,
            image,
        )
    }

    public(friend) fun mutate<T>(
        project_updated: &project::ProjectUpdated,
        project: &mut project::Project<T>,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        let title = project_updated::title(project_updated);
        let description = project_updated::description(project_updated);
        let target = project_updated::target(project_updated);
        let image = project_updated::image(project_updated);

        project::set_title(project, title);
        project::set_description(project, description);
        project::set_target(project, target);
        project::set_image(project, image);
    }
}
