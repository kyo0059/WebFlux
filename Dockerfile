# 使用 OpenJDK 17 作為基礎映像
FROM openjdk:17-jdk-slim

# 設置應用程序的工作目錄
WORKDIR /app

# 將構建的 JAR 文件拷貝到容器中
COPY build/libs/TestWebFlux-1.0.0-SNAPSHOT.jar app.jar

# 暴露應用程序運行的端口
EXPOSE 8080

# 創建日志目錄
RUN mkdir /logs

# 定義應用程序的啟動命令
ENTRYPOINT ["java", "-jar", "app.jar"]
