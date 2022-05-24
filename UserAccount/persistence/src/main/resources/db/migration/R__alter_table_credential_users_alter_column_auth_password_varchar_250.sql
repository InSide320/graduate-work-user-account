alter table credential_users
    alter column authorization_password type varchar(250),
    add column role_type varchar(50) default 'STUDENT';

alter table detail_users
    drop column role_type cascade;