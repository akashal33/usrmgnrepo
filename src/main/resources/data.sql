INSERT INTO `country_master` (`country_id`, `country_name`) VALUES ('1', 'India');
INSERT INTO `country_master` (`country_id`, `country_name`) VALUES ('2', 'Us');

INSERT INTO `state_master` (`state_id`, `state_name`, `country_id`) VALUES ('1', 'Maharastra', '1');
INSERT INTO `state_master` (`state_id`, `state_name`, `country_id`) VALUES ('2', 'Telangana', '1');
INSERT INTO `state_master` (`state_id`, `state_name`, `country_id`) VALUES ('3', 'California', '2');
INSERT INTO `state_master` (`state_id`, `state_name`, `country_id`) VALUES ('4', 'Texas', '2');

INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('1', 'Mumbai', '1');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('2', 'Pune', '1');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('3', 'Nasik', '1');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('4', 'Nagpur', '1');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('5', 'Hyderabad', '2');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('6', 'Warangal', '2');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('7', 'Karimnagar', '2');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('8', 'Los Angeles', '3');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('9', 'San Diego', '3');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('10', 'San Jose', '3');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('11', 'San Francisco', '3');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('12', 'Sacramento', '3');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('13', 'Houston', '4');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('14', 'Dallas', '4');
INSERT INTO `city_master` (`city_id`, `city_name`, `state_id`) VALUES ('15', 'Austin', '4');



