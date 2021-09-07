docker run --rm -p"3306:3306" --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=lab1 -d mysql:8.0.26


Entry: http://localhost:8080/bank/dan/account

uppdatera i standalone-full -> JAVA_OPTS=”-Xms64m -Xmx2G -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2G -Djava.net.preferIPv4Stack=true”



# LAB 6: Messaging

## Efter avslutad labb
Två tjänster som kan prata messaging med varandra.

