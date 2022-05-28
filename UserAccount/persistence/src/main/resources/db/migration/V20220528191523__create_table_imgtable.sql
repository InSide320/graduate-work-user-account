create table if not exists img_table
(
    id       serial primary key,
    name_img varchar(250),
    photo    bytea
)