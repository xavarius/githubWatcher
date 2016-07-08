package com.maciejmalak.githubstatistics.presenters

import android.content.Intent

interface HomeScreen {
    fun onAddUserButtonClicked()
    fun onRecyclerViewItemClicked()
    fun onActivityResultCalled(requestCode : Int, resultCode : Int, data : Intent?)

    companion object CONSTANS {
        const val REQUEST_ADD: Int = 999
    }
}