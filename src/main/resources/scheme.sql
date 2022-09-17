create table EXHIBIT
(
    id          int primary key auto_increment,
    name        varchar(50)  not null,
    description varchar(255) not null,
    year        YEAR         not null,
    author      varchar(100),
    hall_number int
);

create table EXHIBITION
(
    id         int primary key auto_increment,
    name       varchar(50)  not null,
    start_date TIMESTAMP    not null,
    end_date   TIMESTAMP    not null,
    country     varchar(50)  not null,
    city       varchar(50)  not null,
    place      varchar(100) not null,
    organizer  varchar(50)
);

create table STORAGE
(
    id      int primary key auto_increment,
    type    varchar(25) not null,
    manager varchar(50) not null
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

create table EXHIBITED_EXHIBIT
(
    exhibit_id    int,
    exhibition_id int,
    constraint fk_exhibited_id foreign key (exhibit_id) references EXHIBIT (id),
    constraint fk_exhibition_id foreign key (exhibition_id) references EXHIBITION (id),
    constraint pk_exhibited_exhibit primary key (exhibit_id, exhibition_id)
);