# Music Microservices

### Desenvolvedores:
 - [Carlos Rodrigues](http://github.com/CarlosAsrc)
 - [Dione Adam](http://github.com/@DioneAdam)


#### Link da apresentação
 - https://youtu.be/dSulyR3VOMw

## Eureka Server
Para Rodar os 3 microserviços, é preciso estar com o Eureka Server up no Tomcat. Para isso:
 - Copie o arquivo eureka.war para a pasta webapps do tomcat.
 - Rode o comando "./catalina.sh start" para subir o servidor tomcat.
 - Navegue para http://localhost:8080/eureka para visualizar o dashboard do Eureka.

## Base de dados
 - Para alimentar a base de dados, abra o mysql e rode os scripts sql que estão na pasta sql. Eles irão criar dois schemas, um para consumo do song-service, e outro para o playlist-service.

## Microserviços
 - Para rodar os 3 microserviços, existe um arquivo "run.sh" com os comandos necessários, exibindo a saída do console para cada um dos serviços em arquivos .out correspondentes.
Alternativamente, para ter controle via terminal sobre os serviços:
	Para cada microserviço, execute os seguintes comandos na raíz do projeto:
		gradle wrapper
		gradle clean build
		gradle run

 - Feito isso, os serviços estarão disponíveis nos seguintes endereços:
		song-service 		http://localhost:8091
		playlist-service 	http://localhost:8092
		application-service 	http://localhost:8093

- Endpoint para o app-service:
		Consultar músicas de uma determinada playlist > GET http://localhost:8093/playlist/id
		Sendo "id" o identificador da playlist.


