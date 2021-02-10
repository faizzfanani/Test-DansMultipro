package com.faizzfanani.codetest_dansmultipro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.faizzfanani.codetest_dansmultipro.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val username = "faizzfanani"
    private val password = "dansmultipro"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //event listener
        btn_login.setOnClickListener {
            if (validateInput()){
                val intent = Intent(applicationContext, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun validateInput():Boolean{
        if(login_username.text.toString().isEmpty()){
            login_username.error = "Username can't be empty!"
            login_username.requestFocus()
            return false
        }
        if(login_password.text.toString().isEmpty()){
            login_password.error = "Password can't be empty!"
            login_password.requestFocus()
            return false
        }
        if(login_username.text.toString() != username || login_password.text.toString() != password){
            Toast.makeText(applicationContext, "wrong username/password!", Toast.LENGTH_SHORT).show()
            login_username.text?.clear()
            login_password.text?.clear()
            return false
        }
        return true
    }

}