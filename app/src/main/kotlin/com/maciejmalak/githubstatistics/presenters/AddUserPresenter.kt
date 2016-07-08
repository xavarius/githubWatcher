package com.maciejmalak.githubstatistics.presenters

import com.maciejmalak.githubstatistics.activities.AddUserScreen

class AddUserPresenter(val mView : AddUserScreen) : AddUser {
    override fun onAddUserButtonClicked() {
        val username : String? = mView.getUserNameFieldText()

        when (username) {
            null, "" -> mView.showUsernameValidationError()
            else -> mView.closeActivity(username)
        }
    }
}
