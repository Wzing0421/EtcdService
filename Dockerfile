FROM  williamyeh/java8:latest
MAINTAINER david "wzning@pku.edu.cn"

RUN mkdir /usr/src/EtcdService
COPY ./ /usr/src/EtcdService

WORKDIR /usr/src/EtcdService
ENTRYPOINT ["java","-jar","EtcdService.jar"]