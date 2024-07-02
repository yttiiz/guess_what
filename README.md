# ![favicon](./src/main/resources/drawable/icons/yz_logo.svg) Quiz Game

This desktop application is my __fist project__ using `Kotlin` and its ecosystem.
It's a simple __quiz game__ to check anybody knowledge on a particular subject.

### Scenario
1. First user have to sign in
2. Then answer the questions
3. Finally, his score will be displayed with an appreciation

### Specifications

- **Language** : [Kotlin](https://kotlinlang.org/docs/home.html)
- **Framework** : [Compose](https://www.jetbrains.com/fr-fr/lp/compose-multiplatform/)
- **Database** : [mongodb](https://www.mongodb.com/)

The application configuration is define in the `build.gradle.kts` file.

## How does it works ?

First of all, you have to install a **_MongoDB_** tool on your machine.

- You can download
  [_MongoDB compass_](https://www.mongodb.com/try/download/compass), a **user
  friendly tool** to handle interaction with the database.
- If you prefer command line tools, you can download the
  [_MongoDB Shell_](https://www.mongodb.com/try/download/shell) or the
  [_Atlas CLI_](https://www.mongodb.com/try/download/atlascli).

After your DB configuration, you have to create a `.env` file, to set your
**environnement variables**. Check the `.env.sample` file to identify the
necessary keys you have to set :

```
MONGODB_USERNAME=
MONGODB_PASSWORD=
MONGODB_HOST=
DATA_HOST=
```

Then, you have to create a __database__ and a __collection__ where __documents__ looks like this type :


```kotlin
data class QuestionItems(
    val title: String,
    val propositions: List<String>
)
```

```kotlin
data class Question(
    val _id: ObjectId,
    val question: QuestionItems,
    val correction: String
)
```

## Recommendation
It's highly recommended to use [IntelliJ](https://www.jetbrains.com/idea/) IDE for better user experience.

## Is the app has tests ?
Not yet but coming asap.
