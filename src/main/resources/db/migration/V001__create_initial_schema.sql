create table cars
(
    id int primary key,
    model varchar(50) not null,
    manufacturer varchar(50) not null,
    "year" varchar(10) not null,
    fipe_value float
);


create table drivers
(
    id bigint primary key,
    document varchar,
    birthdate date
);

create table cars_drivers
(
    id bigint primary key,
    car_id bigint not null,
    driver_id bigint not null,
    is_main_driver boolean not null,
    foreign key (car_id)
        references cars (id),
    foreign key (driver_id)
        references drivers (id)
);

create table customer
(
    id bigint primary key,
    name varchar(50) not null,
    driver_id bigint not null,
    foreign key (driver_id)
        references drivers (id)
);

create table insurance
(
    id bigint primary key,
    customer_id bigint not null,
    car_id bigint not null,
    creation_dt datetime not null,
    updated_dt datetime not null,
    is_active boolean not null,
    foreign key (customer_id)
        references customer (id),
    foreign key (car_id)
        references cars (id)
);

create table claims
(
    id bigint primary key,
    car_id bigint not null,
    driver_id bigint not null,
    event_date date,
    foreign key (car_id)
        references cars (id),
    foreign key (driver_id)
        references drivers (id)
);
