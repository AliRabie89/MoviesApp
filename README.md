Movie App

A Kotlin-based Android application that displays a list of movies and their details using modern Android development tools and clean architecture principles.
  Architecture Decision

This project follows Clean Architecture and the MVVM (Model-View-ViewModel) design pattern for clear separation of concerns and better scalability.
Layers:

    UI Layer: Fragments interact with ViewModels to observe and display UI state.

    ViewModel Layer: Manages UI logic and communicates with UseCases.

    Domain Layer: Contains UseCases that encapsulate business rules.

    Data Layer: Responsible for data fetching and caching.

    Local Caching: Implemented using Room Database to support offline usage.

    Dependency Injection: Managed using Dagger-Hilt.

    Asynchronous Operations: Handled via Kotlin Coroutines and Flow.

   How to Run the Project

    Clone the repository:

    git clone https://github.com/yourusername/movie-app.git
    cd movie-app

    Open the project in Android Studio (Arctic Fox or newer recommended).

    Build the project:

        Ensure your internet connection is active for Gradle sync.

        Wait for all dependencies to finish syncing.

    Run the app on an emulator or physical device with Android 6.0+.

   Trade-offs & Assumptions

    Room Database is used for caching movie data locally to improve performance and offline support.

    Unit tests and UI tests are not included due to limited time and project being completed over a short weekend task.

    Paging is not implemented because:

        The number of movie records is limited.

        The API does not support pagination.

    Basic error handling is provided; edge case handling can be improved in future iterations.

    The application assumes the presence of a stable internet connection for initial data fetch.

Tech Stack

    Language: Kotlin

    Architecture: MVVM + Clean Architecture

    Jetpack Libraries: ViewModel, LiveData, Navigation

    Local Storage: Room Database

    Networking: Retrofit

    Asynchronous Work: Coroutines & Flow

    Dependency Injection: Dagger-Hilt