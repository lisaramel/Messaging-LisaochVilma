# LAB 6: Messaging

## Del 1: Intro
Målet med del 1 är att ge en inblick i hur man kan konfigurera rabbit och hur relationen mellan Exchange Queue och Binding är.

Navigera till RabbitTest i business service och slutför testfallen för att känna på hur det är att jobba med RabbitMQ

## Del 2: Real life use case
Ni äger två tjänster "business" och "audit"
* Business har hand om all affärslogik
* Audit har hand om en audit trail, dvs allt som händer i systemet.

Varje gång det händer en förändring i affären vill vi skicka ett event på en meddelande buss. Varje gång något publiceras på bussen vill vi lyssna på det och uppdatera vår audit log.

### Uppgift 1: Lös uppgifterna i business service för att konfigurara och publicera meddelanden
* Uppgift 1: Konfigurera rabbit, sätt up en exchange
* Uppgift 2: Sätt up en test miljö i ett testfall och se till att det fallerar.
* Uppgift 3: Uppdatera koden så att den publicerar meddelanden.

### Uppgift 2: Lös uppgifterna i audit service för att konfigurera och konsumera meddelanden.
* Uppgift 1: Konfigurera rabbit, sätt upp en Queue och en Binding
* Uppgift 2: Skapa en Listener och uppdatera databasen med alla audit logs.

## Uppgift 3: Provkör och se att allt fungerar
1. Bygg båda projekten
2. bygg docker images av båda projekten
3. Kolla på docker-compose filen och uppdatera så att den fungerar
4. Kör allt

Testa att posta till business servicen det finns två endpoints, en för att öppna konton och en för att sätta in pengar.
Navigera till audit frontenden och se att alla events kommer upp i guit!
Kolla igenom all kod i båda projekten och se så att allt förstås.

## Extra uppgift
Välj någon av följande

### Val nummer 1
Implementera messaging i eran labb-miljö. Se till att alla viktiga händelser plockas upp av en AuditService.
Det går bra att återanvända den i denna labb om man så vill men det ska gå att i GUIt kunna se en 
audit log över alla händelser på alla accounts. Denna är enbart synlig för Admins förståss!

### Val nummer 2
Fortsätt i detta projekt och sätt upp två exchanges och två queues. En för AccountOpened och en för AccountDeposited. Se till att det finns två listeners i AuditService och att befintlig funktionalitet fortfarande leker!

