🏦 Bank Management System (Java Swing + JDBC)

The Bank Management System is a Java-based desktop application developed using Java Swing and JDBC to manage essential banking operations efficiently. 
The application interacts with a MySQL database to perform core banking functionalities such as account creation, balance inquiry, deposits, withdrawals, fund transfers, and password management.
This project demonstrates strong understanding of Core Java, GUI development, database connectivity, and transaction management.

🚀 Features

--> User Registration and Login Authentication

--> Create New Bank Account

--> Check Account Balance

--> Deposit Money

--> Withdraw Money with balance validation

--> Transfer Money between accounts (with transaction handling)

--> Change Account Password

--> Secure database interaction using PreparedStatement

--> User-friendly graphical interface using Java Swing

🛠️ Technologies Used

--> Java

--> Java Swing (JFrame, JButton, JLabel, JTextField, JPasswordField, etc.)

--> JDBC (Java Database Connectivity)

--> MySQL

--> Eclipse IDE

--> WindowBuilder Plugin

--> MySQL Connector (JAR)

🧱 Project Structure
BankManagementSystem
│
├── src
│   ├── ui
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   ├── Dashboard.java
│   │   ├── CreateAccount.java
│   │   ├── CheckBalance.java
│   │   ├── DepositMoney.java
│   │   ├── WithdrawMoney.java
│   │   ├── TransferMoney.java
│   │   └── ChangePassword.java
│   │
│   └── util
│       └── DBConnection.java
│
└── mysql-connector-java.jar

⚙️ How It Works

--> LoginPage.java → Authenticates users using username and password

--> RegisterPage.java → Allows new users to register

--> Dashboard.java → Main navigation page for all banking operations

--> CreateAccount.java → Creates new bank accounts

--> CheckBalance.java → Displays account balance securely

--> DepositMoney.java → Adds money to an account

--> WithdrawMoney.java → Withdraws money after balance validation

--> TransferMoney.java → Transfers money between accounts using JDBC transactions

--> ChangePassword.java → Updates account password securely

--> DBConnection.java → Handles MySQL database connection

--> Each module communicates with the database using JDBC and executes SQL queries securely using PreparedStatement.

🗄️ Database Details

--> Database: MySQL

--> Tables:

--> users → username, password

--> accounts → acc_no, name, balance, password

--> Uses JDBC for database connectivity

--> Implements transaction management for fund transfers

--> Ensures secure and reliable data storage

▶️ How to Run the Project

--> Open the project in Eclipse IDE

--> Add MySQL Connector JAR to the Build Path

--> Configure database credentials in DBConnection.java

--> Create required database and tables in MySQL

--> Run LoginPage.java

--> Perform banking operations using the GUI

🎯 Learning Outcomes

--> Understanding Java Swing GUI development

--> Implementing real-time banking operations using JDBC

--> Working with MySQL databases

--> Using PreparedStatement for secure SQL execution

--> Implementing transaction management (commit & rollback)

--> Exception handling and user input validation

--> Designing modular and scalable desktop applications

👤 Author

Manjunatha R
Computer Science & Engineering
Government Engineering College, Karwar
