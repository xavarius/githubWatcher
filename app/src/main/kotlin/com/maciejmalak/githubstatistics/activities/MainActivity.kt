package com.maciejmalak.githubstatistics.activities

import adapters.RecyclerViewUserAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.bindView
import com.maciejmalak.githubstatistics.api.Requester
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.model.GithubUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val recyclerView : RecyclerView by bindView(R.id.recyclerView)
    val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
    val mAdapter : RecyclerViewUserAdapter = RecyclerViewUserAdapter()

    companion object CONSTANS {
        const val REQUEST_ADD: Int = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(mAdapter)

        fab.setOnClickListener {
            val intent : Intent = Intent(this, AddUserActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)
        }
    }

    /*TODO move to presenter */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val success : Boolean = if (resultCode == Activity.RESULT_OK) requestCode == REQUEST_ADD
                                else false

        if (!success) {
            Toast.makeText(this, "no selected user", Toast.LENGTH_SHORT).show()
            return
        }
        val name : String? = data?.getStringExtra(AddUserActivity::class.java.simpleName) ?: return
        val repoRequester : Requester = Requester(this)
        repoRequester.requestGithubUserInformationByUsername(name)
    }

    fun addUserToDataSet(user: GithubUser) {
        mAdapter.addToDataSet(user)
    }
}
