# kotlin-cli
This is a simple Kotlin Command Line interface application. The application fetches news article using newsapi's public API based on user input. The project uses Koin for dependency injection, Retrofit for making API calls and Caffiene for caching. 

# Features
Fetch news articles from a public API
Cache the API response to reduce any redundant API calls
Command Line Interface for user Interaction

# Dependencies
* Koin: For dependency injection.
* Retrofit: For API calls.
* Caffeine: For caching.
* Mockk: For mocking in tests.
* JUnit: For unit testing.

# Caching
The application uses Caffeine to cache news articles. Cache entries expire after 10 minutes and the cache can store a maximum of 100 entries.

# Getting started

Clone the repository using the following commands:
git clone https://github.com/yourusername/kotlin-cli-news-app.git](https://github.com/go-ammar/kotlin-cli.git
cd kotlin-cli-news-app

Build the project:
./gradlew build

Run the project:
./gradlew run

Running the tests:
./gradlew test

OR

Open the project using Intellij, build gradle and run the application.


# Note
The .env file is only added for this project.
