create table users(
                      id serial primary key not null,
                      email varchar(255) not null unique,
                      password varchar(255) not null,
                      first_name varchar(255),
                      last_name varchar(255),
                      phone_number varchar(50)
)