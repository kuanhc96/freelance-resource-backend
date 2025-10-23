# freelance-resource-backend
## Profile information
### dev

- standalone application. Does not connect with any other microservices. used for local development
- Database: H2 (in-memory)
- no VM arguments needed

### test

- connects with other microservices (configserver, authserver, eurekaserver, rabbitmq) via localhost
- configurations will be read from configfiles repo: freelance-resource-backend-test
- Database: MySQL, port 3306
- rabbitMQ: port 5672
- `-Dspring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:9000/oauth2/jwks`

### qa

- connects with other microservices (configserver, authserver, eurekaserver, rabbitmq) through docker-compose