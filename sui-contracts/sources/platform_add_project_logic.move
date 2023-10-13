module sui_crowdfunding_example::platform_add_project_logic {
    use sui::object::ID;
    use sui::tx_context::{Self, TxContext};
    use sui_crowdfunding_example::platform;
    use sui_crowdfunding_example::project_added_to_platform;

    friend sui_crowdfunding_example::platform_aggregate;

    public(friend) fun verify(
        project_id: ID,
        platform: &platform::Platform,
        ctx: &TxContext,
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
        let project_id = project_added_to_platform::project_id(project_added_to_platform);
        // ...
        //
    }

}
