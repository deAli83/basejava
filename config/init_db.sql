create table resume
(
    uuid      text not null
        constraint resume_pk
            primary key,
    full_name text not null
);

alter table resume
    owner to postgres;

create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    resume_uuid text not null references resume (uuid) on delete cascade,
    type        text     not null,
    value       text     not null

);

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

create table section
(
    id          serial
        constraint section_pk
            primary key,
    resume_uuid text not null references resume (uuid) on delete cascade,
    type        text     not null,
    value       text     not null

);

create unique index section_uuid_type_index
    on contact (resume_uuid, type);

alter table contact
    owner to postgres;