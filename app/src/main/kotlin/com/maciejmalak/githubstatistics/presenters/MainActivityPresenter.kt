package com.maciejmalak.githubstatistics.presenters

import android.app.Activity
import android.content.Intent
import com.maciejmalak.githubstatistics.activities.AddUserActivity
import com.maciejmalak.githubstatistics.activities.IMainActivity
import com.maciejmalak.githubstatistics.api.Requester

import com.maciejmalak.githubstatistics.presenters.IMainActivityPresenter.CONSTANS.REQUEST_ADD

class MainActivityPresenter constructor(val mView : IMainActivity) : IMainActivityPresenter {

    override fun onAddUserButtonClicked() = mView.launchAddUserActivity(REQUEST_ADD)

    override fun onActivityResultCalled(requestCode : Int, resultCode : Int, data : Intent?) {
        val success : Boolean = if (resultCode == Activity.RESULT_OK) requestCode == REQUEST_ADD
                                else false

        if (!success) showAddUserFailedMessage()

        val name : String? = data?.getStringExtra(AddUserActivity::class.java.simpleName) ?: return

        /* TODO move in better place */
        val repoRequester : Requester = Requester((mView as Activity))
        repoRequester.requestGithubUserInformationByUsername(name)
    }

    override fun onRecyclerViewItemClicked() {
        /*TODO After adding Recycler implement showing detail view*/
    }

    fun showAddUserFailedMessage() = mView.showAddUserFailedMessage()
}
