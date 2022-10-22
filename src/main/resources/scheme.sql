create table EXHIBIT
(
    id          int primary key auto_increment,
    name        varchar(100)  not null,
    description varchar(255) not null,
    year        YEAR         not null,
    author      varchar(100),
    type        int          not null,
    constraint fk_type foreign key (type) references DEPARTMENT (id)
);

create table EXHIBITION
(
    id         int primary key auto_increment,
    name       varchar(100)  not null,
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
    id      int primary key auto_increment,
    type    int         not null,
    manager varchar(50) not null,
    constraint fk_type_storage foreign key (type) references DEPARTMENT (id)
);

create table HALL
(
    id     int primary key auto_increment,
    number int not null,
    type   int not null,
    constraint fk_type_hall foreign key (type) references DEPARTMENT (id)
);

create table STORED_EXHIBIT
(
    id         int primary key auto_increment,
    exhibit_id int,
    storage_id int,
    start_date DATE not null,
    end_date   DATE,
    constraint fk_exhibit_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint fk_storage_id foreign key (storage_id) references STORAGE (id)
);

create table EXHIBITION_PART
(
    id            int primary key auto_increment,
    exhibit_id    int,
    exhibition_id int,
    constraint fk_exhibited_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint fk_exhibition_id foreign key (exhibition_id) references EXHIBITION (id)
);

create table EXHIBIT_HALL
(
    id          int primary key auto_increment,
    hall_id     int,
    exhibit_id  int,
    moving_date timestamp not null,
    constraint fk_hall_id foreign key (hall_id) references HALL (id),
    constraint fk_exhibit_item_id foreign key (exhibit_id) references EXHIBIT (id)
);