# About Healthy Lifestyle App

This app retrieves the Articles related to **Health & Fitness** using NYT Article Search APIs.
The app is hardcoded to filter the query based on **headline** and **News Desk Topics**

The Articles were shown as List using RecyclerView and Displays Title and snippet about the article.
If you tap the Article it loads the web_url of the topic in your default browser and shows Picker
option if no default browser is set on your Android Phone.


## Architecture

This app is built based on MVP (Model View Presenter) architecture pattern.
I tried to use JetPack MVVM Architecture pattern but I have not extensively used ViewModel/LiveData yet on my projects yet I have strict with MVP
for simplicity and less dependency over Jetpack library.

MVP architecture pattern enables us to decouple the View logic from Business logic and enables us to write Unit test for Business logic.
View could be tested with UI tests.

## Dependency

I have used following libraries for architecture and design purpose
* Dagger2 and Hilt - For Dependency Injection. Hilt is relatively new lib which that reduces the boilerplate of doing manual dependency injection in
your project.
I have not used Hilt in any of my previous project and this take home exercise is my first bet on it. It really made Dagger setup very easy.
https://developer.android.com/training/dependency-injection/hilt-android#kotlin
 * RecyclerView - To list the articles in UI
 * Retrofit - HTTP lib to fetch the articles using NYT Article Search REST API
 * RxJava2 - Asynchronous processing of Model data over network
 * GSON - JSON Serialization/Deserialization
 * ConstraintLayout 2.0 - UI layout design

 ## How to build the app

 Export the project in Android Studio and wait for the Gradle sync to fetch all the dependencies
 Build the app module and wait for it to be installed in your Phone or Emulator
 Launch the app and check the article list and tap on article to open the article link in browser.

 ## Testing
 I have written Junit test to validate the MainViewPresenter logic. The test demonstrates how dependency management helps us to have fake
 implementation for UI and Repository APIs to validate the Business logic. I did not have a chance to write UI test to validate the UX.
 However I am aware that we could use Espresso framework for UI testing.




