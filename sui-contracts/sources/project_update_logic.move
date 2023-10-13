module sui_crowdfunding_example::project_update_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_updated;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify(
        owner: address,
        title: String,
        description: String,
        target: u64,
        deadline: u64,
        image: String,
        project: &project::Project,
        ctx: &TxContext,
    ): project::ProjectUpdated {
        let _ = ctx;
        project::new_project_updated(
            project,
            owner,
            title,
            description,
            target,
            deadline,
            image,
        )
    }

    public(friend) fun mutate(
        project_updated: &project::ProjectUpdated,
        project: project::Project,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): project::Project {
        let owner = project_updated::owner(project_updated);
        let title = project_updated::title(project_updated);
        let description = project_updated::description(project_updated);
        let target = project_updated::target(project_updated);
        let deadline = project_updated::deadline(project_updated);
        let image = project_updated::image(project_updated);
        let id = project::id(&project);
        let _ = ctx;
        let _ = id;
        project::set_owner(&mut project, owner);
        project::set_title(&mut project, title);
        project::set_description(&mut project, description);
        project::set_target(&mut project, target);
        project::set_deadline(&mut project, deadline);
        project::set_image(&mut project, image);
        project
    }

}
