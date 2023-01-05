CREATE TABLE IF NOT EXISTS driver (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    user_id INT NOT NULL REFERENCES auto_user(id)
);

comment on table driver is 'Таблица с водителями';
comment on column driver.id is 'Идентификатор';
comment on column driver.name is 'Имя';
comment on column driver.user_id is 'Идентификатор пользователя';