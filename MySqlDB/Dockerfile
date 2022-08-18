FROM mysql:5

ENV MYSQL_ROOT_PASSWORD pass
ADD setup.sql /docker-entrypoint-initdb.d

EXPOSE 3306
