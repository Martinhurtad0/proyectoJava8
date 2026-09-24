# Proyecto Java 8 + Servlets + PostgreSQL

Aplicación web Java (Servlets + JSP) con PostgreSQL y Tomcat 7 embebido vía Maven.

## Requisitos

- **JDK 8** (obligatorio para el plugin de Tomcat 7)
- **Maven 3.6+**
- **PostgreSQL 12+**

Verifica:
```bash
java -version    # debe decir 1.8.x
mvn -version     # Java version: 1.8.0_xxx
Comandos Maven
Comando	Acción
mvn clean	Limpia target/
mvn compile	Compila
mvn package	Genera WAR
mvn tomcat7:run	Levanta Tomcat 7 en 8080
mvn -version	Ver JDK activo