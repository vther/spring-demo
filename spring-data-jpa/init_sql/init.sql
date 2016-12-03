INSERT INTO `spring-data-jpa`.`t_employee` (`ID`, `FIRSTNAME`, `GENDER`, `ISACTIVE`, `LAST_NAME`, `SALARY`, `START_DATE`, `START_TIME`, `STARTTIMESTAMP`, `ADDRESS_ID`)
VALUES ('1', '头狸', 'MALE', '1', '胖', '99999', '2016-12-03', '2016-12-03 00:00:00', '2016-12-03 00:00:00', '1');
INSERT INTO `spring-data-jpa`.`t_employee` (`ID`, `FIRSTNAME`, `GENDER`, `ISACTIVE`, `LAST_NAME`, `SALARY`, `START_DATE`, `START_TIME`, `STARTTIMESTAMP`, `ADDRESS_ID`)
VALUES ('2', '本熊', 'MALE', '0', '熊', '88888', '2016-11-03', '2016-11-03 00:00:00', '2016-11-03 00:00:00', '2');
INSERT INTO `spring-data-jpa`.`t_employee` (`ID`, `FIRSTNAME`, `GENDER`, `ISACTIVE`, `LAST_NAME`, `SALARY`, `START_DATE`, `START_TIME`, `STARTTIMESTAMP`, `ADDRESS_ID`)
VALUES ('3', '小七', 'FEMALE', '0', '尹', '100000', '2016-10-03', '2016-10-03 00:00:00', '2016-10-03 00:00:00', '3');

INSERT INTO `spring-data-jpa`.`t_address` (`ADDRESSID`, `CITY`, `COUNTRY`, `POSTCODE`, `STREET`) VALUES ('1', '深圳', '中国', '12345', '布吉大道');
INSERT INTO `spring-data-jpa`.`t_address` (`ADDRESSID`, `CITY`, `COUNTRY`, `POSTCODE`, `STREET`) VALUES ('2', '熊本县', '日本', '32312', '熊本部');
INSERT INTO `spring-data-jpa`.`t_address` (`ADDRESSID`, `CITY`, `COUNTRY`, `POSTCODE`, `STREET`) VALUES ('3', '深圳', '中国', '12345', '坂田大道');

INSERT INTO `spring-data-jpa`.`t_emp_relation` (`EMP_ID`, `MANAGER_ID`) VALUES ('1', '3');
INSERT INTO `spring-data-jpa`.`t_emp_relation` (`EMP_ID`, `MANAGER_ID`) VALUES ('2', '3');
INSERT INTO `spring-data-jpa`.`t_emp_relation` (`EMP_ID`) VALUES ('3');