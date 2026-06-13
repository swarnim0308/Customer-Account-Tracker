FROM eclipse-temurin:11-jre-alpine

# Set environment variables for security credentials (override at runtime)
ENV SECURITY_USERNAME=admin
ENV SECURITY_PASSWORD=changeme

# Copy WAR file to Tomcat's webapps directory
COPY target/CUSTACCTRACKER-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8088

# Start Tomcat
CMD ["catalina.sh", "run"]
