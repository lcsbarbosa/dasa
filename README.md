#Spring Data JPA com Hibernate usando MySql.

Este projeto foi feito para o teste da Dasa.

##Descri��o

Projeto em Maven, para manuten��o de Laborat�rios e Exames com banco MySql rodando em container em servidor GCP(Google Cloud Plataform). 

Usando os seguintes endpoints, podem se obter as seguintes opera��es CRUD:

###Laborat�rio:
    GET:
    /rest/lab/ - Retorna lista de todos Laborat�rios criados no banco MySql Tabela - laboratorio
    /rest/lab/{name} - Retorna detalhes do Laborat�rio que foi passado usando o Nome na URL
    /rest/lab/id/{id} - Retorna detalhes do Laborat�rio que foi passado usando o ID na URL
    
    Post:
    /rest/lab/ - Cria Laborat�rio com os dados passados no Corpo da API
    
    Put:
    /rest/lab/{id} - Realiza Update no Banco MySQL, na tabela laborat�rio usando como Referencia o ID passado na URL
    
    Delete:
    /rest/lab/{id} - Realiza Delete no Banco MySQL, na tabela laborat�rio usando como Referencia o ID passado na URL
    
###Exames:

    GET:
    /rest/lab/ - Retorna lista de todos Exames criados no banco MySql Tabela - exame
    /rest/lab/{name} - Retorna detalhes do Exames que foi passado usando o Nome na URL
    /rest/lab/id/{id} - Retorna detalhes do Exames que foi passado usando o ID na URL
    
    Post:
    /rest/lab/ - Cria Exames com os dados passados no Corpo da API
    
    Put:
    /rest/lab/{id} - Realiza uma Atualiza��o dos dados presentes na tabela exames no Banco MySQL, usando como Referencia o ID passado na URL
    
    Delete:
    /rest/lab/{id} - Deleta um dado no Banco MySQL, na tabela exames usando como Referencia o ID passado na URL

Bibliotecas usadas:

    Spring Boot
    Spring MVC (Spring Web)
    Spring Data JPA com Hibernate
    MySql

Ferramentas usadas:

    Git 2.23.0
    Eclipse
    MySql


