#基础镜像
FROM java:8-jre
#SpringBoot项目必须使用/tmp目录
VOLUME /tmp
#将项目的jar包拷贝并命名
COPY learn-validation-0.0.1-SNAPSHOT.jar learn-validation.jar
RUN bash -c "touch /learn-validation.jar"
#暴露的端口
EXPOSE 8082
#执行命令运行项目
ENTRYPOINT ["java","-jar","learn-validation.jar"]
#解决时间不正确的问题
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone \