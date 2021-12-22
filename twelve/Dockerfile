FROM openjdk:8-alpine
LABEL seg=card
COPY ./cncamp-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
WORKDIR /app
ENTRYPOINT ["nohup", "java"]
CMD ["-server", "-Xmx1G", "-Xms1G", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=20", "-Djava.security.egd=file:/dev/./urandom", "-XX:InitiatingHeapOccupancyPercent=35", "-XX:+DisableExplicitGC", "-Djava.awt.headless=true", "-jar", "cncamp-0.0.1-SNAPSHOT.jar", ">/dev/null", "2>&1", "&"]