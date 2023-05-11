create table if not exists MASTER
(
    MASTER_ID identity primary key,
    FULL_NAME varchar(50) not null,
    POST      varchar(50) not null,
    PHONE     varchar(50) not null
);

create table if not exists SERVICE
(
    ID        identity primary key,
    TITLE     varchar(50) not null,
    PRICE     real        not null,
    DURATION  real        not null,
    MASTER_ID bigint      not null,
    CONSTRAINT MASTER_ID_FK FOREIGN KEY (MASTER_ID) REFERENCES MASTER (MASTER_ID)
);