export DATAFLOW_VERSION=2.8.1
export SKIPPER_VERSION=2.7.1
export HOST_MOUNT_PATH=~/.m2
export DOCKER_MOUNT_PATH=/home/cnb/.m2/

# docker-compose -f ./docker-compose.yml -f ./docker-compose-prometheus.yml up
docker-compose -f ./docker-compose.yml up -d

