package com.maciejmalak.githubstatistics.model

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.*

object DataStorage {
    val DATA_SET: HashSet<Account> = HashSet<Account>()

    init {
        println("Data storage is ready")
    }

    fun storeUser(@NotNull user : Account) = DATA_SET.add(user)
    fun deleteUser(user : Account) = DATA_SET.remove(user)
    @Nullable fun findUser(name : String) : Account? = DATA_SET.find { it.name == name }
}
