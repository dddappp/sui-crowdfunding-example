module sui_crowdfunding_example::platform_add_project_logic {
    use std::vector;
    use sui::object::ID;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::platform;
    use sui_crowdfunding_example::project_added_to_platform;

    friend sui_crowdfunding_example::platform_aggregate;

    public(friend) fun verify(
        project_id: ID,
        platform: &platform::Platform,
        _ctx: &TxContext,
    ): platform::ProjectAddedToPlatform {
        platform::new_project_added_to_platform(
            platform,
            project_id,
        )
    }

    public(friend) fun mutate(
        project_added_to_platform: &platform::ProjectAddedToPlatform,
        platform: &mut platform::Platform,
        ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        let _ = ctx;
        let project_id = project_added_to_platform::project_id(project_added_to_platform);
        let projects = platform::projects(platform);
        if (!vector::contains(&projects, &project_id)) {
            vector::push_back(&mut projects, project_id);
            platform::set_projects(platform, projects);
        };
    }
}
