create table if not exists detail_users(
    id serial primary key,
    role_type varchar(25),
    date_enter date,
    date_release date,
    group_cyrillic varchar(25),
    group_transliteration varchar(25)
)