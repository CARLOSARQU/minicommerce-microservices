#🚀 E-commerce Microservices Architecture (Proof of Concept)Este proyecto es una implementación práctica de una arquitectura de **Microservicios** moderna, utilizando el ecosistema de **Spring Boot** para el backend y **Angular 17+** para el frontend. El objetivo principal fue desacoplar la lógica de negocio y aplicar patrones de comunicación asíncrona.

##🏗️ Arquitectura del SistemaEl sistema sigue una arquitectura distribuida donde cada servicio tiene una responsabilidad única:

* **API Gateway (Spring Cloud Gateway):** Punto de entrada único. Maneja el enrutamiento y resuelve problemas de CORS.
* **Discovery Server (Netflix Eureka):** Registro dinámico de servicios. Permite que los microservicios se encuentren entre sí sin conocer sus IPs/Puertos.
* **Product Service:** Gestiona el catálogo de productos (PostgreSQL).
* **Order Service:** Gestiona la creación de pedidos (PostgreSQL).
* **RabbitMQ (Event Bus):** Facilita la comunicación asíncrona. Cuando se crea un pedido, se emite un evento para que el inventario se actualice sin acoplar los servicios.

##🛠️ Tech Stack* **Backend:** Java 21, Spring Boot 3.4
* **Frontend:** Angular 17+ (Standalone Components, Signals)
* **Data:** PostgreSQL, Spring Data JPA
* **Messaging:** RabbitMQ (Event-Driven Architecture)
* **Infrastructure:** Docker & Docker Compose
* **Microservices Patterns:**
* Service Discovery (Eureka)
* API Gateway
* Circuit Breaker (Resilience4j - *Planned*)
* Distributed Tracing



##🔄 Flujo de Datos (Caso de uso: "Comprar Producto")1. **Cliente (Angular)** envía una solicitud `POST` al Gateway.
2. **Gateway** enruta la petición al **Order Service**.
3. **Order Service** guarda la orden en su base de datos y publica un evento `OrderPlacedEvent` en **RabbitMQ**.
4. **Order Service** responde inmediatamente al usuario (baja latencia).
5. **Product Service** escucha el evento asíncronamente y descuenta el stock en su propia base de datos.

##🐳 Cómo ejecutar (Docker)Todo el entorno backend está contenerizado.

1. Clonar el repositorio.
2. Ejecutar la infraestructura con Docker Compose:
```bash
docker-compose up -d --build

```


3. Acceder a los servicios:
* **Frontend (Angular):** `http://localhost:4200` (Requiere `ng serve`)
* **Eureka Dashboard:** `http://localhost:8761`
* **API Gateway:** `http://localhost:8080`