-- Hibernate: drop table if exists autEnv cascade
-- Hibernate: drop table if exists job_tested_case cascade
-- Hibernate: drop table if exists job_result cascade
-- Hibernate: drop table if exists tested_case cascade
-- Hibernate: drop table if exists test_result cascade
create table aut_env (
    id bigint not null,
    aut varchar(255) not null,
    environment varchar(255) not null,
    primary key (id)
)

create table aut_env_tested_case (
    aut_env_id bigint not null,
    tested_case_id bigint not null
)

create table job_result (
    id bigint not null,
    browser varchar(255),
    end_time timestamp(6) not null,
    autEnv varchar(255),
    host varchar(255),
    is_parallel boolean,
    name varchar(255) not null,
    start_time timestamp(6) not null,
    viewport varchar(255),
    job_id bigint,
    primary key (id)
)

create table tested_case (
    id bigint not null,
    create_date timestamp(6) not null,
    description varchar(255),
    is_data_driven boolean,
    name varchar(255) not null,
    primary key (id)
)

create table test_result (
    id bigint not null,
    instance varchar(255),
    is_data_driven boolean,
    length bigint not null,
    method varchar(255) not null,
    parameters varchar(255),
    result varchar(255) not null,
    throwable varchar(255),
    tr_id integer not null,
    was_retried boolean,
    job_result_id bigint,
    tested_case_id bigint,
    primary key (id)
)
