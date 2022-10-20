create table EXHIBIT
(
    id            int primary key auto_increment,
    name          varchar(100) not null,
    description   varchar(255) not null,
    year          int          not null,
    author        varchar(100),
    department_id int,
    constraint fk_department_id foreign key (department_id) references DEPARTMENT (id)
);

create table EXHIBITION
(
    id         int primary key auto_increment,
    name       varchar(50)  not null,
    start_date TIMESTAMP    not null,
    end_date   TIMESTAMP    not null,
    country    varchar(50)  not null,
    city       varchar(50)  not null,
    place      varchar(100) not null,
    organizer  varchar(50)
);

create table DEPARTMENT
(
    id   int primary key auto_increment,
    name varchar(50) not null
);

create table STORAGE
(
    id            int primary key auto_increment,
    department_id int         not null,
    manager       varchar(50) not null,
    constraint fk_st_deparment_id foreign key (department_id) references DEPARTMENT (id)
);

create table STORED_EXHIBIT
(
    exhibit_id int,
    storage_id int,
    start_date DATE not null,
    end_date   DATE,
    constraint fk_exhibit_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint fk_storage_id foreign key (storage_id) references STORAGE (id),
    constraint pk_stored_exhibit_id primary key (exhibit_id, storage_id)
);

create table EXHIBITION_PART
(
    exhibit_id    int,
    exhibition_id int,
    constraint fk_exhibited_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint fk_exhibition_id foreign key (exhibition_id) references EXHIBITION (id),
    constraint pk_exhibited_exhibit primary key (exhibit_id, exhibition_id)
);

create table HALL
(
    id            int primary key auto_increment,
    number        int not null,
    department_id int not null,
    constraint fk_ex_h_depatment_id foreign key (department_id) references DEPARTMENT (id)
);

create table EXHIBIT_IN_HALL
(
    hall_id        int       not null,
    exhibit_id     int       not null,
    transport_date TIMESTAMP not null,
    constraint fk_hall_id foreign key (hall_id) references HALL (id),
    constraint fk_h_exhibit_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint pk_hall_exhibit primary key (hall_id, exhibit_id)
)