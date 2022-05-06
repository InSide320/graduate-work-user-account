create table if not exists users_personal_data
(
    id         serial primary key not null,
    last_name_transliteration varchar(25) not null,
    first_name_transliteration varchar (25) not null,
    last_name varchar (25) not null,
    first_name varchar (25) not null,
    midl_name varchar (25) not null
)
