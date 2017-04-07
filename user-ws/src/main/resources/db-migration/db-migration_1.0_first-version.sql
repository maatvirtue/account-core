CREATE TABLE `userStatus` (
  `userStatusId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`userStatusId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `user` (
  `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `avatarUrl` varchar(100) DEFAULT NULL,
  `userStatusId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  KEY `fk_user_userStatus_idx` (`userStatusId`),
  CONSTRAINT `fk_user_userStatus` FOREIGN KEY (`userStatusId`) REFERENCES `userStatus` (`userStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `email` (
  `emailId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL,
  `email` varchar(100) NOT NULL,
  `confirmed` bool not null,
  `primary` bool not null,
  PRIMARY KEY (`emailId`),
  UNIQUE KEY `emailId_UNIQUE` (`emailId`),
  KEY `fk_email_user_idx` (`userId`),
  CONSTRAINT `fk_email_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `confirmationEmail` (
  `confirmationEmailId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `emailId` int(11) unsigned NOT NULL,
  `confirmationCode` char(20) NOT NULL,
  PRIMARY KEY (`confirmationEmailId`),
  UNIQUE KEY `confirmationEmailId_UNIQUE` (`confirmationEmailId`),
  KEY `fk_confirmationEmail_email_idx` (`emailId`),
  CONSTRAINT `fk_confirmationEmail_email` FOREIGN KEY (`emailId`) REFERENCES `email` (`emailId`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `passwordCredential` (
  `passwordCredentialId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `passwordHash` binary(32) NOT NULL,
  `salt` char(20) NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`passwordCredentialId`),
  KEY `fk_passwordCredential_user_idx` (`userId`),
  CONSTRAINT `fk_passwordCredential_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE cascade ON UPDATE NO ACTION
);

CREATE TABLE `permission` (
  `permissionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`permissionId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `role` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `role_permission` (
  `roleId` int(10) unsigned NOT NULL,
  `permissionId` int(10) unsigned NOT NULL,
  KEY `fk_rolePermission_role_idx` (`roleId`),
  KEY `fk_rolePermission_permission_idx` (`permissionId`),
  CONSTRAINT `fk_rolePermission_permission` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`permissionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rolePermission_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

insert into `userStatus` (`name`) values ('pendingActivation'), ('active');
