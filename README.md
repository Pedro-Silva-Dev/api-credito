<p align="center">
<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge"/>
</p>

# Índice 

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Ferramentas utilizadas](#ferramentas-utilizadas)


## Descrição do projeto 
<p align="justify">
 Este projeto foi desenvolvido como um desafio técnico para uma API de consulta de créditos. A API permite consultas de forma eficiente e estruturada, seguindo boas práticas de desenvolvimento.  

Com essa solução, é possível realizar buscas por **número da NFS-e** ou **número de crédito**, obtendo todos os detalhes do crédito consultado.

O objetivo deste projeto é demonstrar habilidades técnicas em **desenvolvimento back-end**, **boas práticas de código**, **testes automatizados** e **uso de tecnologias modernas**.
</p>

## Funcionalidades

Este projeto de API foi desenvolvido para proporcionar uma experiência eficiente e segura na consulta de créditos, seguindo boas práticas de desenvolvimento. Entre os principais recursos, destacam-se:

:heavy_check_mark: **Consulta de créditos** por **número da NFS-e** ou **número de crédito**.

:heavy_check_mark: **Exibição dos detalhes completos do crédito consultado**.

:heavy_check_mark: **Arquitetura baseada no padrão MVC**, proporcionando organização e manutenibilidade do código.

:heavy_check_mark: **Testes unitários e de integração**, garantindo confiabilidade e estabilidade.

:heavy_check_mark: **Testes utilizando Testcontainers**, garantindo um ambiente de testes altamente próximo ao de produção.

:heavy_check_mark: **Configuração de CORS**, permitindo comunicação segura entre diferentes origens.

:heavy_check_mark: **Tratamento de erros** estruturado para uma API robusta e resiliente.

## Acesso ao projeto
1. Clone este repositório:
```
  git clone git@github.com:Pedro-Silva-Dev/api-credito.git
```
2. Acesse a pasta do projeto:
```
  cd api-credito
```
3. Der permissão ao script start.sh para executar todos os comandos do docker:
```
  chmod +x start.sh
```
4. Execute o comando do script start.sh para realizar todos os comandos necessários para deixar a aplicação pronta para uso:
```
  ./start.sh
```
5. Explore as funcionalidades do projeto acessando o projeto de front-end ou realizando consultas nos endpoints abaixo e descubra como a consulta de créditos pode ser rápida e intuitiva:
 
  Projeto Front-End:
  ```
     http://localhost:4200/https://github.com/Pedro-Silva-Dev/web-credito
  ```
  Consulta para obter os créditos pelo número NFS-e: GET
  ```
     http://localhost:8080/api/creditos/7891011
  ```
  Consulta para obter o crédito pelo número de crédito: GET
  ```
     http://localhost:8080/api/creditos/credito/123456
  ```

## Ferramentas utilizadas
Este projeto foi desenvolvido utilizando tecnologias modernas e boas práticas para garantir **desempenho, segurança e escalabilidade**. As principais tecnologias empregadas são:  

### 🔹 Backend  
- **Java 17** - Linguagem robusta e eficiente, garantindo alta performance e suporte a recursos modernos  
- **Spring Boot 3.4.3** - Framework que simplifica o desenvolvimento de aplicações Java, proporcionando uma configuração ágil e produtiva  
- **Spring Boot Security** - Implementação de segurança integrada para controle de autenticação e autorização  

### 🗄️ Banco de Dados  
- **PostgreSQL 17** - Banco de dados relacional escalável e confiável, garantindo consistência e integridade dos dados  
- **JPA (Jakarta Persistence API)** - Abstração ORM para facilitar a manipulação de dados e interações com o banco de dados  
- **Hibernate** - Implementação do JPA, permitindo o mapeamento objeto-relacional de forma eficiente  

### ⚡ Ferramentas e Bibliotecas  
- **Lombok** - Reduz a verbosidade do código, eliminando a necessidade de escrever getters, setters e construtores manualmente  
- **MapStruct** - Biblioteca para mapeamento automático de objetos DTOs, facilitando a conversão de entidades  
- **Testcontainers** - Ferramenta para execução de testes com um ambiente de banco de dados realista e próximo ao de produção  
- **JUnit** - Framework para criação de testes unitários e de integração, garantindo a qualidade do código  

Essas tecnologias foram escolhidas para proporcionar uma **solução robusta, segura e alinhada às melhores práticas de desenvolvimento de APIs modernas**.

Essas tecnologias foram escolhidas para garantir alto desempenho, modularidade e escalabilidade ao projeto.
