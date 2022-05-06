create table if not exists credential_users(
    id serial primary key,
    authorization_email varchar(50),
    authorization_password varchar(50)
)