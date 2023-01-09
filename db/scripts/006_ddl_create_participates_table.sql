CREATE TABLE participates  (
    id serial PRIMARY KEY,
    item_id int NOT NULL REFERENCES items(id),
    user_id int NOT NULL REFERENCES j_user(id)
);

comment on table participates is 'Описывает участников задачи';