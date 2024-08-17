# Usa una imagen base de OpenJDK
FROM openjdk:18-oraclelinux7

# Establece el directorio de trabajo en /app
WORKDIR /app

RUN yum install -y iputils
RUN yum install -y nano

# Crea el directorio /var/logs/ y establece permisos
RUN mkdir -p /var/logs/ralfit && chmod -R 755 /var/logs/ralfit

RUN echo "2024-07-26 12:00:00,000 [main] INFO  ExampleLogger - This is an example log message." > /var/logs/ralfit/app.log \
 && echo "2024-07-26 12:00:01,000 [main] DEBUG ExampleLogger - Debugging example log message." >> /var/logs/ralfit/app.log \
 && echo "2024-07-26 12:00:02,000 [main] ERROR ExampleLogger - Example error log message." >> /var/logs/ralfit/app.log

# Copia el archivo JAR generado por Maven en el contenedor
COPY target/personaltraining-0.0.1-SNAPSHOT.jar /app/personal-training.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/personal-training.jar"]
