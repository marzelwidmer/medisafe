drop table IF EXISTS album cascade;
-- create table IF NOT EXISTS album
-- (
--     id     varchar(255) not null,
--     user_id varchar(255) not null,
--     title  varchar(255) not null,
--     version int,
--     primary key (id)
-- );
create table IF NOT EXISTS album
(
    id       varchar(255) not null,
    user_id  int          not null,
    album_id int          not null,
    version  int,
    primary key (id)
);
