package com.maciejmalak.githubstatistics.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.maciejmalak.githubstatistics.R
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        addUserFAB.setOnClickListener { v -> respondToClick() }
    }

    fun respondToClick() {
        val name : String? = userName.text.toString()
        when (name) {
            null, "" -> askAgain()
            else -> finishAddUser(name)
        }
    }

    fun finishAddUser(name : String) {
        val intent = Intent().putExtra(AddUserActivity::class.java.simpleName, name)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun askAgain() = Toast.makeText(this, "Please enter again", Toast.LENGTH_SHORT).show()
}
