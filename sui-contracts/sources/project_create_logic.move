#[allow(unused_mut_parameter)]
module sui_crowdfunding_example::project_create_logic {
    use std::string;
    use std::string::String;
    use std::type_name;
    use sui::tx_context;
    use sui::tx_context::TxContext;
    use sui_crowdfunding_example::platform_aggregate;
    use sui_crowdfunding_example::platform::{Self, Platform};
    use sui_crowdfunding_example::project;
    use sui_crowdfunding_example::project_created;

    friend sui_crowdfunding_example::project_aggregate;

    const NOT_STARTED: u64 = 0;

    public(friend) fun verify<T>(
        platform: &mut Platform,
        title: String,
        description: String,
        target: u64,
        image: String,
        ctx: &mut TxContext,
    ): project::ProjectCreated {
        let token_type = type_name::into_string(type_name::get<T>());
        project::new_project_created<T>(
            platform::id(platform),
            tx_context::sender(ctx),
            title,
            description,
            target,
            image,
            string::from_ascii(token_type),
        )
    }

    public(friend) fun mutate<T>(
        project_created: &project::ProjectCreated,
        platform: &mut Platform,
        ctx: &mut TxContext,
    ): project::Project<T> {
        //let platform_id = project_created::platform_id(project_created);
        let owner = project_created::owner(project_created);
        let title = project_created::title(project_created);
        let description = project_created::description(project_created);
        let target = project_created::target(project_created);
        let deadline = NOT_STARTED;
        let image = project_created::image(project_created);
        let project = project::new_project<T>(
            owner,
            title,
            description,
            target,
            deadline,
            image,
            ctx,
        );
        platform_aggregate::add_project(
            platform,
            project::id(&project),
            ctx,
        );
        project
    }

}
