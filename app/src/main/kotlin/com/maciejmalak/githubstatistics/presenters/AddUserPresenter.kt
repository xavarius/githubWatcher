package com.maciejmalak.githubstatistics.presenters

import com.maciejmalak.githubstatistics.activities.IAddUser

class AddUserPresenter(val mView : IAddUser) : IAddUserPresenter {
    override fun onAddUserButtonClicked() {
        val username : String? = mView.getUserNameFieldText()

        when (username) {
            null, "" -> mView.showUsernameValidationError()
            else -> mView.closeActivity(username)
        }
    }
}
