CREATE SCHEMA IF NOT EXISTS epam;
drop table epam.tag if exists;
drop table epam.gift_certificate if exists;
drop table epam.certificate_tag if exists;
drop table epam.users if exists;
drop table epam.users_certificates if exists;
/*----------------------tag-----------------------------*/
CREATE TABLE epam.tag
(
    tag_id   INTEGER PRIMARY KEY,
    tag_name VARCHAR(30)
);
/*---------------------certificate_tag------------------*/
create TABLE epam.certificate_tag
(
    certificate_id INTEGER,
    tag_id         INTEGER
);
/*----------------------certificate---------------------*/
create TABLE epam.gift_certificate
(
    certificate_id   INTEGER PRIMARY KEY,
    name             varchar(45),
    description      varchar(45),
    price            int,
    duration         int,
    create_date      datetime,
    last_update_date datetime
);
/*----------------------USERS------------------------------*/
create TABLE epam.users
(
    user_id             INTEGER PRIMARY KEY,
    password            varchar(60),
    role                varchar(45),
    nickname            varchar(45),
    money               int,
    overage_order_price int
);
/*------------------------USERS_CERTIFICATES----------------*/
create TABLE epam.users_certificates
(
    user_id           INTEGER,
    certificate_id    INTEGER,
    certificate_price INTEGER,
    buy_date          datetime,
    id                integer primary key
);