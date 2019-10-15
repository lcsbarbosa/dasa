# Spring Data JPA com Hibernate usando MySql.

Este projeto foi feito para o teste da Dasa.

## Descrição

Projeto em Maven, para manutenção de Laboratórios e Exames com banco MySql rodando em container em servidor GCP(Google Cloud Plataform). 

Usando os seguintes endpoints, podem se obter as seguintes operações CRUD:


### Laboratório:
    GET:
    /rest/laboratorio/ - Retorna lista de todos Laboratóios ATIVOS criados no banco MySql / Tabela - laboratorio
    /rest/laboratorio/{name} - Retorna detalhes do dado na tabela Laboratóio que foi passado usando o Nome na URL
    /rest/laboratorio/id/{id} - Retorna detalhes do dado na tabela Laboratóio que foi passado usando o ID na URL
    
    Post:
    /rest/laboratorio/ - Cria Laboratório com os dados passados no Corpo da API
    
    Put:
    /rest/laboratorio/{id} - Realiza Update no Banco MySQL, na tabela laboratorio usando como Referencia o ID passado na URL
    
### Exames:

    GET:
    /rest/exame/ - Retorna lista de todos Exames ATIVOS criados no banco MySql Tabela - exame
    /rest/exame/{name} - Retorna detalhes do Exames que foi passado usando o Nome na URL
    /rest/exame/id/{id} - Retorna detalhes do Exames que foi passado usando o ID na URL
    
    Post:
    /rest/exame/ - Cria Exames com os dados passados no Corpo da API
    
    Put:
    /rest/exame/{id} - Realiza uma Atualização dos dados presentes na tabela exames no Banco MySQL, usando como Referencia o ID passado na URL
    
    Delete:
    /rest/exame/{id} - Deleta um dado no Banco MySQL, na tabela exames usando como Referencia o ID passado na URL

    
###	Associação:

    GET:
    /rest/associacao/ - Retorna lista de todos Associações criados no banco MySql Tabela - associacao
    /rest/associacao/{name} - Retorna detalhes do associacao que foi passado usando o Nome na URL
    /rest/associacao/id/{id} - Retorna detalhes do associacao que foi passado usando o ID na URL
    
    Post:
    /rest/associacao/ - Cria Associações com os dados passados no Corpo da API
    
    Put:
    /rest/associacao/{id} - Realiza uma Atualização dos dados presentes na tabela associacao no Banco MySQL, usando como Referencia o ID passado na URL




Bibliotecas usadas:

    Spring Boot
    Spring MVC (Spring Web)
    Spring Data JPA com Hibernate
    MySql

Ferramentas usadas:

    Git 2.23.0
    Eclipse
    MySql

Dependencias usadas:

    Spring Boot Data JPA
    Spring Boot Starter Web
    Spring Boot Starter
    Mysql Connector
    Spring Boot Devtools
    
Plugins usados:

    Spring Boot Maven Plugin
    Dockerfile Maven Plugin
    
    


## Docker Container


O projeto está utilizando Dockerfile Maven Plugin.

O mesmo está sendo configurado nos arquivos Dockerfile e Pom.xml.

Para criar a imagem do container, altere Repository no arquivo Pom e use Clean Install.

O deploy da imagem será realizado automaticamente GitHub.

### Imagem

Para executar a imagem no servidor, execute o seguinte comando:
		
    Executar:
    docker run -d -p8080:8080 --name dasa {usuario/repositorio:tag}
    
    Listar containers rodando:
    docker ps
    
    Parar Container:
    docker stop {container id}

    
# Rodar Projeto


Necessário para rodar o Projeto:
	
	Maven
	Docker
	Git	
	
Antes de rodar o projeto, serão necessário os seguintes ajustes: 

	Conexão com Banco de Dados:
		Arquivo: Application.proprieties
		Local: Resources
	Conexão GitHub:		
		Arquivo: Pom.xml
		Local: Raiz

### Comando de Compilação

	mvn clean install
	
### Acessos:

	Banco Mysql:
		34.95.172.172:3306
		senha: Kai9987321A*
		
	