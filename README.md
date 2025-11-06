# CipherSpace

A modern real-time chat application built with Spring Boot and WebSocket technology. This application allows users to communicate in real-time, send text messages, emojis, and images while maintaining message encryption and user authentication.

## Features

- 🔐 Secure user authentication with JWT
- 💬 Real-time messaging using WebSocket
- 😊 Emoji picker with categorized emojis
- 📸 Image sharing capability
- 🌓 Dark/Light mode toggle
- 🔒 Message encryption
- 📱 Responsive design for all devices
- 💾 Persistent login using localStorage

## Tech Stack

### Backend
- Spring Boot
- Spring WebSocket
- Spring Security
- JWT (JSON Web Tokens)
- STOMP Protocol
- Java

### Frontend
- HTML5
- CSS3
- JavaScript
- WebSocket Client
- Bootstrap
- Font Awesome

## Prerequisites

- Java 8 or higher
- Maven
- Modern web browser
- Internet connection for real-time communication

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/chat-application.git
```

2. Navigate to the project directory:
```bash
cd chat-application
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring:boot run
```

5. Open your browser and visit:
```
http://localhost:8081
```

## Configuration

1. Create `application.properties` in `src/main/resources/` with the following content:
```properties
server.port=8081
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## Usage

1. Register a new account or login with existing credentials
2. Start chatting with other users in real-time
3. Use the emoji picker to add emojis to your messages
4. Share images by clicking the image upload button
5. Toggle between dark and light mode using the theme switch

## Security Features

- JWT-based authentication
- Password encryption
- Secure WebSocket connections
- Message encryption
- Session management
- Input sanitization

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot team for the amazing framework
- WebSocket community for real-time communication
- All contributors who have helped improve this project 
