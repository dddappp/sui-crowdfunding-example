set @fkConstraintName = (SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS WHERE
            CONSTRAINT_SCHEMA = DATABASE() AND
            TABLE_NAME        = 'donation' AND
            CONSTRAINT_TYPE   = 'FOREIGN KEY');
set @sqlVar = if(@fkConstraintName is not null, 
			concat('ALTER TABLE donation drop foreign key ', @fkConstraintName),
            'select 1');
prepare stmt from @sqlVar;
execute stmt;
deallocate prepare stmt;
set @fkConstraintName = (SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS WHERE
            CONSTRAINT_SCHEMA = DATABASE() AND
            TABLE_NAME        = 'platform_projects' AND
            CONSTRAINT_TYPE   = 'FOREIGN KEY');
set @sqlVar = if(@fkConstraintName is not null, 
			concat('ALTER TABLE platform_projects drop foreign key ', @fkConstraintName),
            'select 1');
prepare stmt from @sqlVar;
execute stmt;
deallocate prepare stmt;
set @fkConstraintName = (SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS WHERE
            CONSTRAINT_SCHEMA = DATABASE() AND
            TABLE_NAME        = 'donation' AND
            CONSTRAINT_TYPE   = 'FOREIGN KEY');
set @sqlVar = if(@fkConstraintName is not null, 
			concat('ALTER TABLE donation drop foreign key ', @fkConstraintName),
            'select 1');
prepare stmt from @sqlVar;
execute stmt;
deallocate prepare stmt;
set @fkConstraintName = (SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS WHERE
            CONSTRAINT_SCHEMA = DATABASE() AND
            TABLE_NAME        = 'platform_projects' AND
            CONSTRAINT_TYPE   = 'FOREIGN KEY');
set @sqlVar = if(@fkConstraintName is not null, 
			concat('ALTER TABLE platform_projects drop foreign key ', @fkConstraintName),
            'select 1');
prepare stmt from @sqlVar;
execute stmt;
deallocate prepare stmt;
drop table if exists donation;
drop table if exists donation_event;
drop table if exists hibernate_hello;
drop table if exists hibernate_sequences;
drop table if exists move_object_id_generator_object;
drop table if exists platform;
drop table if exists platform_event;
drop table if exists platform_projects;
drop table if exists project;
drop table if exists project_event;
drop table if exists sui_package;
create table donation (project_donation_id_project_id VARCHAR(66) not null, project_donation_id_donator VARCHAR(66) not null, off_chain_version bigint not null, amount DECIMAL(20,0), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, created_at datetime, updated_at datetime, primary key (project_donation_id_project_id, project_donation_id_donator)) engine=InnoDB;
create table donation_event (project_donation_id_project_id VARCHAR(66) not null, project_donation_id_donator VARCHAR(66) not null, version DECIMAL(20,0) not null, event_type varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), primary key (project_donation_id_project_id, project_donation_id_donator, version)) engine=InnoDB;
create table hibernate_hello (id bigint not null, message varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequences (sequence_name varchar(255) not null, next_val bigint, primary key (sequence_name)) engine=InnoDB;
insert into hibernate_sequences(sequence_name, next_val) values ('default',9999);
create table move_object_id_generator_object (name varchar(255) not null, created_at datetime, created_by varchar(255), object_id varchar(255), object_type varchar(255), updated_at datetime, updated_by varchar(255), primary key (name)) engine=InnoDB;
create table platform (id VARCHAR(66) not null, off_chain_version bigint not null, name varchar(200), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, version DECIMAL(20,0), created_at datetime, updated_at datetime, primary key (id)) engine=InnoDB;
create table platform_event (id VARCHAR(66) not null, version DECIMAL(20,0) not null, event_type varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), sui_timestamp bigint, sui_tx_digest varchar(50), sui_event_seq DECIMAL(20,0), sui_package_id VARCHAR(66), sui_transaction_module varchar(100), sui_sender VARCHAR(66), sui_type varchar(200), status CHAR(1), command_type varchar(50), dynamic_properties_lob VARCHAR(2000), primary key (id, version)) engine=InnoDB;
create table platform_projects (id varchar(255) not null, projects_index integer not null, projects_item varchar(255) not null, primary key (id, projects_index)) engine=InnoDB;
create table project (id VARCHAR(66) not null, off_chain_version bigint not null, owner VARCHAR(66), title varchar(200), description varchar(2000), target DECIMAL(20,0), deadline DECIMAL(20,0), image varchar(200), vault decimal(19,2), version DECIMAL(20,0), created_by varchar(255), updated_by varchar(255), active bit, deleted bit, type_argument_t varchar(255), created_at datetime, updated_at datetime, primary key (id)) engine=InnoDB;
create table project_event (id VARCHAR(66) not null, version DECIMAL(20,0) not null, event_type varchar(255) not null, created_by varchar(255), created_at datetime, command_id varchar(255), sui_timestamp bigint, sui_tx_digest varchar(50), sui_event_seq DECIMAL(20,0), sui_package_id VARCHAR(66), sui_transaction_module varchar(100), sui_sender VARCHAR(66), sui_type varchar(200), status CHAR(1), command_type varchar(50), dynamic_properties_lob VARCHAR(2000), primary key (id, version)) engine=InnoDB;
create table sui_package (name varchar(255) not null, created_at datetime, created_by varchar(255), object_id varchar(255), publisher varchar(255), updated_at datetime, updated_by varchar(255), version bigint, primary key (name)) engine=InnoDB;
alter table donation add constraint FKe2pr1kbqv9nvb3kdr929wi1d8 foreign key (project_donation_id_project_id) references project (id);
alter table platform_projects add constraint FKn9j1ul7fl6uoq9una17phyplq foreign key (id) references platform (id);
