// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_crowdfunding_example::project_started {

    use sui::object;
    use sui_crowdfunding_example::project::{Self, ProjectStarted};

    public fun id(project_started: &ProjectStarted): object::ID {
        project::project_started_id(project_started)
    }

}
