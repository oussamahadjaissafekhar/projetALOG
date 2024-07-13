package com.example.projet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivitySigninBinding
import com.example.myapplication.entity.LoginRequest
import com.example.myapplication.model.MyModel
import com.example.myapplication.retrofit.AuthRepository
import kotlinx.coroutines.*

class SingIn : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var loginButton = binding.loginButton

        loginButton.setOnClickListener(View.OnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if(username != "" && password != ""){
                login(username,password)
            }else{
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            }
    })
}

    private fun login(username: String, password: String) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            this.runOnUiThread {
                Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val user = LoginRequest(username,password)
            //Log.d("user","user = ${user.username} , password = ${user.password}")
            val response = AuthRepository.createApiService().login(username,password)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    //Toast.makeText(this@SingIn, "clientid : ${response.body()!!.ClientId}", Toast.LENGTH_SHORT).show()
                    if(response.body()!!.ClientId == 0){
                        Toast.makeText(this@SingIn, "password or username invalid", Toast.LENGTH_SHORT).show()
                    }else{
                        binding.password.setText("")
                        binding.username.setText("")
                        val intent = Intent(this@SingIn, HomeActivity::class.java)
                        intent.putExtra("ClientId", response.body()!!.ClientId.toString()) // Replace "key" and "value" with your actual data
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@SingIn, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}