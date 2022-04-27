# REST API

REST - Representational State Transfer

- Different HTTP methods - get, post, put and delete.
- JSON Data format
- Provider/Consumer

SOAP VS REST
- SOAP has only XML Data Format while in REST we can have either XML or JSON. 
- SOAP has specific structure to follow while REST does not have that. 
- REST is very easy to implement as compared to SOAP. (from Provider side)
- REST APIs are easy to consume as compared to SOAP. (from Consumer side)

JSON - JavaScript Object Notation
- Key/Value pair (similar to Map in Java)
- Type
  Key - String
	Value - can be of any data type

# Spring Boot

- less configuration
- stand-alone app (with embedded tomcat server)

## Annotations

@SpringBootApplication
@ComponentScan

**Controller layer**
@RestController
@RequestMapping
@GetMapping
@PostMapping
@RequestBody
@RequestParam - /delete?id=3
@PathVariable - /delete/3

**Service layer**
@Service

**Spring Data JPA**
@EntityScan
@Entity
@Table - specify table name
@Column - specify column name
@Id
@GeneratedValue
@Repository
@EnableJpaRepositories

**Lombok**
@Data
- @Getter
- @Setter
- @ToString
- @EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@NonNull

**Configuration**
```
@Value("${app.name}") //get data from application.properties
@Value("${app.name:Default Value}")
```

**Json**
@JsonIgnore
@JsonProperty - change the Key in the Json Response

**Validation**
@NotBlank
@NotNull
@Valid

## Controller

### Json Response
```
String -> String
Java Object -> Json
Map<String, Object> -> Json
```

## Runnable Jar Of Spring Boot App
Run as / Maven build... 
Goals: clean install

maven clean install
Jar will be generated in target/
and can be run in command line: java -jar

## Change Port
Two ways:
- set the port in configuration file.
	server.port
- set VM option: -Dserver.port (higher priority)
	UI: Run Configuration / Arguments / VM arguments: -Dserver.port=...
	Command line: `java -Dserver.port=8089 -jar demo.jar`

> Set port to 0 ? 
> Run on a random port every time.

# Spring Boot JPA

Terms:
- **JPA** Java Persistence API
	Hibernate is one of the implementations of JPA.
	**Hibernate** is a JPA implementation, while **Spring Data JPA** is a JPA data access abstraction. 
	Spring Data JPA cannot work without a JPA provider.
- **ORM** Object Relational Mapping
	Java Class <-- map --> Table in DB
- **JPQL** Java Persistence Query Language
	In JPQL, we refer to the Class instead of the Table.

Advantage:
Without database native sql, you can change the database in future.


Database Connection Configuration in yml 
(That's all we need to do to establish the connection to the DB)
...
spring.jpa.hibernate.ddl-auto=create-drop --Create Tables from Entities


Entity

Repository (DAO - Data Access Object)
- JpaRepository

Request/Response (DTO - Data Transfer Object)
- MapStruct
- Validation

FindById (default, do not need to write in the Repository)
FindByColumn
FindByColumn1AndColumn2
FindByColumn1OrColumn2
FindByColumnIn

Pagination
- PageNo
- PageSize (limit)

```sql
select * from customer limit 10 offset 20
```

```java
Pageable pageable = PageRequest.of(pageNo, pageSize);
Page<Customer> customerPage = this.customerRepository.findAll(pageable);
```


Fetching Type: 
- eager(default)
- lazy
	Lazy Loading with Hibernate Proxy Object(s).

TODO: Why doesn't Lazy Loading work in unit test?

JPA Relationships:
- unidirectional
- bidirectional

For a bidirectional relationship, we usually define:
- the owning side
	@JoinColumn
- inverse or the referencing side (or the non owning side)
	mappedBy

# flyway

# Spring-HATEOAS
HATEOAS (Hypertext as the Engine of Application State).
https://www.baeldung.com/spring-hateoas-tutorial

# Questions

**1 Plugin 'org.springframework.boot:spring-boot-maven-plugin:' not found.**

By adding the version of the plugin in the pom.xml:
```
<artifactId>spring-boot-maven-plugin</artifactId>
<version>${project.parent.version}</version>
```

## Git Basic

List All Branches
- To see local branches, run this command: git branch.
- To see remote branches, run this command: git branch -r.
- To see all local and remote branches, run this command: git branch -a.



## A. Install DEV Environment

- Brew
- Git
	brew install git
	credential.helper
	https://blog.favorstack.io/macOS/git-tools-credential-storage.html
- GitHub
  - Token-Based Authentication
    - SSH Key
    - Personal access tokens
			https://github.com/settings/tokens
		- OAuth
- JDK 11
	brew install java  // 17
	brew install java11
	Homebrew installed the JDK files and directories at /usr/local/Cellar/openjdk/, 
	and symbolic link at /usr/local/opt/openjdk
	https://mkyong.com/java/how-to-install-java-on-mac-osx/#switch-between-different-jdk-versions
	https://medium.com/@kirebyte/using-homebrew-to-install-java-jdk11-on-macos-2021-4a90aa276f1c
- Maven
	brew install maven
	The brew will install the Maven packages at /usr/local/Cellar/maven/${version}, 
	The brew also creates shortcut or symbolic links at /usr/local/opt/maven/
	https://mkyong.com/maven/install-maven-on-mac-osx/
- STS
	lombok
	java -jar lombok.jar
	Press the Specify Location button. Therefore proceed to find and select the SpringToolSuite4.ini
	https://projectlombok.org/setup/eclipse
	https://projectlombok.org/download

~
over.
