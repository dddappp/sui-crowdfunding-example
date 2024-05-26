// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_crowdfunding_example::project {
    use std::option;
    use std::string::String;
    use sui::balance::Balance;
    use sui::event;
    use sui::object::{Self, ID, UID};
    use sui::table;
    use sui::transfer;
    use sui::tx_context::TxContext;
    use sui_crowdfunding_example::donation::{Self, Donation};
    friend sui_crowdfunding_example::project_create_logic;
    friend sui_crowdfunding_example::project_update_logic;
    friend sui_crowdfunding_example::project_start_logic;
    friend sui_crowdfunding_example::project_donate_logic;
    friend sui_crowdfunding_example::project_withdraw_logic;
    friend sui_crowdfunding_example::project_refund_logic;
    friend sui_crowdfunding_example::project_aggregate;

    const EIdAlreadyExists: u64 = 101;
    #[allow(unused_const)]
    const EDataTooLong: u64 = 102;
    #[allow(unused_const)]
    const EInappropriateVersion: u64 = 103;
    const EEmptyObjectID: u64 = 107;
    const EIdNotFound: u64 = 111;

    struct Project<phantom T> has key {
        id: UID,
        version: u64,
        owner: address,
        title: String,
        description: String,
        target: u64,
        deadline: u64,
        image: String,
        vault: Balance<T>,
        donations: table::Table<address, Donation>,
    }

    public fun id<T>(project: &Project<T>): object::ID {
        object::uid_to_inner(&project.id)
    }

    public fun version<T>(project: &Project<T>): u64 {
        project.version
    }

    public fun owner<T>(project: &Project<T>): address {
        project.owner
    }

    public(friend) fun set_owner<T>(project: &mut Project<T>, owner: address) {
        project.owner = owner;
    }

    public fun title<T>(project: &Project<T>): String {
        project.title
    }

    public(friend) fun set_title<T>(project: &mut Project<T>, title: String) {
        assert!(std::string::length(&title) <= 200, EDataTooLong);
        project.title = title;
    }

    public fun description<T>(project: &Project<T>): String {
        project.description
    }

    public(friend) fun set_description<T>(project: &mut Project<T>, description: String) {
        assert!(std::string::length(&description) <= 2000, EDataTooLong);
        project.description = description;
    }

    public fun target<T>(project: &Project<T>): u64 {
        project.target
    }

    public(friend) fun set_target<T>(project: &mut Project<T>, target: u64) {
        project.target = target;
    }

    public fun deadline<T>(project: &Project<T>): u64 {
        project.deadline
    }

    public(friend) fun set_deadline<T>(project: &mut Project<T>, deadline: u64) {
        project.deadline = deadline;
    }

    public fun image<T>(project: &Project<T>): String {
        project.image
    }

    public(friend) fun set_image<T>(project: &mut Project<T>, image: String) {
        assert!(std::string::length(&image) <= 200, EDataTooLong);
        project.image = image;
    }

    public fun borrow_vault<T>(project: &Project<T>): &Balance<T> {
        &project.vault
    }

    public(friend) fun borrow_mut_vault<T>(project: &mut Project<T>): &mut Balance<T> {
        &mut project.vault
    }

    public(friend) fun add_donation<T>(project: &mut Project<T>, donation: Donation) {
        let key = donation::donator(&donation);
        assert!(!table::contains(&project.donations, key), EIdAlreadyExists);
        table::add(&mut project.donations, key, donation);
    }

    public(friend) fun remove_donation<T>(project: &mut Project<T>, donator: address) {
        assert!(table::contains(&project.donations, donator), EIdNotFound);
        let donation = table::remove(&mut project.donations, donator);
        donation::drop_donation(donation);
    }

    public(friend) fun borrow_mut_donation<T>(project: &mut Project<T>, donator: address): &mut Donation {
        table::borrow_mut(&mut project.donations, donator)
    }

    public fun borrow_donation<T>(project: &Project<T>, donator: address): &Donation {
        table::borrow(&project.donations, donator)
    }

    public fun donations_contains<T>(project: &Project<T>, donator: address): bool {
        table::contains(&project.donations, donator)
    }

    public fun donations_length<T>(project: &Project<T>): u64 {
        table::length(&project.donations)
    }

    public(friend) fun new_project<T>(
        owner: address,
        title: String,
        description: String,
        target: u64,
        deadline: u64,
        image: String,
        ctx: &mut TxContext,
    ): Project<T> {
        assert!(std::string::length(&title) <= 200, EDataTooLong);
        assert!(std::string::length(&description) <= 2000, EDataTooLong);
        assert!(std::string::length(&image) <= 200, EDataTooLong);
        Project {
            id: object::new(ctx),
            version: 0,
            owner,
            title,
            description,
            target,
            deadline,
            image,
            vault: sui::balance::zero(),
            donations: table::new<address, Donation>(ctx),
        }
    }

    struct ProjectCreated has copy, drop {
        id: option::Option<object::ID>,
        platform_id: ID,
        owner: address,
        title: String,
        description: String,
        target: u64,
        image: String,
        token_type: String,
    }

    public fun project_created_id(project_created: &ProjectCreated): option::Option<object::ID> {
        project_created.id
    }

    public(friend) fun set_project_created_id(project_created: &mut ProjectCreated, id: object::ID) {
        project_created.id = option::some(id);
    }

    public fun project_created_platform_id(project_created: &ProjectCreated): ID {
        project_created.platform_id
    }

    public fun project_created_owner(project_created: &ProjectCreated): address {
        project_created.owner
    }

    public fun project_created_title(project_created: &ProjectCreated): String {
        project_created.title
    }

    public fun project_created_description(project_created: &ProjectCreated): String {
        project_created.description
    }

    public fun project_created_target(project_created: &ProjectCreated): u64 {
        project_created.target
    }

    public fun project_created_image(project_created: &ProjectCreated): String {
        project_created.image
    }

    public fun project_created_token_type(project_created: &ProjectCreated): String {
        project_created.token_type
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_project_created<T>(
        platform_id: ID,
        owner: address,
        title: String,
        description: String,
        target: u64,
        image: String,
        token_type: String,
    ): ProjectCreated {
        ProjectCreated {
            id: option::none(),
            platform_id,
            owner,
            title,
            description,
            target,
            image,
            token_type,
        }
    }

    struct ProjectUpdated has copy, drop {
        id: object::ID,
        version: u64,
        title: String,
        description: String,
        target: u64,
        image: String,
    }

    public fun project_updated_id(project_updated: &ProjectUpdated): object::ID {
        project_updated.id
    }

    public fun project_updated_title(project_updated: &ProjectUpdated): String {
        project_updated.title
    }

    public fun project_updated_description(project_updated: &ProjectUpdated): String {
        project_updated.description
    }

    public fun project_updated_target(project_updated: &ProjectUpdated): u64 {
        project_updated.target
    }

    public fun project_updated_image(project_updated: &ProjectUpdated): String {
        project_updated.image
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_project_updated<T>(
        project: &Project<T>,
        title: String,
        description: String,
        target: u64,
        image: String,
    ): ProjectUpdated {
        ProjectUpdated {
            id: id(project),
            version: version(project),
            title,
            description,
            target,
            image,
        }
    }

    struct ProjectStarted has copy, drop {
        id: object::ID,
        version: u64,
        deadline: u64,
    }

    public fun project_started_id(project_started: &ProjectStarted): object::ID {
        project_started.id
    }

    public fun project_started_deadline(project_started: &ProjectStarted): u64 {
        project_started.deadline
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_project_started<T>(
        project: &Project<T>,
        deadline: u64,
    ): ProjectStarted {
        ProjectStarted {
            id: id(project),
            version: version(project),
            deadline,
        }
    }

    struct DonationReceived has copy, drop {
        id: object::ID,
        version: u64,
        donator: address,
        amount: u64,
    }

    public fun donation_received_id(donation_received: &DonationReceived): object::ID {
        donation_received.id
    }

    public fun donation_received_donator(donation_received: &DonationReceived): address {
        donation_received.donator
    }

    public fun donation_received_amount(donation_received: &DonationReceived): u64 {
        donation_received.amount
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_donation_received<T>(
        project: &Project<T>,
        donator: address,
        amount: u64,
    ): DonationReceived {
        DonationReceived {
            id: id(project),
            version: version(project),
            donator,
            amount,
        }
    }

    struct VaultWithdrawn has copy, drop {
        id: object::ID,
        version: u64,
        amount: u64,
    }

    public fun vault_withdrawn_id(vault_withdrawn: &VaultWithdrawn): object::ID {
        vault_withdrawn.id
    }

    public fun vault_withdrawn_amount(vault_withdrawn: &VaultWithdrawn): u64 {
        vault_withdrawn.amount
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_vault_withdrawn<T>(
        project: &Project<T>,
        amount: u64,
    ): VaultWithdrawn {
        VaultWithdrawn {
            id: id(project),
            version: version(project),
            amount,
        }
    }

    struct DonationRefunded has copy, drop {
        id: object::ID,
        version: u64,
        donator: address,
        amount: u64,
    }

    public fun donation_refunded_id(donation_refunded: &DonationRefunded): object::ID {
        donation_refunded.id
    }

    public fun donation_refunded_donator(donation_refunded: &DonationRefunded): address {
        donation_refunded.donator
    }

    public fun donation_refunded_amount(donation_refunded: &DonationRefunded): u64 {
        donation_refunded.amount
    }

    #[allow(unused_type_parameter)]
    public(friend) fun new_donation_refunded<T>(
        project: &Project<T>,
        donator: address,
        amount: u64,
    ): DonationRefunded {
        DonationRefunded {
            id: id(project),
            version: version(project),
            donator,
            amount,
        }
    }


    #[lint_allow(share_owned)]
    public(friend) fun share_object<T>(project: Project<T>) {
        assert!(project.version == 0, EInappropriateVersion);
        transfer::share_object(project);
    }

    public(friend) fun update_object_version<T>(project: &mut Project<T>) {
        project.version = project.version + 1;
        //assert!(project.version != 0, EInappropriateVersion);
    }

    public(friend) fun drop_project<T>(project: Project<T>) {
        let Project {
            id,
            version: _version,
            owner: _owner,
            title: _title,
            description: _description,
            target: _target,
            deadline: _deadline,
            image: _image,
            vault,
            donations,
        } = project;
        object::delete(id);
        sui::balance::destroy_zero(vault);
        table::destroy_empty(donations);
    }

    public(friend) fun emit_project_created(project_created: ProjectCreated) {
        assert!(std::option::is_some(&project_created.id), EEmptyObjectID);
        event::emit(project_created);
    }

    public(friend) fun emit_project_updated(project_updated: ProjectUpdated) {
        event::emit(project_updated);
    }

    public(friend) fun emit_project_started(project_started: ProjectStarted) {
        event::emit(project_started);
    }

    public(friend) fun emit_donation_received(donation_received: DonationReceived) {
        event::emit(donation_received);
    }

    public(friend) fun emit_vault_withdrawn(vault_withdrawn: VaultWithdrawn) {
        event::emit(vault_withdrawn);
    }

    public(friend) fun emit_donation_refunded(donation_refunded: DonationRefunded) {
        event::emit(donation_refunded);
    }

}
