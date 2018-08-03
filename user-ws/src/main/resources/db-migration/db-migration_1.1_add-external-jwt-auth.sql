CREATE TABLE `external_jwt_credential` (
  `external_jwt_credential_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `issuer` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`external_jwt_credential_id`),
  KEY `fk_external_jwt_credential_id_user_idx` (`user_id`),
  CONSTRAINT `fk_external_jwt_credential_id_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE cascade ON UPDATE NO ACTION
);
