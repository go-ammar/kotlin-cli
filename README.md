# kotlin-cli
This is a simple Kotlin Command Line interface application. The applicatin fetches news article using newsapi's public API based on user input. The project uses Koin for dependency injection, Retrofit for making API calls and Caffiene for caching. 

# Features
Fetch news articles from a public API
Cache the API response to reduce any redundant API calls
Command Line Interface for user Interaction

# Dependencies
Koin: For dependency injection.
Retrofit: For API calls.
Caffeine: For caching.
Mockk: For mocking in tests.
JUnit: For unit testing.

# Caching
The application uses Caffeine to cache news articles. Cache entries expire after 10 minutes and the cache can store a maximum of 100 entries.


# Note
The .env file is only added for this project.
