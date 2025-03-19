# Grpc

前提: 需要在pom.xml中加入build: package針對內容產生額外jar檔好讓server & client有可以執行的環境library

啟動grpc的server
server side start: java --enable-preview -cp "target/classes:target/dependency/*" com.ku.grpc.HelloServer

使用client呼叫server
java --enable-preview -cp "target/classes:target/dependency/*" com.ku.grpc.HelloClient