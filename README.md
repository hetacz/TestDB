docker run --detach
--autEnv MYSQL_ROOT_PASSWORD=123456
--autEnv MYSQL_USER=rafal
--autEnv MYSQL_PASSWORD=123456
--autEnv MYSQL_DATABASE=results
--name mysql
--publish 3306:3306 mysql:8-oracle
