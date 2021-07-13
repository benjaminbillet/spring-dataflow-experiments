export DATAFLOW_VERSION=2.8.1
export SKIPPER_VERSION=2.7.1

# docker-compose -f ./docker-compose.yml -f ./docker-compose-prometheus.yml up
docker-compose -f ./docker-compose.yml up -d

