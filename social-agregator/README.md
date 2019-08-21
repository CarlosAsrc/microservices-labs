# Social Agregator

## Microserviços Springboot com Hystrix

4 arquivos se encontram na raíz do projeto, os 3 microserviços: GithubMicroservice, TwitterMicroservice e Agregator, e o docker-compose.yml para orquestração dos containers. Para cada microserviço existe um Dockerfile de configuração.

Para subir os 3 microserviços, siga os passos a seguir.

- É necessário configurar as credenciais de acesso à API do Twitter, em `./TwitterMicroservice/src/main/resources/twitter.properties`.

- Inicialmente, execute o comando "gradle clean build" na raíz do projeto de cada microserviço para criar o build e disponibilizar o arquivo .jar para ser utilizado pelos containers.

- Na raíz do arquivo `docker-compose.yml`, execute `sudo docker-compose up` para executar as instruçoes do arquivo de orquestração dos containers. Aguarde verificando os logs até que os microserviços estejam disponíveis.

- Navegue para http://localhost:8080 e verá o serviço disponível com a explicação dos endpoints disponíveis, como a que se segue:
	
	- Requisitar quantidade de respositórios e de tweets de um usuário: 
		GET http://localhost:8080/github/githubUserName/twitter/twitterUserName

	- Requisitar quantidade de tweets de um usuário: 
		GET http://localhost:8080/twitter/userName

	- Requisitar quantidade de respositórios de um usuário: 
		GET http://localhost:8080/github/userName