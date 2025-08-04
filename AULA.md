1. **Conceitos (JPA, Hibernate, DAO/Repository, EntityManager)**
2. **Estrutura de pastas e arquivos**
3. **Explicação do fluxo do CRUD (Inserir, Buscar, Remover, Atualizar)**
4. **Execução do projeto (Maven + MySQL)**
5. **Exercícios Práticos**

---

# 📝 Aula Completa: CRUD com JPA, Hibernate e MySQL

## 🎯 Objetivo

Nesta aula, você vai aprender a criar uma aplicação Java simples utilizando **JPA com Hibernate** para persistência de dados em um banco de dados **MySQL**, organizando o código em camadas:

* **Aplicativo (Main)**
* **Repository (DAO)**
* **Domínio (Entidade Pessoa)**

---

## 📚 Tecnologias Usadas

| Tecnologia                        | Descrição                               |
| --------------------------------- | --------------------------------------- |
| **Java 17**                       | Linguagem de Programação                |
| **Maven**                         | Gerenciador de dependências e build     |
| **JPA (Jakarta Persistence API)** | Abstração ORM para manipulação de banco |
| **Hibernate**                     | Implementação do JPA                    |
| **MySQL**                         | Banco de dados relacional               |

---

## 🗂️ Estrutura do Projeto

```
src/
 └── main/
     └── java/
         ├── aplicativo/
         │    └── Main.java
         ├── repository/
         │    └── CrudPessoa.java
         └── dominio/
              └── Pessoa.java
 └── resources/
     └── META-INF/
          └── persistence.xml
pom.xml
```

---

## 🧩 Conceitos Importantes

### 🔹 **JPA (Java Persistence API)**

* API padrão do Java para mapeamento objeto-relacional (ORM).
* Permite manipular tabelas do banco de dados como se fossem objetos Java.

### 🔹 **Hibernate**

* Implementação do JPA.
* Responsável por transformar objetos em registros no banco de dados (e vice-versa).

### 🔹 **EntityManager**

* Interface principal do JPA para gerenciar as entidades (inserir, buscar, atualizar, deletar).

### 🔹 **DAO/Repository**

* Padrão de projeto para separar a camada de acesso a dados.
* No nosso projeto: `CrudPessoa.java` será o DAO (Repository).

---

## 🛠️ Arquivos do Projeto

### 1. **Pessoa.java (Entidade)**

```java
package dominio;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nomePessoa")
    private String nome;

    @Column(name="emailPessoa")
    private String email;

    // Getters, Setters, Construtores, toString, equals, hashCode...
}
```

> **Responsabilidade:** Mapeia a entidade "Pessoa" para a tabela do banco.

---

### 2. **CrudPessoa.java (Repository/DAO)**

```java
package repository;

import javax.persistence.EntityManager;
import dominio.Pessoa;

public class CrudPessoa {

    public static void InsertPessoa(EntityManager em, Pessoa p) {
        em.persist(p);
    }

    public static Pessoa findByIDPessoa(EntityManager em, int id) {
        return em.find(Pessoa.class, id);
    }

    public static void removePessoa(EntityManager em, int id) {
        Pessoa p = em.find(Pessoa.class, id);
        em.remove(p);
    }

    public static Pessoa updatePessoa(EntityManager em, int id, String nome, String email) {
        Pessoa p = em.find(Pessoa.class, id);
        p.setNome(nome);
        p.setEmail(email);
        return p;
    }
}
```

> **Responsabilidade:** Operações de CRUD na tabela `pessoa` através do `EntityManager`.

---

### 3. **Main.java (Aplicativo)**

```java
package aplicativo;

import javax.persistence.*;
import dominio.Pessoa;
import repository.CrudPessoa;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("percistence-jpa-teste");
        EntityManager em = emf.createEntityManager();

        Pessoa p1 = new Pessoa(null, "jota", "jota@gmail.com");
        Pessoa p2 = new Pessoa(null, "ana", "ana@gmail.com");

        int num = 1; // Altere aqui (1-inserir, 2-buscar, 3-remover, 4-atualizar)

        if (num == 1) {
            em.getTransaction().begin();
            CrudPessoa.InsertPessoa(em, p1);
            em.getTransaction().commit();
        }

        if (num == 2) {
            em.getTransaction().begin();
            System.out.println(CrudPessoa.findByIDPessoa(em, 1));
            em.getTransaction().commit();
        }

        if (num == 3) {
            em.getTransaction().begin();
            CrudPessoa.removePessoa(em, 2);
            em.getTransaction().commit();
        }

        if (num == 4) {
            em.getTransaction().begin();
            Pessoa updated = CrudPessoa.updatePessoa(em, 1, "Manuela", "manu@gmail.com");
            em.getTransaction().commit();
            System.out.println(updated);
        }

        em.close();
        emf.close();
    }
}
```

> **Responsabilidade:** Controla a execução do CRUD chamando o `CrudPessoa` conforme a operação desejada.

---

### 4. **persistence.xml**

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
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

> **Responsabilidade:** Configura a conexão com o banco e as propriedades do Hibernate.

---

### 5. **pom.xml**

Dependências:

* **Hibernate Core**
* **Hibernate EntityManager**
* **MySQL Connector/J**

---

## ⚙️ Executando o Projeto (Passo a Passo)

1. **Criar Banco de Dados MySQL:**

   ```sql
   CREATE DATABASE jpateste;
   USE jpateste;
   CREATE TABLE pessoa (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nomePessoa VARCHAR(255),
       emailPessoa VARCHAR(255)
   );
   ```

2. **Configurar o `persistence.xml`:**

   * Alterar URL, usuário e senha conforme seu MySQL.

3. **Build e Run com Maven:**

   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="aplicativo.Main"
   ```

4. **Alterar Operação:**
   No arquivo `Main.java`, altere a variável `num` para:

   * `1` → Inserir Pessoa
   * `2` → Buscar Pessoa pelo ID
   * `3` → Remover Pessoa pelo ID
   * `4` → Atualizar Pessoa pelo ID

---

## 📊 Diagrama da Arquitetura (Simplificado)

```
+-------------+
|  Aplicativo  |  -> Main.java
+------+------+                (Chama Repository)
       |
       v
+-------------+
|  Repository  |  -> CrudPessoa.java (DAO)
+------+------+                (Acessa Entidade via EntityManager)
       |
       v
+-------------+
|   Dominio    |  -> Pessoa.java (Entidade)
+-------------+
       |
       v
+-------------+
| Banco Dados  |  -> Tabela: pessoa (MySQL)
+-------------+
```

---

## 🏋️ Exercícios Práticos

1. **Adicionar um método que liste todas as pessoas do banco.**
2. **Criar uma função no DAO que busca pessoas pelo nome.**
3. **Alterar o `persistence.xml` para criar as tabelas automaticamente (alterar hbm2ddl.auto para `create-drop`).**
4. **Fazer a mesma estrutura usando banco H2 em memória.**

