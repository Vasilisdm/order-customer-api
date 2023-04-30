CREATE TABLE orders
(
    id         bigint primary key auto_increment,
    created_at timestamp with time zone default now()
);


create table items
(
    id       bigint primary key auto_increment,
    order_id bigint       not null,
    name     varchar(255) not null,
    quantity int          not null,
    price    double       not null,
    constraint fk_order foreign key (order_id) references orders (id)
);