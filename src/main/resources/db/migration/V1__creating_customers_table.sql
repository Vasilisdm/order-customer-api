CREATE TABLE customers
(
    id         bigint primary key auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255) unique,
    created_at timestamp with time zone default now()
)