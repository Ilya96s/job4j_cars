CREATE TABLE IF NOT EXISTS history_owner(
    car_id INT NOT NULL REFERENCES car(id),
    driver_id INT NOT NULL REFERENCES driver(id),
    startAt TIMESTAMP NOT NULL,
    endAt TIMESTAMP NOT NULL
);

comment on table history_owner is 'Таблица с историями владения автомобилем';
comment on column history_owner.car_id is 'Идентификатор автомобиля';
comment on column history_owner.driver_id is 'Идентификатор водителя';
comment on column history_owner.startAt is 'Дата начала владения автомобилем';
comment on column history_owner.endAt is 'Дата окончания владения автомобилем';