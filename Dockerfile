# ==========================================
# Etapa 1: Construcción (Build)
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar todo el código fuente al contenedor
COPY . .
# 1. Limpiar los saltos de línea de Windows a Linux
RUN sed -i 's/\r$//' gradlew

# Dar permisos de ejecución al wrapper de Gradle y compilar
RUN chmod +x ./gradlew
# Compilamos saltándonos las pruebas para que el build sea más rápido
RUN ./gradlew build -x test

# ==========================================
# Etapa 2: Ejecución (Runtime)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos solo el archivo .jar generado en la etapa anterior
# Nota: Spring Boot suele generar el jar ejecutable en /build/libs/
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

# Exponemos el puerto por el que escucha tu backend
EXPOSE 8080

# Comando para ejecutar la aplicación (Se puede Poner perfil de producción)
ENTRYPOINT ["java", "-jar", "app.jar"]