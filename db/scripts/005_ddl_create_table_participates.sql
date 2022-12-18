CREATE TABLE IF NOT EXISTS participates (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES auto_user(id),
    post_id INT NOT NULL REFERENCES auto_post(id)
);