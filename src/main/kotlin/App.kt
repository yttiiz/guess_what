import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import quiz.data.mongo.MongoClientConnexion

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.main()

    // Display screen
    MaterialTheme {
        Column(
        ) {
            Header(name)
            HomeBody()
        }
    }
}