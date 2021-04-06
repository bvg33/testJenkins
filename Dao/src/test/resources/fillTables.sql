/*----------------------tag-----------------------------*/
INSERT INTO epam.tag
VALUES (1, 'tag1');
INSERT INTO epam.tag
VALUES (2, 'tag2');
INSERT INTO epam.tag
VALUES (3, 'tag3');
/*----------------------certificate---------------------*/
INSERT INTO epam.gift_certificate
VALUES ('1', 'sad', 'dsf', '4', '5', '2021-11-10 00:00:00', '2021-11-10 00:00:00');
INSERT INTO epam.gift_certificate
VALUES ('2', 'name1', 'description', '5', '5', '2021-04-03 21:07:00', '2021-04-04 20:46:00');
INSERT INTO epam.gift_certificate
VALUES ('3', 'sdfkl', '1', '5', '4', '2021-04-05 15:03:00', '2021-04-05 15:03:00');
/*---------------------certificate_tag------------------*/
insert into epam.certificate_tag
values (1, 1);
insert into epam.certificate_tag
values (1, 2);
insert into epam.certificate_tag
values (2, 2);
insert into epam.certificate_tag
values (3, 3);
