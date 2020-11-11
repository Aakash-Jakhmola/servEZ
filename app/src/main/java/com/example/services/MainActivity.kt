package com.example.services

import android.content.Intent
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val string:SpannableString = SpannableString("Don't have an account? Sign Up") ;

        val intent:Intent = Intent(this, SignupActivity::class.java)
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
        textView6.setMovementMethod(LinkMovementMethod.getInstance()) ;
    }


}
