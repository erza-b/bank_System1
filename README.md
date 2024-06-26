# Bank System Console Application

## Description

This is a console-based bank system application developed in Java. The application allows users to create and manage multiple bank accounts, perform transactions (including withdrawals, deposits, and transfers), and manage transaction fees. The system supports both flat fee and percentage fee transactions.

## Features

- Create a bank with a specified name.
- Create an account with a unique ID, holder name, and initial balance.
- Perform transactions (withdrawals, deposits, and transfers) between accounts.
- Handle both flat fee and percentage fee transactions.
- View transaction history for any account.
- Check the balance of any account.
- Display a list of all bank accounts.
- Check the total transaction fee amount collected by the bank.
- Check the total transfer amount processed by the bank.

## Setup and Installation

### Prerequisites
- Java 8 or later.
- Maven (for managing dependencies).

### Installation
1. Clone the repository from GitHub:
``` git clone https://github.com/erza-b/bank_System1.git ```
2. Navigate to the project directory:
```cd bank-system-console```
3. Build the project using Maven:
```mvn clean install```

## Running the Application

1. Run the application:
```mvn spring-boot:run``` 
2. Follow the on-screen instructions to navigate through the menu options and use the application.
## Using Postman for Testing RESTful APIs

This application includes RESTful API endpoints for managing bank accounts and transactions. You can use Postman to test these endpoints. The base URL for the API is: http://localhost:8080/api/accounts
![Description of the image](images/img1.png)

