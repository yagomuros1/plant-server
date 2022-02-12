SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `MEDIATOR_EMAIL_UNIQUE` (`email` ASC)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NULL,
    `first_name` VARCHAR(255) NULL,
    `second_name` VARCHAR(255) NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     INDEX `fk_admin_user_idx` (`user_id` ASC),
	CONSTRAINT `fk_admin_user`
	    FOREIGN KEY (`user_id`)
	    REFERENCES `user` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `crop`;
CREATE TABLE `crop` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    `conservation` VARCHAR(255) NOT NULL,
    `difficulty_id` BIGINT NOT NULL,
    `situation_id` BIGINT NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     CONSTRAINT `fk_crop_difficulty`
            FOREIGN KEY (`difficulty_id`)
            REFERENCES `difficulty` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
     CONSTRAINT `fk_crop_situation`
            FOREIGN KEY (`situation_id`)
            REFERENCES `situation` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `difficulty`;
CREATE TABLE `difficulty` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `companion`;
CREATE TABLE `companion` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `external_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `crop_category`;
CREATE TABLE `crop_category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `crop_id` BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     CONSTRAINT `fk_cropCategory_crop`
            FOREIGN KEY (`crop_id`)
            REFERENCES `crop` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
     CONSTRAINT `fk_cropCategory_category`
            FOREIGN KEY (`category_id`)
            REFERENCES `category` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `crop_companion`;
CREATE TABLE `crop_companion` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `crop_id` BIGINT NOT NULL,
    `companion_id` BIGINT NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     CONSTRAINT `fk_cropCompanion_crop`
            FOREIGN KEY (`crop_id`)
            REFERENCES `crop` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
     CONSTRAINT `fk_cropCompanion_companion`
            FOREIGN KEY (`companion_id`)
            REFERENCES `companion` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `subtitle` VARCHAR(255) NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `crop_property`;
CREATE TABLE `crop_property` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `crop_id` BIGINT NOT NULL,
    `property_id` BIGINT NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`),
     CONSTRAINT `fk_crop_property_crop`
            FOREIGN KEY (`crop_id`)
            REFERENCES `crop` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
     CONSTRAINT `fk_crop_property_property`
            FOREIGN KEY (`property_id`)
            REFERENCES `property` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

DROP TABLE IF EXISTS `situation`;
CREATE TABLE `situation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `version` BIGINT NOT NULL DEFAULT 0,
     PRIMARY KEY (`id`)
)  CHARACTER SET utf8 COLLATE utf8_bin engine=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;
