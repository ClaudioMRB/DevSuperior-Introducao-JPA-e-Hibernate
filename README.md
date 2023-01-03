# Introdução JPA e Hibernate (bônus Maven e MySQL) - Aulão #006
###### DevSuperior - sua carreira dev com fundamento de ensino superior

**Comunidade no Discord**:
https://discord.gg/SbjpsFv

Não perca as novidades:
- https://instagram.com/devsuperior.ig
- https://facebook.com/devsuperior.fb
- https://youtube.com/devsuperior
- https://twitter.com/devsuperior

Assista o vídeo desta aula:

[![Image](https://img.youtube.com/vi/CAP1IPgeJkw/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/CAP1IPgeJkw)

## Sumário
- [O que você vai aprender](#O-que-você-vai-aprender)
- [Pré-requisitos](#Pré-requisitos)
- [Visão geral sobre mapeamento objeto-relacional](#Visão-geral-sobre-mapeamento-objeto-relacional)
- [JPA](#JPA)
- [Criando uma aplicação simples](#Criando-uma-aplicação-simples)

## O que você vai aprender
- Visão geral sobre mapeamento objeto-relacional
- Introdução ao JPA - Java Persistence API

## Pré-requisitos

- Lógica de programação
- OO básica
- BD básico

## Visão geral sobre mapeamento objeto-relacional

![myImage](https://github.com/devsuperior/aulao006/raw/master/img-problema-orm.png)

### Outros problemas que devem ser tratados:
- Contexto de persistência (objetos que estão ou não atrelados a uma conexão em um dado momento)
- Mapa de identidade (cache de objetos já carregados)
- Carregamento tardio (lazy loading)
- Outros

## JPA

Java Persistence API (JPA) é a especificação padrão da plataforma Java EE (pacote javax.persistence) para mapeamento objeto-relacional e persistência de dados.

JPA é apenas uma especificação (JSR 338):
http://download.oracle.com/otn-pub/jcp/persistence-2_1-fr-eval-spec/JavaPersistence.pdf

Para trabalhar com JPA é preciso incluir no projeto uma implementação da API (ex: Hibernate).

Arquitetura de uma aplicação que utiliza JPA:

![myImage](https://github.com/devsuperior/aulao006/raw/master/img-arquitetura-jpa.png)

### Principais classes:

#### EntityManager
https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html

Um objeto EntityManager encapsula uma conexão com a base de dados e serve para efetuar operações de acesso a dados (inserção, remoção, deleção, atualização) em entidades (clientes, produtos, pedidos, etc.) por ele monitoradas em um mesmo contexto de persistência.

Escopo: tipicamente mantem-se uma instância única de EntityManager para cada thread do sistema (no caso de aplicações web, para cada requisição ao sistema). 

#### EntityManagerFactory
https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManagerFactory.html

Um objeto EntityManagerFactory é utilizado para instanciar objetos EntityManager.

Escopo: tipicamente mantem-se uma instância única de EntityManagerFactory para toda aplicação.

## Criando uma aplicação simples

![myImage](https://github.com/devsuperior/aulao006/raw/master/img-pessoa.png)



4) Incluindo JPA para persistir os objetos em banco de dados



### Passos

#### Crie uma base de dados MySQL vazia
- Instale o Xampp no seu computador
- Inicie o Apache e o MySQL
- No PhpMyAdmin, crie uma base de dados chamada "aulajpa"
- [Welcome to XAMPP](http://localhost/dashboard/)

#### Crie um novo projeto Maven
- File -> New -> Other -> Maven Project
- Create Simple Project -> Next
  - Group Id: com.educandoweb
  - Artifact Id: aulajpamaven
  -Finish

#### Copie as classes Programa e Pessoa para o novo projeto

```java
package dominio;

import java.io.Serializable;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String email;

	public Pessoa() {
	}

	public Pessoa(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}
```

```java
package aplicacao;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(1, "Carlos da Silva", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(2, "Joaquim Torres", "joaquim@gmail.com");
		Pessoa p3 = new Pessoa(3, "Ana Maria", "ana@gmail.com");

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}
}
```

#### Atualize o Maven do projeto para Java 11
- Edite o arquivo pom.xml
- Inclua o conteúdo abaixo
- Salve o projeto
- Botão direito no projeto -> Maven -> Update Project

```xml
<properties>
	<maven.compiler.source>11</maven.compiler.source>
	<maven.compiler.target>11</maven.compiler.target>
</properties>
```

#### Inclua as dependências Maven a serem baixadas:

```xml
<dependencies>
	<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.1.6.Final</version>
    <type>pom</type>
</dependency>


	<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.6.14.Final</version>
</dependency>


	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.31</version>
</dependency>

</dependencies>
```

#### Configure o JPA no seu projeto por meio do arquivo persistence.xml
- Crie uma pasta "META-INF" a partir da pasta "resources"
- Dentro da pasta META-INF crie um arquivo "persistence.xml"
- Conteúdo do arquivo persistence.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
    http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
	version="2.1">

    //PROPERTIES DA URL DE CONEXÃO COM O BANCO DE DADOS. 
	//"RESOURCE_LOCAL" EFETUA AS OPERAÇÕES MANUALMENTE
    //EM PROPERTIES NO CAMPO VALUE TROCAR O ENDEREÇO "LOCALHOST" PELO "IP" 
    //CASO O BANCO DE DADOS ESTEJA EM UM SERVIDOR NA INTERNET, MANTER "LOCALHOST"
    //SE O BANCO DE DADOS ESTIVER NA MAQUINA LOCAL.
    //NOME DO BANCO DE DADOS SEGUIDO DE "?" INICIA OS ATRIBUTOS
    //1° ATRIBUTO REFERENTE AO "SSL", CASO USE COLOCAR "TRUE" SE NÃO "FALSE"
    
    
	<persistence-unit name="exemplo-jpa" transaction-type="RESOURCE_LOCAL">
	<properties>
		<property name="javax.persistence.jdbc.url"
			value="jdbc:mysql://localhost/aulajpa?useSSL=false&amp;serverTimezone=UTC" />

		<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
		<property name="javax.persistence.jdbc.user" value="root" />
		<property name="javax.persistence.jdbc.password" value="" />

		<property name="hibernate.hbm2ddl.auto" value="update" />

		<!-- https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/dialect/package-summary.html -->
		<property name="hibernate.dialect" 	value="org.hibernate.dialect.MySQL8Dialect" />
	</properties>
	</persistence-unit>
</persistence>
```

#### Inclua os MAPEAMENTOS na classe de domínio:

```java
package dominio;

import (...)

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	(...)
```

#### Na classe "Programa" faça os testes (veja vídeo-aula).

FERRAMENTA DE MAPEAMENTO OBJETO RELACIONAL



1. Abra o Git Bash ou terminal na pasta onde está o seu projeto
2. Inicie a pasta como um repositório do Git através do comando:
   `git init`
3. Em seguida, adicione os arquivos de configuração para preparar o commit:
   `git add .`
4. *Opcional:* Adicione um arquivo readme caso não tenha iniciado o repositório com ele:
   `git add README.md`
5. Crie um novo commit para os arquivos que irá subir para o repositório:
   `git commit -m "first commit"`
6. Suba seus arquivos utilizando a URL gerada no passo 2 no seguinte comando:
   `git remote add origin URL-GERADA-PELO-PASSO-2-AQUI`
7. Autorize o upload com seu login e senha:
   `git push -u origin master`
