// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_crowdfunding_example::project_added_to_platform {

    use sui::object::{Self, ID};
    use sui_crowdfunding_example::platform::{Self, ProjectAddedToPlatform};

    public fun id(project_added_to_platform: &ProjectAddedToPlatform): object::ID {
        platform::project_added_to_platform_id(project_added_to_platform)
    }

    public fun project_id(project_added_to_platform: &ProjectAddedToPlatform): ID {
        platform::project_added_to_platform_project_id(project_added_to_platform)
    }

}
