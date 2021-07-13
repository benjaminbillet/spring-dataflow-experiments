# Spring Dataflow experiments

## Build

```
mvn clean package
```

## Run locally

API Poller Source:

```
java -jar api-poller-source/target/api-poller-source-1.0-SNAPSHOT.jar \
    --api-poller.uri=https://random-data-api.com/api/users/random_user \
    --spring.cloud.stream.bindings.output.destination=raw-users \
    --spring.cloud.stream.poller.fixedDelay=5000
```

JOLT transformer:

```
java -jar jolt-transformer/target/jolt-transformer-1.0-SNAPSHOT.jar \
    --jolt-transformer.jolt-specification=file:./jolt-transformer/src/main/resources/testspec.json \
    --spring.cloud.stream.bindings.input.destination=raw-users \
    --spring.cloud.stream.bindings.output.destination=users
```

In-memory key-value store:

```
java -jar in-memory-store/target/in-memory-store-1.0-SNAPSHOT.jar \
    --in-memory-store.key-json-path=$.id \
    --spring.cloud.stream.bindings.input.destination=users \
    --server.port=9999
```