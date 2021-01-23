# слои общие для всех spring-boot контейнеров
FROM openjdk:8u191-jre-alpine
RUN adduser -S -D -g '' spring-boot-app-user \
    && apk add --no-cache \
        curl \
    && apk add --no-cache --repository http://dl-cdn.alpinelinux.org/alpine/edge/community/ \
        jattach \
    && apk add --no-cache \
        fontconfig \
    && apk add --no-cache \
            ttf-dejavu \
    && echo done
USER spring-boot-app-user
WORKDIR /opt/spring-boot-app
## Дополнительнные опции java передаем в JAVA_TOOL_OPTIONS
ENTRYPOINT [\
    "java"\
    , "-jar", "/opt/spring-boot-app/spring-boot-app.jar"\
]

# слой изменяющийся при каждой сборке
COPY *.jar /opt/spring-boot-app/spring-boot-app.jar
