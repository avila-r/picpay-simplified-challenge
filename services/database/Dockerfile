FROM postgres:16.2

ENV POSTGRES_DB picpay_db
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123

COPY init-db.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]
