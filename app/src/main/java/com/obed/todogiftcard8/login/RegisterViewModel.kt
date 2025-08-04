package com.obed.todogiftcard8.login

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var uiState by mutableStateOf(RegisterUiState())

    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun onConfirmPasswordChange(newPassword: String) {
        uiState = uiState.copy(confirmPassword = newPassword)
    }

    fun registerUser(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val email = uiState.email.trim()
        val password = uiState.password.trim()
        val confirmPassword = uiState.confirmPassword.trim()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            onError("All fields are required")
            return
        }

        if (password != confirmPassword) {
            onError("Passwords do not match")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                uiState = uiState.copy(isLoading = false)

                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    val error = task.exception?.message ?: "Registration failed"
                    onError(error)
                }
            }
    }
}


data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)



