CREATE TABLE IF NOT EXISTS car(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    engine_id INT NOT NULL REFERENCES engine(id)
);

comment on table car is 'Таблица с автомобилями';
comment on column car.id is 'Идентификатор';
comment on column car.name is 'Название';
comment on column car.engine_id is 'Идентификатор двигателя';