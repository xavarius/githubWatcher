package com.maciejmalak.githubstatistics.activities

import adapters.RecyclerViewUserAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.bindView
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.model.GithubUser
import com.maciejmalak.githubstatistics.presenters.IMainActivityPresenter
import com.maciejmalak.githubstatistics.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainActivity {
    val mRecyclerView: RecyclerView by bindView(R.id.recyclerView)
    val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
    val mAdapter : RecyclerViewUserAdapter = RecyclerViewUserAdapter()
    val mPresenter : IMainActivityPresenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(layoutManager)
        mRecyclerView.setAdapter(mAdapter)

        fab.setOnClickListener { mPresenter.onAddUserButtonClicked() }
    }

    override fun launchAddUserActivity(requestCode: Int) {
        val intent : Intent = Intent(this, AddUserActivity::class.java)
        startActivityForResult(intent, requestCode)
    }

    override fun launchUserDetailsActivity() { /*TODO*/}

    override fun showAddUserFailedMessage() = Toast.makeText(this, "Failed during addin user", Toast.LENGTH_SHORT).show()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResultCalled(requestCode, resultCode, data)
    }

    /*TODO what to do with that? Belongs to presenter or to view? */
    fun addUserToDataSet(user: GithubUser) {
        mAdapter.addToDataSet(user)
    }
}
