# Coding Challenge Backend


# Sobre o projeto - Configurações necessárias
Para melhorar a perfomance do consumo da api optei pelo cache, utilizando o Redis para melhor desempenho. 
No arquivo: aplication.properties, está definido :
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379

Portanto, vamos rodar o Redis com o Docker. Criando um container:
                            (port forwarding)   (versão redis)
docker run -it --name redis -p 6379:6379 redis: 5.0.3

Caso seja preferível rodar sem o redis, somente local, é necessário comentar ou excluir as propriedades definidas no aplication.properties.

Porta da aplicação:  8080
