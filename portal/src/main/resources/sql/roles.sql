create table users_roles(
    user_id bigserial not null references users(id),
    role_id serial not null references roles(id),
    primary key (user_id, role_id)
)