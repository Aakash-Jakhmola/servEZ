package com.example.services.signInRegister

import android.content.Intent
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.services.R
import com.example.services.shared.GetCurrentUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(FirebaseAuth.getInstance().currentUser?.uid != null) {
            GetCurrentUser(this)
        }
        val buttonLogin:Button = findViewById(R.id.button_login)
        buttonLogin.setOnClickListener{
            performLogin()
        }

        val string:SpannableString = SpannableString("Don't have an account? Sign Up") ;

        val intent = Intent(this, SignupActivity::class.java)
        val click : ClickableSpan = object :ClickableSpan() {
            override fun onClick(view : View) {
                startActivity(intent)
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText= false

            }
        }

        string.setSpan(StyleSpan(BOLD), 23, 30,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(click, 23, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView6.text = string;
        textView6.movementMethod = LinkMovementMethod.getInstance();
    }

    private fun performLogin(){
        val email = findViewById<EditText>(R.id.email_login).text
        val password = findViewById<EditText>(R.id.password_login).text
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(),password.toString())
            .addOnSuccessListener {
                email.clear()
                password.clear()
                GetCurrentUser(this)
            }
                .addOnFailureListener{
                    Log.d("Logs","failed to login ${it.message}")
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
                }
    }

}

