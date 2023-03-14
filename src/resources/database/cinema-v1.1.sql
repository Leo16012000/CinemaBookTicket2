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

create table if not exists tickets(
	id int primary key auto_increment,
	seat varchar(16),
	showtime timestamp not null,
	user_id int not null,
	movie_id int not null,
	created_at timestamp default current_timestamp not null,
	foreign key(user_id) references users(id),
	foreign key(movie_id) references movies(id)
);
