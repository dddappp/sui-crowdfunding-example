alter table donation drop foreign key FKe2pr1kbqv9nvb3kdr929wi1d8;
alter table platform_projects drop foreign key FKn9j1ul7fl6uoq9una17phyplq;
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