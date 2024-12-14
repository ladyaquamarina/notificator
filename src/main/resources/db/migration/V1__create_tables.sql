create table if not exists "user" (
    id varchar(36) not null primary key,
    email varchar(200),
    phone varchar(16),
    push_token varchar(255)
);

create table if not exists notification (
    id varchar(36) not null primary key,
    user_id varchar(36) not null,
    message varchar(200) not null,
    target_type varchar(20) not null,
    target_value varchar(200) not null,
    status varchar(20) not null,
    created_at timestamp default now(),
    constraint fk_user_id foreign key (user_id) references "user" (id)
)