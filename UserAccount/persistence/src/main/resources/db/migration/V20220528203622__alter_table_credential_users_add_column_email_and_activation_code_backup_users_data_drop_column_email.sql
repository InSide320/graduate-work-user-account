alter table if exists credential_users
    add column email_backup    varchar(250) default 'dekud2109@gmail.com',
    add column activation_code varchar(250);

alter table if exists backup_users_data
    drop column email_backup cascade;