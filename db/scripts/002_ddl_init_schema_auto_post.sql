CREATE TABLE IF NOT EXISTS auto_post(
    id SERIAL PRIMARY KEY,
    text VARCHAR NOT NULL,
    created DATE NOT NULL ,
    auto_user_id INT NOT NULL REFERENCES auto_user(id)
);

comment on table auto_post is 'Таблица с объявлениями';
comment on column auto_post.id is 'Идентификатор объявления';
comment on column auto_post.text is 'Описание объявления';
comment on column auto_post.created is 'Дата создания объявления';
comment on column auto_post.auto_user_id is 'Идентификатор пользователя';