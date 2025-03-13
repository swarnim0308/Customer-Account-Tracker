# Customer Account Tracker (Spring MVC)

This project is a Spring MVC-based banking application that allows to manage customers and to perform transactional operations through a simple web interface.

## 🚀 **Project Overview**
- **Framework:** Spring MVC  
- **View Layer:** JSP  
- **Database:** H2 (In-Memory)  
- **Security:** Spring Security  
- **Container:** Docker with Apache Tomcat  
- **Port:** 8088 (mapped to container's 8080)  
---

### ✅ **Features:**
- **Insert a Customer** – Add a new customer record.  
- **Update Customer** – Modify existing customer details.  
- **Delete Customer** – Remove a customer record.  
- **Retrieve Customer By ID** – Fetch customer details using the customer ID.  
- **Make Transaction** – Perform transactions and update balances. 
---
## 🏗️ **Setup Instructions**

### ➡️ 3. **Build the WAR File**
Use Maven to build the project:
```bash
mvn clean package
```
## 🐳 **Docker Commands**

### ➡️ **Build Docker Image**
Use the following command to build the Docker image:
```bash
docker build -t spring-mvc-app .
```
### ➡️ **Run Docker Container**
```bash
docker run -d -p 8088:8080 spring-mvc-app
```
### ➡️ **Stop and Remove Docker Container**
To stop and remove the container:
```bash
docker stop <container_id>
docker rm <container_id>
```
### ➡️ **Access the Application**
- **App:** [App](http://localhost:8088/bank/)  


