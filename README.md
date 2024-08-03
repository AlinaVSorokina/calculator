# Service zur Berechnung einer Versicherungsprämie

Dieser Dienst (Calculator service) ermöglicht es dem Benutzer, eine Versicherungsprämie auf der Grundlage folgender Parameter zu berechnen

- Jährliche Kilometerleistung
- Fahrzeugtyp
- Region der Fahrzeugzulassung

**API:**

Derzeit bietet der Berechnungsdienst einen Endpoint an: 
```
method: GET
url: "/premium"
header: clientId - obligatorische header. Die ID des Kunden
payload: 
{
    "kilometerleistung": 15000, - obligatorisch, positiv
    "postleitzahl": "52066", - obligatorisch string
    "fahrzeugtyp": "PKW" - einer der beiden Werte PKW oder LKW
}
```
	
Response: 
```
status: 200
{
    "clientId": "test-client",
    "premium": "150.00"
}
```

Error-codes:
```
status 400: Validierungsfehler in Header oder Payload
status 500: In allen Fällen, in denen es nicht möglich war, die Prämie zu berechnen
status 404: Wenn die Region nicht über die Postleitzahl gefunden werden konnte
```

**Deployment:**

Um den Dienst zu starten, müssen drei Teile gestartet werden:
1. discovery Service 
2. catalog Service (defaut port: 9090)
3. calculation Service (defaut port: 8080)

services catalog und calculation verwenden die integrierte H2-Datenbank. 
H2-Konsole verfügbar unter '/console/'.
Die Zugriffs-URL kann dem Log unter 'Database available at' entnommen werden.

**Design:**

service-discovery - eureka service discovery Service
catalog - service mit Referenzinformationen. jetzt - Regionale Informationen
calculation - Prämienberechnung durchführen. REST-API-Aufruf an den Katalogdienst, um Informationen über Regionen zu erhalten.

![](/assets/services.jpg)
