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

# Prerequisites

* Java 8 or higher
* Kotlin 1.5 or higher
* Gradle 6.0 or higher

# Getting started

Clone the repository and run the project using the following commands:


git clone https://github.com/go-ammar/kotlin-cli.git

git fetch

git checkout origin master

cd kotlin-cli-news-app

./gradlew build

./gradlew run

./gradlew test

# OR

* Open the project using Intellij, build gradle and run the application.

# Video Walkthrough


https://github.com/go-ammar/kotlin-cli/assets/61266813/b73c4904-9d13-45b9-b7f6-66ad2cc21a45



# Note
The .env file is only added for this project.
