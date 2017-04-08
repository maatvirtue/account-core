CREATE TABLE `user_status` (
  `user_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_status_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL,
  `user_status_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_user_user_status_idx` (`user_status_id`),
  CONSTRAINT `fk_user_user_status` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`user_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `email` (
  `email_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `email` varchar(100) NOT NULL,
  `confirmed` bool not null,
  `primary` bool not null,
  PRIMARY KEY (`email_id`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`),
  KEY `fk_email_user_idx` (`user_id`),
  CONSTRAINT `fk_email_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `confirmation_email` (
  `confirmation_email_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email_id` int(11) unsigned NOT NULL,
  `confirmation_code` char(20) NOT NULL,
  PRIMARY KEY (`confirmation_email_id`),
  UNIQUE KEY `confirmation_email_id_UNIQUE` (`confirmation_email_id`),
  KEY `fk_confirmation_email_email_idx` (`email_id`),
  CONSTRAINT `fk_confirmation_email_email` FOREIGN KEY (`email_id`) REFERENCES `email` (`email_id`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `password_credential` (
  `password_credential_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password_hash` binary(32) NOT NULL,
  `salt` char(20) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`password_credential_id`),
  KEY `fk_password_credential_user_idx` (`user_id`),
  CONSTRAINT `fk_password_credential_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `role_permission` (
  `role_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  KEY `fk_rolePermission_role_idx` (`role_id`),
  KEY `fk_rolePermission_permission_idx` (`permission_id`),
  CONSTRAINT `fk_rolePermission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rolePermission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

insert into `user_status` (`name`) values ('pendingActivation'), ('active');
