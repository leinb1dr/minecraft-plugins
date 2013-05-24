CREATE SCHEMA IF NOT EXISTS `status`;
USE `status`;
DROP TABLE IF EXISTS `player_quit_event` ;
DROP TABLE IF EXISTS `player_login_event` ;
DROP TABLE IF EXISTS `player` ;
CREATE  TABLE IF NOT EXISTS `player` (
  `player_id` INT NOT NULL AUTO_INCREMENT ,
  `screen_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`player_id`) );
CREATE  TABLE IF NOT EXISTS `player_login_event` (
  `player_login_event_id` INT NOT NULL AUTO_INCREMENT ,
  `player_id` INT NOT NULL ,
  `login` DATETIME NULL ,
  PRIMARY KEY (`player_login_event_id`) ,
  INDEX `fk_player_login_event_player_idx` (`player_id` ASC) ,
  CONSTRAINT `fk_player_login_event_player`
    FOREIGN KEY (`player_id` )
    REFERENCES `player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE  TABLE IF NOT EXISTS `player_quit_event` (
  `player_quit_event_id` INT NOT NULL AUTO_INCREMENT ,
  `player_id` INT NOT NULL ,
  `quit` DATETIME NULL ,
  PRIMARY KEY (`player_quit_event_id`) ,
  INDEX `fk_player_quit_event_player1_idx` (`player_id` ASC) ,
  CONSTRAINT `fk_player_quit_event_player1`
    FOREIGN KEY (`player_id` )
    REFERENCES `player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE IF NOT EXISTS `player_status` (`screen_name` INT, `login` INT, `quit` INT);
DROP VIEW IF EXISTS `player_status` ;
DROP TABLE IF EXISTS `player_status`;
CREATE  OR REPLACE VIEW `player_status` AS
SELECT p.screen_name, l.login, q.quit 
		from player as p join player_login_event as l on p.player_id = l.player_id
			join player_quit_event as q on p.player_id = q.player_id
		where (Select max(login) from player_login_event as sl where p.player_id = sl.player_id) = l.login;


