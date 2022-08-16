# Image Search

Image Search is an Android project using [Flickr API](https://www.flickr.com/services/api/flickr.photos.search.html) based on MVVM architecture.
The app gets and shows a list of images from the Flickr API, then it shows details when image items on the list are tapped.

![Showcase](/docs/list_screen.jpg) ![Showcase](/docs/details_screen.jpg)

## Features
- 100% Kotlin
- MVVM architecture
- Reactive pattern
- Android Jetpack
- Flow
- Single activity app with fragments
- Dependency injection

## Tech stack
- [Android Jetpack](https://developer.android.com/jetpack) - A collections of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices.
    - [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - Extend the functionality of Dagger Hilt to enable dependency injection of certain classes from the androidx libraries.
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Build and structure your in-app UI, handle deep links, and navigate between screens.
    - [Paging](https://developer.android.com/reference/androidx/paging/package-summary) - The Paging Library makes it easier for you to load data gradually and gracefully within your app's RecyclerView.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room) - Create, store, and manage persistent data backed by a SQLite database.
- [PhotoView](https://github.com/Baseflow/PhotoView) - PhotoView aims to help produce an easily usable implementation of a zooming Android ImageView.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide) - A fast and efficient open source media management and image loading framework for Android.
- [Logcat](https://github.com/square/logcat) - Tiny Kotlin API for cheap logging on top of Android's normal Log class.


## Development setup
You need [Android Studio Bumblebee](https://developer.android.com/studio) (or newer) to be able to build the app.

### Code style
This project uses [ktlint](https://github.com/pinterest/ktlint). Use the `ktlint` and `ktlint-format` Gradle tasks
to check and format according to proper Kotlin lint rules.

### API key :key:
You will need to provide an API key to use the app.
You can find information about how to gain access in the following link.
- [Flickr API](https://www.flickr.com/services/api/)

Add the key to the `gradle.properties` file:

```
# Flickr API key
FLICKR_API_KEY=YOUR_KEY_GOES_HERE
```
