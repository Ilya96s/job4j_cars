CREATE TABLE IF NOT EXISTS auto_user(
    id SERIAL PRIMARY KEY,
    login VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

comment on table auto_user is 'Таблица с пользователями';
comment on column auto_user.id is 'Идентификатор пользователя';
comment on column auto_user.login is 'Логин пользователя';
comment on column auto_user.password is 'Пароль пользователя';