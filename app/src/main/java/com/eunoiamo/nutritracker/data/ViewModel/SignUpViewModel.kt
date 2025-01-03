import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoiamo.nutritracker.data.api.ApiClient
import com.eunoiamo.nutritracker.data.model.SignUpRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SignUpViewModel : ViewModel() {
    private val apiService = ApiClient.apiService

    fun registerUser(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = SignUpRequest(username, email, password)
                val response = apiService.signUp(request)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    onError("Failed: $errorBody")
                }
            } catch (e: HttpException) {
                onError("Network error: ${e.message()}")
            } catch (e: IOException) {
                onError("IO error: ${e.message}")
            }
        }
    }
}
