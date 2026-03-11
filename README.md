E-Commerce Admin API
Project Overview
This project is a Java-based REST API designed to manage the backend operations of an e-commerce store. It focuses on organized data structures and a secure environment for administrative tasks.
The system is built around a relationship where categories serve as the primary organizational units for products. To ensure the safety of the store data, the application uses token-based security. This allows any user to view the inventory, but it restricts the ability to add, edit, or delete items to verified administrators.

Core Functionality
Role Based Security
The API distinguishes between public visitors and administrators. Public access is permitted for all requests to view data. Any operation that modifies the database requires a valid security token.

Stateless Authentication
Security is handled via JSON Web Tokens. After a successful login, an administrator is issued a token. This token acts as a digital key that must be presented with every administrative request to verify identity without requiring the server to store session data.

Admin Account Management
The system includes an automated data loader. On every startup, the application checks for the existence of an administrator account. If none is found, it automatically generates a default set of credentials to ensure the system is never left without a manager.

Global Error Handling
A centralized management system is in place to catch errors across the entire application. Whether a user provides incorrect data formats, violates database rules, or attempts unauthorized access, the API responds with a clear and structured message explaining the issue.

Relational Database Design
The project uses a relational model where products are linked directly to their respective categories. This structure ensures data integrity and allows for efficient filtering and organization of the store catalog.

Configuration and Security
To maintain professional security standards, sensitive credentials are kept separate from the source code. The application pulls the following information from the system environment:

Database Password
The unique password required to connect to the MySQL instance.

Security Secret
A private key used to sign and verify authentication tokens.

Interactive Documentation
The API features an integrated documentation interface. This allows developers to view all available endpoints and test them directly from a web browser. When the application is running, this interface can be accessed at the following address:
http://localhost:8080/swagger-ui/index.html
