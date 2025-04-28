-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`adresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`adresa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `ulica` VARCHAR(45) NOT NULL,
  `broj` VARCHAR(45) NOT NULL,
  `grad` VARCHAR(45) NOT NULL,
  `drzava` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`osoba` (
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `adresa_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_osoba_adresa1_idx` (`adresa_id` ASC) VISIBLE,
  INDEX `fk_osoba_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_osoba_adresa1`
    FOREIGN KEY (`adresa_id`)
    REFERENCES `mydb`.`adresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_osoba_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`nastavnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nastavnik` (
  `status` VARCHAR(45) NOT NULL,
  `osoba_id` BIGINT NOT NULL,
  PRIMARY KEY (`osoba_id`),
  INDEX `fk_nastavnik_osoba1_idx` (`osoba_id` ASC) VISIBLE,
  CONSTRAINT `fk_nastavnik_osoba1`
    FOREIGN KEY (`osoba_id`)
    REFERENCES `mydb`.`osoba` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`univerzitet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`univerzitet` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `kontakt` VARCHAR(45) NOT NULL,
  `opis` TEXT NOT NULL,
  `adresa_id` BIGINT NOT NULL,
  `nastavnik_osoba_rektor` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_univerzitet_adresa1_idx` (`adresa_id` ASC) VISIBLE,
  INDEX `fk_univerzitet_nastavnik1_idx` (`nastavnik_osoba_rektor` ASC) VISIBLE,
  CONSTRAINT `fk_univerzitet_adresa1`
    FOREIGN KEY (`adresa_id`)
    REFERENCES `mydb`.`adresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_univerzitet_nastavnik1`
    FOREIGN KEY (`nastavnik_osoba_rektor`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = ascii;


-- -----------------------------------------------------
-- Table `mydb`.`fakultet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fakultet` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `opis` TEXT NOT NULL,
  `kontakt` VARCHAR(45) NOT NULL,
  `univerzitet_id` BIGINT NOT NULL,
  `adresa_id` BIGINT NOT NULL,
  `dekan` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fakultet_univerzitet1_idx` (`univerzitet_id` ASC) VISIBLE,
  INDEX `fk_fakultet_adresa1_idx` (`adresa_id` ASC) VISIBLE,
  INDEX `fk_fakultet_nastavnik1_idx` (`dekan` ASC) VISIBLE,
  CONSTRAINT `fk_fakultet_univerzitet1`
    FOREIGN KEY (`univerzitet_id`)
    REFERENCES `mydb`.`univerzitet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_fakultet_adresa1`
    FOREIGN KEY (`adresa_id`)
    REFERENCES `mydb`.`adresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fakultet_nastavnik1`
    FOREIGN KEY (`dekan`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`studiskiProgram`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`studiskiProgram` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `opis` TEXT NOT NULL,
  `fakultet_id` BIGINT NOT NULL,
  `rukovodioc` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_studiskiProgram_fakultet1_idx` (`fakultet_id` ASC) VISIBLE,
  INDEX `fk_studiskiProgram_nastavnik1_idx` (`rukovodioc` ASC) VISIBLE,
  CONSTRAINT `fk_studiskiProgram_fakultet1`
    FOREIGN KEY (`fakultet_id`)
    REFERENCES `mydb`.`fakultet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studiskiProgram_nastavnik1`
    FOREIGN KEY (`rukovodioc`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `brojIndeksa` VARCHAR(45) NOT NULL,
  `godinaUpisa` DATE NOT NULL,
  `prosecnaOcena` FLOAT NOT NULL,
  `osvojeniESPB` INT NOT NULL,
  `osoba_id` BIGINT NOT NULL,
  `studiskiProgram_id` BIGINT NOT NULL,
  PRIMARY KEY (`osoba_id`),
  INDEX `fk_student_studiskiProgram1_idx` (`studiskiProgram_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_studiskiProgram1`
    FOREIGN KEY (`studiskiProgram_id`)
    REFERENCES `mydb`.`studiskiProgram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_osoba1`
    FOREIGN KEY (`osoba_id`)
    REFERENCES `mydb`.`osoba` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`zvanje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`zvanje` (
  `nastavnik_osoba_id` BIGINT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `datumIzbora` DATE NOT NULL,
  `datumPrestanka` DATE NOT NULL,
  PRIMARY KEY (`nastavnik_osoba_id`),
  CONSTRAINT `fk_zvanje_nastavnik1`
    FOREIGN KEY (`nastavnik_osoba_id`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`predmet` (
  `id` BIGINT NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  `esbp` INT NOT NULL,
  `studiskiProgram_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_predmet_studiskiProgram1_idx` (`studiskiProgram_id` ASC) VISIBLE,
  CONSTRAINT `fk_predmet_studiskiProgram1`
    FOREIGN KEY (`studiskiProgram_id`)
    REFERENCES `mydb`.`studiskiProgram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`nastavniMaterijal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nastavniMaterijal` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `opis` VARCHAR(45) NOT NULL,
  `url` VARCHAR(45) NULL,
  `predmet_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_nastavniMaterijal_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`silabus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`silabus` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `tekst` TEXT NOT NULL,
  `predmet_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_silabus_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ishod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ishod` (
  `naziv` VARCHAR(45) NOT NULL,
  `termin` DATE NOT NULL,
  `silabus_id` BIGINT NOT NULL,
  PRIMARY KEY (`silabus_id`),
  CONSTRAINT `fk_ishod_silabus1`
    FOREIGN KEY (`silabus_id`)
    REFERENCES `mydb`.`silabus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`obavjestenje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`obavjestenje` (
  `idobavjestenje` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `sadrzaj` TEXT NOT NULL,
  `datum` DATE NOT NULL,
  `fk_idpredmet` BIGINT NOT NULL,
  `nastavnik_osoba_id` BIGINT NOT NULL,
  PRIMARY KEY (`idobavjestenje`, `fk_idpredmet`, `nastavnik_osoba_id`),
  INDEX `fk_obavjestenje_predmet1_idx` (`fk_idpredmet` ASC) VISIBLE,
  INDEX `fk_obavjestenje_nastavnik1_idx` (`nastavnik_osoba_id` ASC) VISIBLE,
  CONSTRAINT `fk_obavjestenje_predmet1`
    FOREIGN KEY (`fk_idpredmet`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_obavjestenje_nastavnik1`
    FOREIGN KEY (`nastavnik_osoba_id`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`instrumentEvaluacije`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`instrumentEvaluacije` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `tip` VARCHAR(45) NOT NULL,
  `predmet_id` BIGINT NOT NULL,
  `opis` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_instrumentEvaluacije_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`zavrsniRad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`zavrsniRad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `opis` VARCHAR(45) NOT NULL,
  `naslov` VARCHAR(45) NOT NULL,
  `student_osoba_id` BIGINT NOT NULL,
  `nastavnik_osoba_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_zavrsniRad_student1_idx` (`student_osoba_id` ASC) VISIBLE,
  INDEX `fk_zavrsniRad_nastavnik1_idx` (`nastavnik_osoba_id` ASC) VISIBLE,
  CONSTRAINT `fk_zavrsniRad_student1`
    FOREIGN KEY (`student_osoba_id`)
    REFERENCES `mydb`.`student` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_zavrsniRad_nastavnik1`
    FOREIGN KEY (`nastavnik_osoba_id`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users_roles` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `roles` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_roles_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`prijavaIspita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`prijavaIspita` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_osoba_id` BIGINT NOT NULL,
  `predmet_id` BIGINT NOT NULL,
  `godina` YEAR NOT NULL,
  `rok` INT NOT NULL,
  `datumPrijave` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_student_has_predmet_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  INDEX `fk_student_has_predmet_student1_idx` (`student_osoba_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_has_predmet_student1`
    FOREIGN KEY (`student_osoba_id`)
    REFERENCES `mydb`.`student` (`osoba_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_has_predmet_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`IshodIspita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`IshodIspita` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `predmet_id` BIGINT NOT NULL,
  `student_osoba_id` BIGINT NOT NULL,
  `brojPokusaja` INT NOT NULL,
  `bodovi` INT NOT NULL,
  `polozen` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_predmet_has_student_student1_idx` (`student_osoba_id` ASC) VISIBLE,
  INDEX `fk_predmet_has_student_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  CONSTRAINT `fk_predmet_has_student_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_predmet_has_student_student1`
    FOREIGN KEY (`student_osoba_id`)
    REFERENCES `mydb`.`student` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`nastavnik_has_predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nastavnik_has_predmet` (
  `nastavnik_osoba_id` BIGINT NOT NULL,
  `predmet_id` BIGINT NOT NULL,
  PRIMARY KEY (`nastavnik_osoba_id`, `predmet_id`),
  INDEX `fk_nastavnik_has_predmet_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  INDEX `fk_nastavnik_has_predmet_nastavnik1_idx` (`nastavnik_osoba_id` ASC) VISIBLE,
  CONSTRAINT `fk_nastavnik_has_predmet_nastavnik1`
    FOREIGN KEY (`nastavnik_osoba_id`)
    REFERENCES `mydb`.`nastavnik` (`osoba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nastavnik_has_predmet_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `mydb`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

