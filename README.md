# MovieTrove
Work in progress
- Next step: Integrate Room to save user favorites, and persist data when offline

### Task Overview
Main Screen
- [x] Home Tab:

    -  This section is divided into upper and lower, each containing a horizontal carousel. The upper carousel should display Now Playing movies and the lower carousel display Most Popular movies. Each item should display a thumbnail and title of the movie. Items in both carousels should be clickable and trigger the Movie Details Screen.

- [ ] Favorites Tab:
    - Displays a list of the movies that a user has marked as 'favorites'. This list should be persisted for viewing even when offline. A list item should be clickable and trigger Movie Details Screen. A user should be able to remove a movie from their favorites list.

- [ ]  Movie Details Screen
   - [x] Display the movie poster
   - [ ] Display pertinent details for the specific movie including title, genre(s), release date, rating, overview.
    -[ ] Provides a button that allows a user to save this movie to their favorites list.

### Running Instructions
- Add API key to [APIConstants.kt](app/src/main/java/com/ambrosio.movietrove/network/APIConstants.kt)

### Preview

Home screen
![Home](home.png)

Movie Detail
![Detail](detail.png)

### APK
- [apk](movieTrove.apk)