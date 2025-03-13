FROM tomcat:9.0-jdk11

# Copy WAR file to Tomcat's webapps directory
COPY target/CUSTACCTRACKER-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
