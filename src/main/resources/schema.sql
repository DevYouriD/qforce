--CREATING TABLE ON APPLICATION STARTUP ONLY WORKS IF DB PATH IS ABSOLUTE???

-- CREATE TABLE IF NOT EXISTS ANALYTICS (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     fetchDataByName NVARCHAR(255),
--     fetchDataById NVARCHAR(255)
-- );

--EXAMPLE TABLE SCHEMAS

-- CREATE TABLE IF NOT EXISTS ANALYTICS_BY_NAME (
--                                          id INT AUTO_INCREMENT PRIMARY KEY,
--                                          DataByNameApiCall NVARCHAR(255)
-- );
--
-- CREATE TABLE IF NOT EXISTS ANALYTICS_BY_ID (
--                                          id INT AUTO_INCREMENT PRIMARY KEY,
--                                          DataByIdApiCall NVARCHAR(255)
-- );