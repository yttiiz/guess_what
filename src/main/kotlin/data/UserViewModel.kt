package quiz.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import quiz.data.mongo.User

class UserViewModel: ViewModel() {
    private var _user = MutableStateFlow<List<User>>(emptyList())
    val user: StateFlow<List<User>> = _user.asStateFlow()

    fun setUser(newUser: List<User>) {
        _user.value = newUser
    }
}