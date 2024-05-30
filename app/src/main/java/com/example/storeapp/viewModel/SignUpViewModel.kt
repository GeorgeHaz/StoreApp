package com.example.storeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.SingUpUseCase
import com.example.storeapp.model.Address
import com.example.storeapp.model.Geolocation
import com.example.storeapp.model.Name
import com.example.storeapp.utils.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: SingUpUseCase) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState = _signUpState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _city = MutableStateFlow("")
    val city = _city.asStateFlow()

    private val _street = MutableStateFlow("")
    val street = _street.asStateFlow()

    private val _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    private val _zipCode = MutableStateFlow("")
    val zipCode = _zipCode.asStateFlow()

    private val _phone = MutableStateFlow("")
    val phone = _phone.asStateFlow()

    private val _latitude = MutableStateFlow("")
    val latitude = _latitude.asStateFlow()

    private val _longitude = MutableStateFlow("")
    val longitude = _longitude.asStateFlow()

    fun onUserChange(email: String, username: String, password: String, phone: String) {
        _email.value = email
        _username.value = username
        _password.value = password
        _phone.value = phone
    }

    fun onNameChange(firstName: String, lastName: String){
        _firstName.value = firstName
        _lastName.value = lastName
    }

    fun onAddressChange(city: String, street: String, number: Int, zipCode: String) {
        _city.value = city
        _street.value = street
        _number.value = number
        _zipCode.value = zipCode
    }

    fun onLocationChange(latitude: String, longitude: String) {
        _latitude.value = latitude
        _longitude.value = longitude
    }

    fun singUpInit() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val name = Name(_firstName.value, _lastName.value)
                val location = Geolocation(_latitude.value, _longitude.value)
                val address =
                    Address(_city.value, _street.value, _number.value, _zipCode.value, location)

                val result = useCase.invoke(
                    _email.value,
                    _username.value,
                    _password.value,
                    name,
                    address,
                    _phone.value
                )
                _signUpState.value = SignUpState.Success(result)
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.localizedMessage ?: "Error")
            }

        }
    }
}