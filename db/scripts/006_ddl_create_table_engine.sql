CREATE TABLE IF NOT EXISTS engine(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

comment on table engine is 'Таблица с двигателями';
comment on column engine.id is 'Идентификатор';
comment on column engine.name is 'Название';