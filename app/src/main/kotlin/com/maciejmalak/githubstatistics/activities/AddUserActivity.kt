package com.maciejmalak.githubstatistics.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.presenters.AddUser
import com.maciejmalak.githubstatistics.presenters.AddUserPresenter
import kotlinx.android.synthetic.main.activity_add_user.*
import org.jetbrains.annotations.NotNull

class AddUserActivity : AppCompatActivity(), AddUserScreen {
    val mPresenter: AddUser = AddUserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        addUserBtn.setOnClickListener { mPresenter.onAddUserButtonClicked() }
    }

    override fun closeActivity(@NotNull username: String) {
        val className = AddUserActivity::class.java.simpleName
        val intent = Intent().putExtra(className, username)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun getUserNameFieldText(): String? = userName.text.toString()

    override fun showUsernameValidationError() = Toast.makeText(this, "Username is not valid", Toast.LENGTH_SHORT).show()
}
