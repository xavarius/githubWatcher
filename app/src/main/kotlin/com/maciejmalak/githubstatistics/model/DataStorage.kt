package com.maciejmalak.githubstatistics.model

import org.jetbrains.annotations.Nullable
import java.util.*

object DataStorage {
    val dataSet : HashSet<GithubUser> = HashSet<GithubUser>()

    init {
        println("Data storage is ready")
    }

    fun storeUser(user : GithubUser) = dataSet.add(user)
    fun deleteUser(user : GithubUser) = dataSet.remove(user)
    @Nullable fun findUser(name : String) : GithubUser? = dataSet.find { it.name == name }
}
