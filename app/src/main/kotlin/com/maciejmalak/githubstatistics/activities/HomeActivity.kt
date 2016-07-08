package com.maciejmalak.githubstatistics.activities

import adapters.RecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.bindView
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.model.Account
import com.maciejmalak.githubstatistics.presenters.HomeScreen
import com.maciejmalak.githubstatistics.presenters.HomeScreenPresenter
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), Home {

    val mPresenter: HomeScreen = HomeScreenPresenter(this)
    val mAdapter: RecyclerAdapter = RecyclerAdapter()
    val mRecyclerView: RecyclerView by bindView(R.id.recyclerView)
    val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(mLayoutManager)
        mRecyclerView.setAdapter(mAdapter)

        fab.setOnClickListener { mPresenter.onAddUserButtonClicked() }
    }

    override fun launchAddUserActivity(requestCode: Int) {
        val intent: Intent = Intent(this, AddUserActivity::class.java)
        startActivityForResult(intent, requestCode)
    }

    override fun launchUserDetailsActivity(name: String) {
        val startActivity = Intent(this, AccountDetailsScreen::class.java)
        startActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity.putExtra(AccountDetailsScreen::class.java.name, name)
        startActivity(startActivity)
    }

    override fun showAddUserFailedMessage()
            = Toast.makeText(this, "Failed during adding user", Toast.LENGTH_SHORT).show()

    override fun showDownloadAccountFailedMessage()
            = Toast.makeText(this, "Problem occured while downloading account from Github", Toast.LENGTH_SHORT).show()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResultCalled(requestCode, resultCode, data)
    }

    override fun showNewListEntry(user: Account) {
        mAdapter.addToDataSet(user)
    }
}
