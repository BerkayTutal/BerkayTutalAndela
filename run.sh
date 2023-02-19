mvn clean install; \
docker build -t app .
docker-compose build --no-cache
docker-compose up --force-recreate