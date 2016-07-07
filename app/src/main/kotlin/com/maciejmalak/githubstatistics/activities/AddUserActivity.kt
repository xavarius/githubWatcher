package com.maciejmalak.githubstatistics.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.presenters.AddUserPresenter
import com.maciejmalak.githubstatistics.presenters.IAddUserPresenter
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity(), IAddUser {
    val mPresenter : IAddUserPresenter = AddUserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        addUser.setOnClickListener { mPresenter.onAddUserButtonClicked() }
    }

    override fun closeActivity(username: String) {
        val intent = Intent().putExtra(AddUserActivity::class.java.simpleName, username)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun getUserNameFieldText(): String? = userName.text.toString()

    override fun showUsernameValidationError() = Toast.makeText(this, "Username is not valid", Toast.LENGTH_SHORT).show()
}
