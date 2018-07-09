# Contact List Service
___

### O que é?
Projeto de um serviço via API REST, que poisibilita a consulta, criação, edição e deleção de dados referentes a contatos de uma pessoa (email, telefone e WhatsApp).


### Como funnciona?
Baixe o projeto, e inclua em seu "workspace" de sua IDE de preferêcia.

Para utilziar o mesmo, por se tratar de um projeto em Spring Boot, podemos simplesmente rodar a ["main"](https://github.com/attnk/kairos_test_services/blob/master/contact-list-service/src/main/java/contactlistservice/ContactListServiceApplication.java) que será executado a aplicação e a mesma estará pronta para ser usada localmennte.

Após a aplicação estar rodando em sua máquina local, basta acessar o serviço através de um REST CLIENT ou pelo próprio navegador usanndo como prefixo: `http://localhost:8080/`

**OBS:** 
* Este projeto está configurado para utilizar um banco em memória H2, caso queira ser incluído algum dado manualmennte a base exsite o arquivo [data.sql](https://github.com/attnk/kairos_test_services/blob/master/contact-list-service/src/main/resources/data.sql) com um `INSERT` de exemplo.
* Para acessar uma interface para rodar queries, também está connfigurado no projeto o console do H2 que pode ser acessado por: 
```
URL: http://localhost:8080/h2-console/

No formulário será necessário informar os seguintes dados:
- JDBC URL: jdbc:h2:mem:testdb
- username: sa
- password: sa
```

### Serviços disponíveis

* GET de um PERSON:  `http://localhost:8080/persons/{personId}`, sendo `personId` um valor numérico que represente o ID do dado em questão
* GET de uma lista de CONTACTS: `http://localhost:8080/persons/{personId}/conntats`
* GET de um dado de CONTACT: `http://localhost:8080/contact/{contactId}`, sendo `contactId` um valor numérico que represente o ID do dado em questão
* POST de um novo PERSON: `http://localhost:8080/persons/` 
* POST de um novo CONTACT: `http://localhost:8080/persons/{personId}/conntats`
* PUT de atualização de dados de um PERSON: `http://localhost:8080/persons/`
* PUT de atualização de dados de um CONTACT: `http://localhost:8080/contacts/`
* DELETE de um PERSON: `http://localhost:8080/persons/{personId}`
* DELETE de um CONTACT: `http://localhost:8080/contact/{contactId}`

### Exemplo do modelo JSON
```json
{

    "id": 1,
    "name": "teste",
    "createdAt": "2018-07-09 06:09:21",
    "modifiedAt": "2018-07-09 06:09:21",
    "contacts": [
        {
            "type": "EMAIL",
            "value": "teste@teste.com",
            "createdAt": "2018-07-09 06:09:21",
            "modifiedAt": "2018-07-09 06:09:21",
            "id": 1
        },
        {
            "type": "1",
            "value": "011 5532-5123",
            "createdAt": "2018-07-09 06:09:21",
            "modifiedAt": "2018-07-09 06:09:21",
            "id": 2
        },
        {
            "type": "WHATS_APP",
            "value": "011 99864-5632",
            "createdAt": "2018-07-09 06:09:21",
            "modifiedAt": "2018-07-09 06:09:21",
            "id": 3
        }
    ]

}
```
