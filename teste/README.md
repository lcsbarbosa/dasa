# Spring Data JPA com Hibernate usando MySql.

Este projeto foi feito para o teste da Dasa.

## Descrição

Projeto em Maven, para manutenção de Laboratórios e Exames com banco MySql rodando em container em servidor GCP(Google Cloud Plataform). 

Usando os seguintes endpoints, podem se obter as seguintes operações CRUD:

### Laboratório:
    GET:
    /rest/laboratorio/ - Retorna lista de todos Laboratóios criados no banco MySql Tabela - laboratorio
    /rest/laboratorio/{name} - Retorna detalhes do Laboratóio que foi passado usando o Nome na URL
    /rest/laboratorio/id/{id} - Retorna detalhes do Laboratóio que foi passado usando o ID na URL
    
    Post:
    /rest/laboratorio/ - Cria Laboratório com os dados passados no Corpo da API
    
    Put:
    /rest/laboratorio/{id} - Realiza Update no Banco MySQL, na tabela laboratorio usando como Referencia o ID passado na URL
    
    Delete:
    /rest/laboratorio/{id} - Realiza Delete no Banco MySQL, na tabela laboratorio usando como Referencia o ID passado na URL
    
### Exames:

    GET:
    /rest/exame/ - Retorna lista de todos Exames criados no banco MySql Tabela - exame
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
    
    Delete:
    /rest/associacao/{id} - Deleta um dado no Banco MySQL, na tabela associacao usando como Referencia o ID passado na URL
    
Bibliotecas usadas:

    Spring Boot
    Spring MVC (Spring Web)
    Spring Data JPA com Hibernate
    MySql

Ferramentas usadas:

    Git 2.23.0
    Eclipse
    MySql


