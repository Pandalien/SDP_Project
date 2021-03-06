-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CSS_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CSS_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CSS_DB` DEFAULT CHARACTER SET utf8 ;
USE `CSS_DB` ;

-- -----------------------------------------------------
-- Table `CSS_DB`.`suburb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`suburb` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`suburb` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `suburb` VARCHAR(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `suburb_UNIQUE` (`suburb` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`user` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`user` (
  `id` INT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `joined_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` INT NULL,
  `visible` TINYINT(1) NULL DEFAULT 0,
  `introduction` VARCHAR(500) NULL DEFAULT '',
  `email` VARCHAR(100) NOT NULL DEFAULT '',
  `suburb_id` INT NOT NULL,
  `DOB` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `rating` DOUBLE NULL DEFAULT 0.0,
  `phone` VARCHAR(45) NULL,
  `img` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_suburb1_idx` (`suburb_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `fk_user_suburb1`
    FOREIGN KEY (`suburb_id`)
    REFERENCES `CSS_DB`.`suburb` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`classification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`classification` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`classification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`skills`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`skills` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`skills` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT '',
  `description` VARCHAR(200) NULL DEFAULT '',
  `classification_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_skills_classification1_idx` (`classification_id` ASC),
  CONSTRAINT `fk_skills_classification1`
    FOREIGN KEY (`classification_id`)
    REFERENCES `CSS_DB`.`classification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`user_skills`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`user_skills` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`user_skills` (
  `user_id` INT NOT NULL,
  `skills_id` INT NOT NULL,
  `level` INT NULL,
  PRIMARY KEY (`user_id`, `skills_id`),
  INDEX `fk_user_skills_skills1_idx` (`skills_id` ASC),
  CONSTRAINT `fk_user_skills_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSS_DB`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_skills_skills1`
    FOREIGN KEY (`skills_id`)
    REFERENCES `CSS_DB`.`skills` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`adverts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`adverts` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`adverts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` VARCHAR(1000) NOT NULL DEFAULT '',
  `closed` TINYINT(1) NULL DEFAULT 0,
  `suburb_id` INT NOT NULL,
  `classification_id` INT NOT NULL,
  `expiry_date` TIMESTAMP NULL,
  `img` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_adverts_user1_idx` (`user_id` ASC),
  INDEX `fk_adverts_suburb1_idx` (`suburb_id` ASC),
  INDEX `fk_adverts_classification1_idx` (`classification_id` ASC),
  CONSTRAINT `fk_adverts_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSS_DB`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adverts_suburb1`
    FOREIGN KEY (`suburb_id`)
    REFERENCES `CSS_DB`.`suburb` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adverts_classification1`
    FOREIGN KEY (`classification_id`)
    REFERENCES `CSS_DB`.`classification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`responders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`responders` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`responders` (
  `user_id` INT NOT NULL,
  `adverts_id` INT NOT NULL,
  `message` VARCHAR(200) NULL,
  `time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `feedback` VARCHAR(45) NULL,
  `rating` INT NULL,
  `status` INT NULL DEFAULT 0,
  `feedback_worker` VARCHAR(45) NULL DEFAULT '',
  `rating_worker` INT NULL,
  PRIMARY KEY (`user_id`, `adverts_id`),
  INDEX `fk_responders_adverts1_idx` (`adverts_id` ASC),
  CONSTRAINT `fk_responders_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSS_DB`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_responders_adverts1`
    FOREIGN KEY (`adverts_id`)
    REFERENCES `CSS_DB`.`adverts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`requirements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`requirements` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`requirements` (
  `adverts_id` INT NOT NULL,
  `skills_id` INT NOT NULL,
  `level` INT NULL,
  PRIMARY KEY (`adverts_id`, `skills_id`),
  INDEX `fk_requirements_skills1_idx` (`skills_id` ASC),
  CONSTRAINT `fk_requirements_adverts1`
    FOREIGN KEY (`adverts_id`)
    REFERENCES `CSS_DB`.`adverts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requirements_skills1`
    FOREIGN KEY (`skills_id`)
    REFERENCES `CSS_DB`.`skills` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSS_DB`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CSS_DB`.`messages` ;

CREATE TABLE IF NOT EXISTS `CSS_DB`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(200) NOT NULL DEFAULT '',
  `is_read` TINYINT(1) NULL DEFAULT 0,
  `sent_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_messages_user1_idx` (`sender_id` ASC),
  INDEX `fk_messages_user2_idx` (`receiver_id` ASC),
  CONSTRAINT `fk_messages_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `CSS_DB`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_user2`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `CSS_DB`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
