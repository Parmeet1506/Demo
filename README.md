# Demo REST API - Spring Boot

A Spring Boot REST API application for managing the DEMO table in Oracle Database 19c Enterprise Edition.

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Oracle Database 19c Enterprise Edition (running locally or accessible via network)
- Oracle JDBC driver compatible with your database version

## Database Configuration

**Database Details:**
- Host: localhost
- Port: 1521
- SID: orcl
- Schema: PARMEET
- Username: PARMEET
- Password: OracleDB

**Table Structure:**
```sql
CREATE TABLE "PARMEET"."DEMO" (
    "ID" NUMBER PRIMARY KEY,
    "NAME" VARCHAR2(100 BYTE)
);
```

## Project Structure

```
src/main/java/com/demo/
├── DemoApplication.java          # Main Spring Boot Application
├── controller/
│   └── DemoController.java       # REST API Endpoints
├── service/
│   └── DemoService.java          # Business Logic
├── repository/
│   └── DemoRepository.java       # Data Access Layer
├── model/
│   └── Demo.java                 # Entity Model
└── exception/
    └── GlobalExceptionHandler.java # Exception Handling

src/main/resources/
└── application.properties        # Configuration
```

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Parmeet1506/Demo.git
   cd Demo
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. PUT - Insert/Update Data

**Endpoint:** `PUT /api/demo`

**Request Body:**
```json
{
  "id": 1,
  "name": "John Doe"
}
```

**Response (Success - 200 OK):**
```json
{
  "id": 1,
  "name": "John Doe"
}
```

**Response (Bad Request - 400):**
```
ID is required
```

**cURL Example:**
```bash
curl -X PUT http://localhost:8080/api/demo \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "John Doe"}'
```

---

### 2. GET - Retrieve Details

**Endpoint:** `GET /api/demo/{id}`

**Path Parameters:**
- `id` (Long): The ID of the record to retrieve

**Response (Success - 200 OK):**
```json
{
  "id": 1,
  "name": "John Doe"
}
```

**Response (Not Found - 404):**
```
HTTP 404 Not Found
```

**cURL Example:**
```bash
curl -X GET http://localhost:8080/api/demo/1 \
  -H "Accept: application/json"
```

---

### 3. DELETE - Delete Record

**Endpoint:** `DELETE /api/demo/{id}`

**Path Parameters:**
- `id` (Long): The ID of the record to delete

**Response (Success - 200 OK):**
```json
"Record with ID 1 deleted successfully."
```

**Response (Not Found - 404):**
```
HTTP 404 Not Found
```

**cURL Example:**
```bash
curl -X DELETE http://localhost:8080/api/demo/1
```

---

## Testing the API

### Using Postman

1. **PUT Request:**
   - URL: `http://localhost:8080/api/demo`
   - Method: PUT
   - Headers: `Content-Type: application/json`
   - Body (raw): `{"id": 1, "name": "John Doe"}`

2. **GET Request:**
   - URL: `http://localhost:8080/api/demo/1`
   - Method: GET

3. **DELETE Request:**
   - URL: `http://localhost:8080/api/demo/1`
   - Method: DELETE

### Using cURL

```bash
# Insert/Update
curl -X PUT http://localhost:8080/api/demo \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "John Doe"}'

# Get
curl -X GET http://localhost:8080/api/demo/1

# Delete
curl -X DELETE http://localhost:8080/api/demo/1
```

### Using HTTPie

```bash
# Insert/Update
http PUT http://localhost:8080/api/demo id:=1 name="John Doe"

# Get
http GET http://localhost:8080/api/demo/1

# Delete
http DELETE http://localhost:8080/api/demo/1
```

## Configuration Details

### application.properties

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Oracle Database
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/orcl
spring.datasource.username=PARMEET
spring.datasource.password=OracleDB
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=validate
```

## Troubleshooting

### Connection Issues

1. **"Connection refused" error:**
   - Ensure Oracle Database is running
   - Check if the hostname, port, and SID are correct
   - Verify network connectivity to the database server
   - Test connection: `sqlplus PARMEET/OracleDB@localhost:1521/orcl`

2. **"ORA-01017: invalid username/password" error:**
   - Verify credentials in `application.properties`
   - Ensure the user has proper permissions on the DEMO table
   - Reset password if necessary

3. **"ORA-00942: table or view does not exist" error:**
   - Ensure the DEMO table exists in the PARMEET schema
   - Check that you're connecting to the correct schema
   - Verify table creation: `SELECT * FROM PARMEET.DEMO;`

### Build Issues

1. **Maven build fails:**
   ```bash
   mvn clean
   mvn dependency:resolve
   java -version  # Check Java version (should be 11+)
   ```

2. **Oracle JDBC driver issues:**
   - Ensure ojdbc11 is in the local Maven repository
   - Download from: https://www.oracle.com/database/technologies/jdbc-ucp-19c-downloads.html

### Runtime Issues

1. **Port already in use:**
   - Change port in `application.properties`: `server.port=8081`
   - Or kill the process: `lsof -i :8080` (macOS/Linux)

2. **Application won't start:**
   - Check logs for errors
   - Verify all environment variables are set correctly
   - Enable debug logging: `logging.level.root=DEBUG`

## Logging

Logs are available in the console output. Configure logging levels in `application.properties`:

```properties
logging.level.com.demo=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Performance Tuning

- Connection pooling is configured automatically by Spring Boot
- Default connection pool size: 10
- For production, configure additional settings in `application.properties`:

```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

## Dependencies

- **Spring Boot 2.7.14** - Web framework
- **Spring Data JPA 2.7.14** - ORM framework
- **Hibernate 5.6.x** - JPA implementation
- **Oracle JDBC 21.11.0.0** - Database driver
- **Lombok** - Reduce boilerplate code

## License

MIT

## Support

For issues or questions, please open an issue in the repository.
