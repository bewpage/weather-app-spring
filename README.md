# Weather App using Java/Spring

## Project Description

`weather-app-spring` is a robust and feature-rich weather application built with Java and Spring Boot. The application
allows you to fetch real-time weather information using the OpenWeather API and offers a RESTful API that can be easily
navigated with the included Swagger documentation.

## Features

- Fetch real-time weather information by city name, zip code, or coordinates.
- RESTful API for easy integration with other services.
- Built-in Swagger for API documentation and testing.
- Docker Compose setup for running the application with MySQL.

## Requirements

- Java 11+
- Spring Boot
- Maven
- OpenWeather API Key
- Docker (optional)
- Docker Compose (optional)

## Installation

### Standard Setup

1. **Clone the repository:**
   ```
   git clone https://github.com/bewpage/weather-app-spring.git
   ```

2. **Navigate to the project directory:**
   ```
   cd weather-app-spring
   ```

3. **Create an OpenWeather API key:**
   Sign up at [OpenWeather](https://openweathermap.org/appid) to get your API key.

4. **Add OpenWeather API Key:**
   Create a new file named `application-secret.properties` inside the `resources` folder and add your OpenWeather API
   Key.
   ```
   OPENWEATHER_API_KEY=<your-api-key-here>
   ```

### Docker Compose Setup (Optional)

1. **Navigate to the project directory.**

2. **Run services with Docker Compose:**
   ```
   docker-compose -p <here-your-project-name> -f ./docker/docker-compose.yml up -d
   ```
   This will start the Spring Boot application and MySQL database, making it even easier to manage both.

### Running the App

1. **Build the project:**
   ```
   mvn clean install
   ```

2. **Run the Spring Boot app:**
   ```
   mvn spring-boot:run
   ```

   Or if using Docker Compose:
   ```
   docker-compose up
   ```

The application should now be running on [http://localhost:8080](http://localhost:8080).

## API Documentation

- After starting the application, visit [Swagger UI](http://localhost:8080/swagger-ui.html) to see the available RESTful
  endpoints and to test them interactively.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

Enjoy weather hunting! 🌤☔️💨