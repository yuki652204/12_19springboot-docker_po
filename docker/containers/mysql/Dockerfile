FROM mysql:8.0

COPY my.cnf /etc/mysql/conf.d
COPY initialize_data.sql /docker-entrypoint-initdb.d
