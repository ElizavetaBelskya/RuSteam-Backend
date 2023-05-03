docker pull redis
docker build -t rusteam_redis .
docker volume create --name rusteam_redis_volume
