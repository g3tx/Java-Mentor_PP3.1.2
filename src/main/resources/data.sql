# Дамп таблицы roles
# ------------------------------------------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles`
(
    `role_id`  bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `rolename` varchar(250) DEFAULT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `role_id` (`role_id`),
    UNIQUE KEY `rolename` (`rolename`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `t_roles` (`role_id`, `rolename`)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

# Дамп таблицы users
# ------------------------------------------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`
(
    `user_id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `password`  varchar(250) DEFAULT NULL,
    `firstname` varchar(250) DEFAULT NULL,
    `lastname`  varchar(250) DEFAULT NULL,
    `age`       bigint(20)   DEFAULT NULL,
    `email`     varchar(250) DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_id` (`user_id`),
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `t_users` (`user_id`, `password`, `firstname`, `lastname`, `age`, `email`)
VALUES (1, '$2a$12$UmXTqpzuG.38VRVT9Vp2r.KWvY0XXftOEow/9P.xYtXtFCBJmczg6', 'none', 'none', 0, 'none'),
       (2, '$2a$12$LhgKNh0ap2.SdftZAw4idus0o9DW13WbQyiO9SaBagVnrXF5QULOW', 'none', 'none', 0, 'none'),
       (3, '$2a$12$3N8Lqz8aMKQbBpFFA6avouHVzvm2U5K3z.MGsj5XmWMIB0jqNowCa', 'Thor', 'Odinson', 2098,
        'GodOfThunder@gmail.com'),
       (4, 'LokiRuled', '$2a$12$WaZU5Q5kEekLAhYjWd/0d..zpmf7/RuhS33fiJaq/i.h1h.waarT2', 'Loki', 'Laufeyson', 2059,
        'GodOfMischiefr@gmail.com'),
       (5, '$2a$12$MHxCgH6nQlSqUfZVH4wKcuWGgda0JODso/th/v7DpvNnqMh1CzWaq', 'Odin', 'Borson', 6089,
        'AllFather@gmail.com'),
       (6, '$2a$12$aHCAgyCWfKYlYIyE2/u9POj1i/YjjqXKgG8N3h7AtHs5sixpCSHi6', 'Hela', 'Odinson', 3574,
        'GoddessOfDeath@gmail.com');

# Дамп таблицы t_users_roles
# ------------------------------------------------------------
DROP TABLE IF EXISTS `t_users_roles`;
CREATE TABLE `t_users_roles`
(
    `user_id` bigint(20) unsigned DEFAULT NULL,
    `role_id` bigint(20) unsigned DEFAULT NULL,
    KEY `hasuser` (`user_id`),
    KEY `hasrole` (`role_id`),
    CONSTRAINT `hasrole` FOREIGN KEY (`role_id`) REFERENCES `t_roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `hasuser` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `t_users_roles` (`user_id`, `role_id`)
VALUES (1, 1), -- User Admin has role ADMIN
       (2, 2), -- User User has role USER
       (3, 2), -- User Thor has role USER
       (4, 2), -- User Loki has role USER
       (5, 1), -- User Odin has role ADMIN
       (5, 2), -- User Odin has role USER
       (6, 2); -- User Hela has role USER

