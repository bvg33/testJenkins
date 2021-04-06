CREATE SCHEMA IF NOT EXISTS epam;
drop table epam.tag if exists;
drop table epam.gift_certificate if exists;
drop table epam.certificate_tag if exists;
/*----------------------tag-----------------------------*/
CREATE TABLE epam.tag
(
    id       INTEGER PRIMARY KEY,
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
    id               INTEGER PRIMARY KEY,
    name             varchar(45),
    description      varchar(45),
    price            int,
    duration         int,
    create_date      datetime,
    last_update_date datetime
);