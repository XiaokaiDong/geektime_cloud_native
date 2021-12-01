FROM java:8
LABEL seg=card
COPY ./cardbill-1.0.jar /app/
EXPOSE 9090
WORKDIR /app
ENTRYPOINT ["nohup", "java"]
CMD ["-server", "-Xmx1G", "-Xms1G", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=20", "-Djava.security.egd=file:/dev/./urandom", "-XX:InitiatingHeapOccupancyPercent=35", "-XX:+DisableExplicitGC", "-Djava.awt.headless=true", "-jar", "cardbill-1.0.jar", ">/dev/null", "2>&1", "&"]
