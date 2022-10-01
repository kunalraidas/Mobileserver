package com.example.repository

import com.example.data.table.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database_Factory
{
    fun  init(){
        Database.connect(hikari())

        transaction {
            SchemaUtils.create(Admin)
            SchemaUtils.create(Customer)
            SchemaUtils.create(Brand)
            SchemaUtils.create(Category)
            SchemaUtils.create(Color)
            SchemaUtils.create(Product)
            SchemaUtils.create(Product_varient)
        }
    }

      fun hikari() : HikariDataSource
      {
          val config  = HikariConfig()
          config.driverClassName = "org.postgresql.Driver"
          config.jdbcUrl = "jdbc:postgresql:Mobile_Database?user:postgres&password=12345"
          config.maximumPoolSize = 5
          config.isAutoCommit = false
          config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
          config.validate()

          println("Database Connected")
          return HikariDataSource(config)
      }

     suspend fun <T> dbQuery(block : () -> T) : T =
         withContext(Dispatchers.IO){
             transaction { block() }
         }
}