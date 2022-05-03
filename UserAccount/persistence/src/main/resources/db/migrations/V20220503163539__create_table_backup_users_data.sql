create table if not exists backup_users_data(
    id serial primary key,
    phone_number varchar(25) default '+380',
    email_backup varchar(50)
)