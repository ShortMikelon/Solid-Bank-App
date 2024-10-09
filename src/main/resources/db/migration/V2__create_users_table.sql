CREATE TABLE `users` (
  user_id BIGINT NOT NULL,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role SMALLINT NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);