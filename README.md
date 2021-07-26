# Spring Dataflow experiments

## Run Confluent and Spring DataFlow stacks locally

```
./run.sh
```

## Run JupyterLab locally

Start:
```
docker-compose -f jupyter/docker-compose.yml up -d
```

Stop:
```
docker-compose -f jupyter/docker-compose.yml stop
```

## Build

JAR artifacts:
```
mvn clean package
```

Docker images:
```
docker build -f api-poller-source/Dockerfile -t benjaminbillet/api-poller-source:1.0.0 api-poller-source

docker build -f jolt-transformer/Dockerfile -t benjaminbillet/jolt-transformer:1.0.0 jolt-transformer

docker build -f in-memory-store/Dockerfile -t benjaminbillet/in-memory-store:1.0.0 in-memory-store
```

## Run services locally

### API Poller Source

```
java -jar api-poller-source/target/api-poller-source-1.0.0.jar \
    --api-poller.uri=https://random-data-api.com/api/users/random_user \
    --spring.cloud.stream.bindings.output.destination=raw-users \
    --spring.cloud.stream.poller.fixedDelay=5000
```

### JOLT transformer

```
java -jar jolt-transformer/target/jolt-transformer-1.0.0.jar \
    --jolt-transformer.jolt-specification=file:./jolt-transformer/src/main/resources/testspec.json \
    --spring.cloud.stream.bindings.input.destination=raw-users \
    --spring.cloud.stream.bindings.output.destination=users
```

### In-memory key-value store

```
java -jar in-memory-store/target/in-memory-store-1.0.0.jar \
    --in-memory-store.key-json-path=$.userId \
    --spring.cloud.stream.bindings.input.destination=users \
    --server.port=9999
```

GET `http://localhost:9999/api/store/keys` for listing keys.

GET `http://localhost:9999/api/store/<some id>` to get data associated to a key.

## Install applications in Spring DataFlow dashboard and build a data flow
First, make sure the artifacts are in your local maven repository:
```
mvn clean install
```

In the dashboard (http://localhost:9393/dashboard), import Applications using these properties:
```
source.api-poller=maven://fr.benjaminbillet:api-poller-source:jar:1.0.0
source.api-poller.metadata=maven://fr.benjaminbillet:api-poller-source:jar:metadata:1.0.0

processor.jolt-transformer=maven://fr.benjaminbillet:jolt-transformer:jar:1.0.0
processor.jolt-transformer.metadata=maven://fr.benjaminbillet:jolt-transformer:jar:metadata:1.0.0

sink.in-memory-store=maven://fr.benjaminbillet:in-memory-store:jar:1.0.0
sink.in-memory-store.metadata=maven://fr.benjaminbillet:in-memory-store:jar:metadata:1.0.0
```

Create a new Streams, using the text definition:
```
STREAM-1=api-poller | jolt-transformer | in-memory-store
```

Deploy the Streams you created with the free-text configuration:
```
app.api-poller.uri=https://random-data-api.com/api/users/random_user
app.api-poller.verb=GET
app.api-poller.spring.cloud.stream.poller.fixedDelay=5000
app.api-poller.spring.cloud.stream.bindings.output.destination=raw-users

app.jolt-transformer.jolt-specification=[{\"operation\":\"shift\",\"spec\":{\"uid\":\"userId\",\"first_name\":\"firstName\",\"last_name\":\"lastName\",\"email\":\"emailAddress\",\"address\":{\"coordinates\":{\"*\":\"location\"}}}},{\"operation\":\"default\",\"spec\":{\"type\":\"USER\"}},{\"operation\":\"sort\"}]
app.jolt-transformer.spring.cloud.stream.bindings.input.destination=raw-users
app.jolt-transformer.spring.cloud.stream.bindings.output.destination=users

app.in-memory-store.key-json-path=$.userId
app.in-memory-store.spring.cloud.stream.bindings.input.destination=users
```

Once deployed, you can go to Runtimes and get the port associated to the in-memory-store.
