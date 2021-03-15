create table project0.account_type(
	id Integer primary key,
	type text
);


insert into account_type values (0, 'UNREGISTERED');
insert into account_type values (1, 'USER');
insert into account_type values (2, 'CUSTOMER');
insert into account_type values (3, 'EMPLOYEE');
insert into account_type values (4, 'ADMIN');


create table app_user(
	id serial primary key,
	username text not null,
	password text not null,
	type int references account_type(id)
);

insert into app_user (username, password, type)
values( 'admin', passwordhash('password'), 4 );

insert into app_user (username, password, type)
values( 'employee1', passwordhash('epass1'), 3 );

insert into app_user (username, password, type)
values( 'customer1', passwordhash('cpass1'), 2 );

insert into app_user (username, password, type)
values( 'customer2', 'cpass2', 2 );

select * from app_user au ;
truncate app_user cascade;

create table car_status(
	id integer primary key,
	type text
);

insert into car_status values(0, 'ON_LOT');
insert into car_status values(1,'OFF_LOT');
insert into car_status values(2, 'CUSTOMER_OWNED');
insert into car_status values (3, 'PENDING');


create table car(
	id serial primary key,
	status int references car_status(id),
	model text,
	model_year int
);

insert into car(status, model, model_year) values(0, 'Wrangler', 2021);
insert into car(status, model, model_year) values(0, 'Grand Cherokee', 2021);
insert into car(status, model, model_year) values(0, 'Compass', 2021);
insert into car(status, model, model_year) values(0, 'Renegade', 2021);
insert into car(status, model, model_year) values(0, 'Gladiator', 2021);


create table customer_car(
	id serial primary key,
	car_id int references car(id),
	customer_id int references app_user(id)
);
select * from customer_car cc ;
select * from app_user au ;
insert into customer_car (car_id,customer_id) values (3,3);
select * from car;

select * from car c
	join customer_car cc on cc.car_id  = c.id
	join app_user au on cc.customer_id = au.id 
	where au.id = 3;



select * from payment p ;
update payment set monthly_amount = 5, loan_amount = 100.9, payment_term = 12, payment_remaining = 23, loan_balance = 3 where customer_id = 7 and car_id = 4;
create table offer_status(
	id int primary key,
	type text
);
select * from car;
select * from offer;
select * from app_user au ;
delete from app_user where id = 8;

update payment set loan_balance = 2000 where id=1;
alter table payment
add column payment_remaining int;
insert into offer_status values(0,'NONE');
insert into offer_status values(1,'ACCEPTED');
insert into offer_status values(2,'REJECTED');
insert into offer_status values(3, 'PENDING');

create table offer(
	id serial primary key,
	status int references offer_status(id),
	car_id int references car(id),
	customer_id int references app_user(id)
);

select * from car;
select * from offer;
delete from offer where id = 3;

create table payment(
	id serial primary key,
	customer_id int references app_user(id),
	car_id int references car(id),
	monthly_amount double precision,
	loan_amount double precision,
	payment_term int,
	payment_remaining int,
	loan_balance double precision
);

