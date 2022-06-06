insert into users_personal_data(last_name_transliteration,
                                first_name_transliteration,
                                last_name, first_name, midl_name)
values ('admin', 'admin', 'admin', 'admin', 'admin');
insert into detail_users(date_enter, date_release, group_cyrillic, group_transliteration)
VALUES (null, null, null, null);
insert into backup_users_data(phone_number)
VALUES (null);
insert into student_entrance_scores(ukrainian_language, math_subject, additional_subject,
                                    avg_subject, min_subject, max_subject)
VALUES (null, null, null, null, null, null);
insert into credential_users(authorization_email, authorization_password, role_type, activation_code)
VALUES ('ad@min',
        '$2a$08$BBPhucBDJ1ZukPZnZ/wHLuDQq9NfKRAtLU9snMFbL7uV2KAkGlj3u',
        'SUPER_ADMIN', null);