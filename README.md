# springboot-microservices
Sample Project show how use springBoot and springCloud to build App base on Microservice architectures


ex from :https://github.com/vinsguru/vinsguru-blog-code-samples/tree/master/architectural-pattern/saga-orchestration

# kafka
### Run compose with env file:
~~~
    docker compose --env-file ./.env up
~~~

### Create Topic:
~~~
1) login in kafka image: docker exec -it kafka bash
2) create topic: Go to './opt/bitnami/kafka/bin/' and type ./kafka-topics.sh --bootstrap-server kafka:9092 --create --topic sampletopic --partitions 3 --replication-factor 1
3) check topic created: Go to './opt/bitnami/kafka/bin/' and type ./kafka-topics.sh --bootstrap-server kafka:9092 --list
~~~