PROBLEM STATEMENT
	DEVELOP A PSEUDO FULL-STACK WEB APPLICATION FOR TASK MANAGEMENT TO-DO LIST.

Initial version of this program contains these functionalities:
	ADD TASK
	EDIT TASK
	FILTER TASK
	DELETE TASK
	OVERDUE TASK
	ARCHIVE TASK

REQUIREMENTS
	You will need Java,Maven,MySQL-RDBMS and a browser installed to run this application.
	Set the JAVA_HOME Variable:
		1. Locate your Java installation directory
		2. Locate to Environment variables in Advanced System Settings.
		3. Under System Variables, click New.
		4. In the Variable Name field, enter: JAVA_HOME 
		5. In the Variable Value field, enter your JDK installation path.
		6. Go to Path: click NEW
		7. In the field, give '%JAVA_HOME%\bin'.
	Set the MAVEN_HOME Variable:
		1. Locate your Maven installation directory
		2. Locate to Environment variables in Advanced System Settings.
		3. Under System Variables, click New.
		4. In the Variable Name field, enter: Maven_HOME 
		5. In the Variable Value field, enter your Maven installation path.
		6. Go to Path: click NEW
		7. In the field, give '%MAVEN_HOME%\bin'.

USAGE:
	Set MySQL username, password in source code:
		1. Extract taskmanagement.zip.
		2. locate to src\main\resources\application.properties
		3. Change username and password to access mysql
			spring.datasource.username='your_username'
			spring.datasource.password='your_password'
	
	DDL, DML for MySQL:
	
		1. create database taskmanagement;
		2. CREATE TABLE `taskmanagement`.`status` (
			`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			`status` VARCHAR(255) NULL,
			PRIMARY KEY (`id`));
		3. CREATE TABLE `taskmanagement`.`label` (
			`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			`label` VARCHAR(255) NULL,
			PRIMARY KEY (`id`));
		4. INSERT INTO `taskmanagement`.`label` (`id`, `label`) VALUES ('1', 'Personal');
			INSERT INTO `taskmanagement`.`label` (`id`, `label`) VALUES ('2', 'Work');
			INSERT INTO `taskmanagement`.`label` (`id`, `label`) VALUES ('3', 'Shopping');
			INSERT INTO `taskmanagement`.`label` (`id`, `label`) VALUES ('4', 'Others');

		5. INSERT INTO `taskmanagement`.`status` (`id`, `status`) VALUES ('1', 'New');
			INSERT INTO `taskmanagement`.`status` (`id`, `status`) VALUES ('2', 'In-Progress');
			INSERT INTO `taskmanagement`.`status` (`id`, `status`) VALUES ('3', 'Completed');

		6. locate to root folder taskmanagement, open cmd:
			mvn spring-boot:run
		
		7. Go to webpages, click on index.html/
	
