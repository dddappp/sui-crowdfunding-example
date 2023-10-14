module sui_crowdfunding_example::project_create_logic {
    use std::string::String;
    use sui::tx_context;
    use sui::tx_context::TxContext;
    use sui_crowdfunding_example::platform::{Self, Platform};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_created;

    friend sui_crowdfunding_example::project_aggregate;

    public(friend) fun verify<T>(
        platform: &mut Platform,
        title: String,
        description: String,
        target: u64,
        deadline: u64,
        image: String,
        ctx: &mut TxContext,
    ): project::ProjectCreated {
        let _ = ctx;
        project::new_project_created(
            platform::id(platform),
            tx_context::sender(ctx),
            title,
            description,
            target,
            deadline,
            image,
        )
    }

    public(friend) fun mutate<T>(
        project_created: &project::ProjectCreated,
        platform: &mut Platform,
        ctx: &mut TxContext,
    ): project::Project<T> {
        let platform_id = project_created::platform_id(project_created);
        let owner = project_created::owner(project_created);
        let title = project_created::title(project_created);
        let description = project_created::description(project_created);
        let target = project_created::target(project_created);
        let deadline = project_created::deadline(project_created);
        let image = project_created::image(project_created);
        project::new_project(
            owner,
            title,
            description,
            target,
            deadline,
            image,
            ctx,
        )
    }

}
