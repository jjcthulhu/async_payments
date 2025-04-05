# Fase de construção
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Instala o Maven wrapper se não existir
RUN test -f mvnw || (apt-get update && apt-get install -y maven && \
    mvn -N io.takari:maven:wrapper -Dmaven=3.8.6)

# Constroi o projeto
RUN ./mvnw clean package -DskipTests

# Fase de produção
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o JAR construído
COPY --from=builder /app/target/async-payments-*.jar /app/app.jar

# Configurações de tempo de execução
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Porta da aplicação
EXPOSE 8080

# Usuário não-root para segurança
RUN useradd -m myuser && chown -R myuser:myuser /app
USER myuser

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
