# Usa imagen oficial de OpenJDK para Kotlin/Spring Boot
FROM openjdk:17-jdk-slim

# Ruta dentro del contenedor
WORKDIR /app

# Copia el jar generado en tu proyecto (ajusta el nombre y ruta correctos)
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usas (ejemplo 8081)
EXPOSE 8081

# Comando para arrancar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]
