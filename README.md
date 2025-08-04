# 📦 Projeto JPA com Hibernate - CRUD Pessoa

Este projeto demonstra a utilização do **JPA (Java Persistence API)** com **Hibernate ORM** e **MySQL** para realizar operações CRUD (Create, Read, Update, Delete) em uma entidade simples chamada `Pessoa`.

O projeto é estruturado de forma didática para apresentar como o JPA gerencia o ciclo de vida das entidades através do **EntityManager**.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia                        | Descrição                                                           |
| --------------------------------- | ------------------------------------------------------------------- |
| **Java 17**                       | Linguagem de programação principal do projeto.                      |
| **JPA (Jakarta Persistence API)** | API de persistência para mapeamento objeto-relacional (ORM).        |
| **Hibernate**                     | Implementação do JPA usada para gerenciar a persistência dos dados. |
| **MySQL Connector/J**             | Driver JDBC para conexão com banco de dados MySQL.                  |
| **Maven**                         | Ferramenta de automação e gerenciamento de dependências do projeto. |

---

## 🗂️ Estrutura do Projeto

```
src/
 └── main/
     └── java/
         ├── aplicativo/
         │    └── Main.java
         ├── repository/  <-- OU dao/ ou persistence/
         │    └── CrudPessoa.java
         └── dominio/
              └── Pessoa.java
 └── resources/
     └── META-INF/
          └── persistence.xml
pom.xml
```

---

## 📄 Descrição dos Arquivos

### **1. Main.java (Pacote: aplicativo)**

Classe principal do projeto. Responsável por:

* Inicializar o **EntityManagerFactory** e o **EntityManager**.
* Criar instâncias da entidade `Pessoa`.
* Executar operações de **Inserção**, **Busca**, **Remoção** e **Atualização** através dos métodos da classe `CrudPessoa`.
* Controlar a transação (begin/commit).

### **2. CrudPessoa.java (Pacote: data)**

Classe responsável por encapsular as operações CRUD na entidade `Pessoa`:

* `InsertPessoa`: Insere uma nova pessoa.
* `findByIDPessoa`: Busca uma pessoa pelo ID.
* `removePessoa`: Remove uma pessoa do banco.
* `updatePessoa`: Atualiza nome e email de uma pessoa.

### **3. Pessoa.java (Pacote: dominio)**

Classe de entidade mapeada para a tabela **pessoa** no banco de dados.

* Anotações JPA: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`.
* Possui campos: `id`, `nome`, `email`.
* Métodos: Getters, Setters, hashCode, equals e toString.

### **4. pom.xml**

Arquivo de configuração do Maven. Gerencia as dependências do projeto:

* **hibernate-core**: Biblioteca principal do Hibernate.
* **hibernate-entitymanager**: Gerenciador de entidades do Hibernate.
* **mysql-connector-j**: Driver JDBC para conexão com o banco MySQL.
* Configuração para Java 17.

---

## 🔌 Configuração do Banco de Dados (MySQL)

Você precisa criar um banco de dados no MySQL para conectar ao projeto.

Exemplo de comandos SQL:

```sql
CREATE DATABASE jpateste;

USE jpateste;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomePessoa VARCHAR(255),
    emailPessoa VARCHAR(255)
);
```

---

## 🗂️ persistence.xml (a ser criado em src/main/resources/META-INF/)

```xml
<persistence xmlns="http://jakarta.ee/xml/ns/persistence" version="3.0">
    <persistence-unit name="percistence-jpa-teste">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>dominio.Pessoa</class>
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/jpateste"/>
            <property name="jakarta.persistence.jdbc.user" value="root"/>
            <property name="jakarta.persistence.jdbc.password" value="sua_senha"/>

            <!-- Configurações do Hibernate -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

---

## 🚀 Como Executar o Projeto

1. **Clone o projeto**:

   ```bash
   git clone https://seurepositorio.git
   cd aulajpamaven
   ```

2. **Configure o `persistence.xml`**:

   * Altere o `jdbc.url`, `user` e `password` conforme seu MySQL.

3. **Compile e execute o projeto**:

   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="aplicativo.Main"
   ```

4. **Alterar o modo de operação**:

   * No arquivo `Main.java`, altere o valor da variável `num`:

     * **num = 1** → Inserir Pessoa
     * **num = 2** → Buscar Pessoa pelo ID
     * **num = 3** → Remover Pessoa pelo ID
     * **num = 4** → Atualizar Pessoa pelo ID

---

## 💡 Observações Importantes

* O Hibernate controla as operações de persistência através do **EntityManager**.
* O `em.persist()` insere uma nova entidade.
* O `em.find()` recupera uma entidade do banco.
* O `em.remove()` exclui uma entidade.
* O **update** é feito apenas modificando a entidade carregada com `find()` dentro de uma transação.
* O `persistence.xml` é obrigatório para o JPA reconhecer a configuração de persistência.
* A versão do **Hibernate EntityManager** (5.x) foi mantida para compatibilidade, pois a partir do Hibernate 6 a integração JPA é via **hibernate-core**.

---

## 📚 Referências

* [Documentação oficial do Hibernate ORM](https://hibernate.org/orm/documentation/)
* [Documentação JPA (Jakarta Persistence API)](https://jakarta.ee/specifications/persistence/)
* [Maven Central Repository](https://mvnrepository.com/)


