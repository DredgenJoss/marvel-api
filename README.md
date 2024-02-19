CREATE TABLE users (
id integer not null primary key,
first_name varchar(255),
second_name varchar(255),
email varchar(255) not null,
password varchar(255) not null
)

CREATE TABLE search_logs (
id integer not null primary key,
search_request varchar(255) not null,
user_id integer not null,
constraint FK_search_logs foreign key (user_id) references users(id)
);

insert into users (id, first_name, second_name, email, password) values
(1, 'test', 'test', 'test@test.test', 'test')
