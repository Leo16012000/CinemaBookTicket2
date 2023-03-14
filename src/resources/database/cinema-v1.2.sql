create table if not exists users(
	id int primary key auto_increment,
	name varchar(128) not null,
	username varchar(64) not null,
	password varchar(64) not null,
	created_at timestamp default current_timestamp
);

create table if not exists movies(
	id int primary key auto_increment,
	title varchar(512) not null,
	duration_time integer not null,
	country varchar(64) not null,
	price int not null,
	created_at timestamp default current_timestamp
);

create table if not exists reservations(
	id int primary key auto_increment,
	user_id int not null,
	showtime_id int not null,
	created_at timestamp default current_timestamp not null,
	foreign key(showtime_id) references showtimes(id),
	foreign key(user_id) references users(id)
);

create table if not exists showtimes(
	id int primary key auto_increment,
	start_time timestamp not null,
	end_time timestamp not null,
	movie_id int not null,
	created_at timestamp not null,
	foreign key(movie_id) references movies(id)
);

create table if not exists seats(
	id int primary key auto_increment,
	auditorium_id int not null,
	seat_num int not null,
	seat_row varchar(2) not null,
	created_at timestamp not null,
	foreign key(auditorium_id) references auditoriums(id)
);

create table if not exists auditoriums(
	id int primary key auto_increment,
	auditorium_num int not null,
	seats_num int,
	created_at timestamp not NULL default current_timestamp
);

create table if not exists seats_reservation(
	id int primary key auto_increment,
	seat_id int not null,
	reservation_id int not null,
	created_at timestamp not null,
	foreign key(seat_id) references seats(id),
	foreign key(reservation_id) references reservations(id)
);
--7/3/2023 1
create table if not exists session(
	id int primary key auto_increment,
	user_id int not null,
	start_time timestamp not null,
	end_time timestamp not null,
	message varchar(255),
	foreign key(user_id) references users(id)
);
--7/3/2023 2
ALTER table users add permission enum ('ADMIN', 'USER', 'GUEST') NOT NULL;
--8/3/2023
alter table seats rename column seat_num to seat_column;

alter table auditoriums rename column seats_num to seats_row_num;
alter table auditoriums add seats_column_num int;

DROP table seats_reservation ;
ALTER table seats add column reservation_id int not null;
ALTER table seats add foreign key(reservation_id) references reservations(id);
-- 10/3
ALTER table showtimes modify column created_at timestamp default current_timestamp;
INSERT INTO cinema.showtimes
(start_time, end_time, movie_id)
VALUES(current_timestamp(), current_timestamp(), 3);
ALTER table showtimes 
add column auditorium_id int not null;

INSERT INTO cinema.auditoriums
(auditorium_num, seats_row_num, created_at, seats_column_num)
VALUES(0, 5, current_timestamp(), 10);

ALTER TABLE showtimes ADD 
FOREIGN KEY (auditorium_id) REFERENCES auditoriums(id);

INSERT INTO cinema.users
(name, username, password, created_at, permission)
VALUES('ADMIN', 'admin', '1', CURRENT_TIMESTAMP(), 'ADMIN');
/*

*/
