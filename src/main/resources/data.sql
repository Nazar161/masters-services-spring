delete from MASTER;
delete from SERVICE;

insert into MASTER (FULL_NAME, POST, PHONE) values ( 'Казаков Авраам Юрьевич', 'Сантехник', '89067821112' );

insert into MASTER (FULL_NAME, POST, PHONE) values ( 'Васильев Степан Гордеевич', 'Мастер по ремонту стиральных машин', '89280984590' );

insert into SERVICE (TITLE, PRICE, DURATION, MASTER_ID) values ( 'Установка ванны', 3000, 2, 1 );

insert into SERVICE (TITLE, PRICE, DURATION, MASTER_ID) values ( 'Замена смесителя', 800, 0.5, 1 );

insert into SERVICE (TITLE, PRICE, DURATION, MASTER_ID) values ( 'Ремонт подшипников', 1500, 1.5, 2 );

insert into SERVICE (TITLE, PRICE, DURATION, MASTER_ID) values ( 'Ремонт блока управлени', 2400, 1.5, 2 );