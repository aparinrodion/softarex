create table option
(
    id       serial primary key not null,
    name     varchar            not null,
    field_id bigint             not null references fields (id)
)