   CREATE TABLE IF NOT EXISTS EMPLOYEETYPE_ENUM(
    ENUM_ID INT(1),
   DESCRIPTION VARCHAR(50)  
   
   );
   
   CREATE TABLE IF NOT EXISTS EMPLOYEEDEPT_ENUM(
    ENUM_ID INT(1),
   DESCRIPTION VARCHAR(50)  
      );

     CREATE TABLE IF NOT EXISTS USERROLE_ENUM(
    ENUM_ID INT(1),
   DESCRIPTION VARCHAR(50)  
      );

CREATE TABLE IF NOT EXISTS EMPLOYEE (
   EMPLOYEE_ID INT(32) AUTO_INCREMENT,
   FIRST_NAME VARCHAR(100),
   LAST_NAME VARCHAR(100),
   PHONE_NUMBER VARCHAR(12),
   EMAIL_ADDRESS VARCHAR(64), 
   
   START_DATE DATE,
   END_DATE DATE,
   EMPLOYEETYPE_EN INT(1),
   EMPLOYEEDEPTTYPE_EN INT(1),
   USERROLE_EN INT(1),
   PASSWORD VARCHAR(32),
   
   PRIMARY KEY (EMPLOYEE_ID),
   FOREIGN KEY (EMPLOYEETYPE_EN) REFERENCES EMPLOYEETYPE_ENUM(ENUM_ID),
   FOREIGN KEY (EMPLOYEEDEPTTYPE_EN) REFERENCES EMPLOYEEDEPT_ENUM(ENUM_ID),
   FOREIGN KEY (USERROLE_EN) REFERENCES USERROLE_ENUM(ENUM_ID)
     
   );
   
--   INSERT INTO EMPLOYEETYPE_ENUM (ENUM_ID, DESCRIPTION) VALUES (1,'Part-time Intern');
--  INSERT INTO EMPLOYEETYPE_ENUM (ENUM_ID, DESCRIPTION) VALUES (2,'Part-time Employee');
--  INSERT INTO EMPLOYEETYPE_ENUM (ENUM_ID, DESCRIPTION) VALUES (3,'Full-time Intern');
--    INSERT INTO EMPLOYEETYPE_ENUM (ENUM_ID, DESCRIPTION) VALUES (4,'Full-time Employee');
--    
--   INSERT INTO EMPLOYEEDEPT_ENUM (ENUM_ID, DESCRIPTION) VALUES (1,'Development'); 
--  INSERT INTO EMPLOYEEDEPT_ENUM (ENUM_ID, DESCRIPTION) VALUES (2,'Testing'); 
--    INSERT INTO EMPLOYEEDEPT_ENUM (ENUM_ID, DESCRIPTION) VALUES (3,'Automation'); 
--    
--       INSERT INTO USERROLE_ENUM (ENUM_ID, DESCRIPTION) VALUES (1,'Admin'); 
--  INSERT INTO USERROLE_ENUM (ENUM_ID, DESCRIPTION) VALUES (2,'Manager'); 
--    INSERT INTO USERROLE_ENUM (ENUM_ID, DESCRIPTION) VALUES (3,'Employee'); 
 --  INSERT INTO USERROLE_ENUM (ENUM_ID, DESCRIPTION) VALUES (4,'Deactivated'); 