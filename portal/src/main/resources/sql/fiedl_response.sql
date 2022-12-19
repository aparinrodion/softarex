create table field_response
(
    id         serial primary key not null,
    field_id    int                not null references fields (id),
    response_id int                not null references response (id),
    answer     varchar            not null
)