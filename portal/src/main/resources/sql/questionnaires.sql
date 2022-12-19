create table questionnaires
(
    id            serial primary key,
    name          varchar(100) not null,
    creation_date date default current_date,
    author_id     int          not null references users (id)
)