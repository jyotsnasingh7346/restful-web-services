/*This data will not be intered when we have connected with MySQL DB, This only works for H2 DB*/

insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Jyotsna');

insert into user_details(id, birth_date, name)
values(10002, current_date(), 'Amar');

insert into user_details(id, birth_date, name)
values(10003, current_date(), 'Singh');

insert into post(id, description, user_id)
values(20001, 'I want to learn AWS', 10001);

insert into post(id, description, user_id)
values(20002, 'I want to learn Azure', 10001);

insert into post(id, description, user_id)
values(20003, 'I want to learn DevOps', 10002);

insert into post(id, description, user_id)
values(20004, 'I want to learn Jenkins', 10002);
