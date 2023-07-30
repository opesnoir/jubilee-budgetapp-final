# Eindopdracht backend bootcamp Novi Hogeschool -2023 Jubilee BudgetApp
![Jubilee BudgetApp logo](./src/assets/logo.png)

<!-- TODO: toevoegen endpoints  -->
# English

This project contains the backend implementation of a financial management application, developed as part of my backend boot camp at Hogeschool Novi in Utrecht. It provides various functionalities, including account management, balance display, file upload, transaction tracking, storage of savings goals, and contract functionality.

**Functionalities**

1. Account functionality: Users can manage their account details and view their current balance.
2. Balance: The balance model contains information about each user's balance and is used to calculate the current balance.
3. File: Users can upload, download, or remove documents within the application.
4. Transaction tracking: Users can add, edit, and delete transactions, as well as view their transaction history. The transaction model includes details about each transaction, such as the amount, date, and description.
5. Savings goals storage: Users can create and manage savings goals to track their financial objectives.
6. Contract functionality: The contract model supports the management of contract information. These could include agreements related to insurance or other financial matters.






**Technologies**

This application has been developed using the following technologies and frameworks:

1. Java 17
2. Spring Boot 3.1.1
3. Spring Boot Data JPA
4. Spring Boot Web
5. PostgreSQL
6. Spring Security
7. JSON Web Token (JWT)
8. Maven


**Installation**

Follow these steps to install and run the application locally:

1. Make sure Java 17 and Maven are installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the main project directory.
4. Update the PostgreSQL database configuration in the application.properties file located in the src/main/resources directory. Set the spring.datasource.url property to jdbc:postgresql://localhost:5432/jubilee-final (assuming your PostgreSQL database is running on port 5432).
5. Run the command mvn spring-boot:run to start the application.
6. The application will now be running at http://localhost:8080 (assuming your PostgreSQL database is configured to run on port 5432).


# Nederlands


Dit project bevat de backend-implementatie van een financiële beheer applicatie, ontwikkeld als onderdeel van mijn backend bootcamp aan de Hogeschool Novi in Utrecht. Het biedt verschillende functionaliteiten, waaronder accountbeheer, balansweergave, file upload, transactietracking, opslag van spaardoelen en contract functionaliteit.

**Functionaliteiten**

1. Account functionaliteit: Gebruikers kunnen hun accountgegevens beheren en hun huidige balans bekijken.
2. Balans: Het balansmodel bevat informatie over het saldo van elke gebruiker en wordt gebruikt om het actuele saldo te berekenen.
3. File: Gebruikers kunnen documenten binnen de applicatie uploaden, downloaden of verwijderen.
4. Transactie tracking: Gebruikers kunnen transacties toevoegen, bewerken en verwijderen, evenals hun transactiegeschiedenis bekijken. Het transactiemodel bevat details over elke transactie, zoals het bedrag, de datum en een omschrijving.
5. Opslag van spaardoelen: Gebruikers kunnen spaardoelen maken en beheren om hun financiële doelstellingen bij te houden.
6. Contract functionaliteit: Het contractmodel ondersteunt de functionaliteit om contract informatie te beheren. Dit kunnen bijvoorbeeld zorgverzekering of andere financiële overeenkomsten zijn.


**Technologieën**

Deze applicatie is ontwikkeld met behulp van de volgende technologieën en frameworks:

1. Java 17
2. Spring Boot 3.1.1
3. Spring Boot Data JPA
4. Spring Boot Web
5. PostgreSQL
6. Spring Security
7. JSON Web Token (JWT)
8. Maven


**Installatie**

Volg deze stappen om de applicatie lokaal te installeren en uit te voeren:

1. Zorg ervoor dat Java 17 en Maven op je systeem zijn geïnstalleerd.
2. Clone de repository naar je lokale machine.
3. Navigeer naar de hoofdmap van het project.
4. Voer het commando mvn spring-boot:run uit om de applicatie te starten.
5. De applicatie wordt nu uitgevoerd op http://localhost:8080 (veronderstellend dat jouw PostgreSQL-database is geconfigureerd om op poort 5432 te draaien).