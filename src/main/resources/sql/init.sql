
create table user
(
    id        varchar(32)                          not null
        primary key,
    email     varchar(256)                         not null,
    password  varchar(512)                         not null,
    name      varchar(32)                          not null,
    age       int                                  null,
    createdAt datetime default current_timestamp() not null
);

create table orders
(
    id        int auto_increment
        primary key,
    userId    varchar(32)                          not null,
    productId int                                  not null,
    count     int                                  not null,
    address   varchar(512)                         not null,
    orderedAt datetime default current_timestamp() not null,
    createdAt datetime default current_timestamp() not null
);

create table products
(
    id            int auto_increment
        primary key,
    name          varchar(128)                         not null,
    stockQuantity int                                  not null,
    price         int                                  not null,
    createdAt     datetime default current_timestamp() not null
);

