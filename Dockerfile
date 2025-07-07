FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.war
RUN apk upgrade --no-cache
RUN addgroup -S 1000 && adduser -S 1000 -G 1000
RUN mkdir -p /home/1000
COPY ${JAR_FILE} /home/1000/kps-public-image-hub-ui.war
RUN chown -R 1000:1000 /home/1000
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/home/1000/kps-public-image-hub-ui.war"]
