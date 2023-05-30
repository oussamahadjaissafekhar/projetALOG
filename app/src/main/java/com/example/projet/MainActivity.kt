package com.example.projet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.movieexample.databinding.ActivityMainBinding
import com.example.projet.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        var loginButton = binding.loginButton

        loginButton.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234"){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent);
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }


    })
        /* private fun login(username: String, password: String) {
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
    }*/
}}