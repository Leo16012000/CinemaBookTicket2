create table if not exists users(
	id int primary key auto_increment,
	name varchar(128) not null,
	username varchar(64) not null,
	password varchar(64) not null,
	created_at timestamp default current_timestamp,
	permission enum ('ADMIN', 'USER', 'GUEST') NOT NULL
);

create table if not exists movies(
	id int primary key auto_increment,
	title varchar(512) not null,
	duration_time integer not null,
	country varchar(64) not null,
	price int not null,
	created_at timestamp default current_timestamp
);

create table if not exists showtimes(
	id int primary key auto_increment,
	start_time timestamp not null,
	end_time timestamp not null,
	movie_id int not null,
	created_at timestamp not null default current_timestamp,
	auditorium_id int not null REFERENCES auditoriums(id),
	foreign key(movie_id) references movies(id)
);

create table if not exists reservations(
	id int primary key auto_increment,
	user_id int not null,
	showtime_id int not null,
	created_at timestamp default current_timestamp not null,
	foreign key(showtime_id) references showtimes(id),
	foreign key(user_id) references users(id)
);

create table if not exists auditoriums(
	id int primary key auto_increment,
	auditorium_num int not null,
	seats_row_num int not null,
	seats_column_num int not null,
	created_at timestamp not NULL default current_timestamp
);

create table if not exists seats(
	id int primary key auto_increment,
	auditorium_id int not null,
	seat_column int not null,
	seat_row int not null,
	created_at timestamp not null default current_timestamp,
	foreign key(auditorium_id) references auditoriums(id)
);


create table if not exists seats_reservation(
	id int primary key auto_increment,
	seat_id int not null,
	reservation_id int not null,
	created_at timestamp not null default current_timestamp,
	foreign key(seat_id) references seats(id),
	foreign key(reservation_id) references reservations(id)
);

create table if not exists session(
	id int primary key auto_increment,
	user_id int not null,
	start_time timestamp not null,
	end_time timestamp not null,
	message varchar(255),
	foreign key(user_id) references users(id)
);
