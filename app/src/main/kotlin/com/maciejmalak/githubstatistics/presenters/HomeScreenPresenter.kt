package com.maciejmalak.githubstatistics.presenters

import android.app.Activity
import android.content.Intent
import com.maciejmalak.githubstatistics.activities.AddUserActivity
import com.maciejmalak.githubstatistics.activities.Home
import com.maciejmalak.githubstatistics.model.GithubInteractor
import com.maciejmalak.githubstatistics.model.GithubInteractor.GithubInteractorListener
import com.maciejmalak.githubstatistics.model.Account
import com.maciejmalak.githubstatistics.presenters.HomeScreen.CONSTANS.REQUEST_ADD

class HomeScreenPresenter constructor(val mView : Home) : com.maciejmalak.githubstatistics.presenters.HomeScreen, GithubInteractorListener {

    override fun onActivityResultCalled(requestCode : Int, resultCode : Int, data : Intent?) {
        val success : Boolean = if (resultCode == Activity.RESULT_OK) requestCode == REQUEST_ADD
                                else false

        if (!success) showAddUserFailedMessage()

        val name : String? = data?.getStringExtra(AddUserActivity::class.java.simpleName) ?: return

        /* TODO move into better place */
        val repoRequester : GithubInteractor = GithubInteractor(this)
        repoRequester.fetchGithubAccount(name)
    }

    override fun onAddUserButtonClicked() = mView.launchAddUserActivity(REQUEST_ADD)

    override fun onRecyclerViewItemClicked() {
        /*TODO After adding Recycler implement showing detail view*/
    }

    fun showAddUserFailedMessage() = mView.showAddUserFailedMessage()

    override fun onAccountInfoFetched(user: Account) = mView.showNewListEntry(user)

    override fun onAccoutFetchingError() = mView.showDownloadAccountFailedMessage()
}
