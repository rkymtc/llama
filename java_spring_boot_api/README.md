# Java Spring Boot API

## API Noktaları
- `GET /api/ping`
- `POST /api/chat`

## Çalıştırma

### Gereksinimler
- Java 17
- Maven

### Adımlar
```
cd java_spring_boot_api
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

## Kullanım
```
curl -X POST -H "Content-Type: application/json" -d '{"message": "LLama nedir?"}' http://localhost:8080/api/chat
``` 