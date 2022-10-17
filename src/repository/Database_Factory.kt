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
    fun init()
    {
        Database.connect(hikari())

        transaction {
            SchemaUtils.create(AdminTable)
            SchemaUtils.create(AccessoriesTable)
            SchemaUtils.create(CartTable)
            SchemaUtils.create(ColorTable)
            SchemaUtils.create(CustomerTable)
            SchemaUtils.create(DiscountTables)
            SchemaUtils.create(IMEI_NO_Table)
            SchemaUtils.create(InvoiceTable)
            SchemaUtils.create(MobileTable)
            SchemaUtils.create(OrderTable)
            SchemaUtils.create(ProductTable)
            SchemaUtils.create(PaymentTable)
            SchemaUtils.create(PincodeTable)
            SchemaUtils.create(PurchaseTable)
            SchemaUtils.create(StockTable)
            SchemaUtils.create(SupplierTable)
        }
    }

    fun hikari() : HikariDataSource
    {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("DATABASE_URL")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        println("Database Connect")
        return  HikariDataSource(config)
    }
    suspend fun <T> dbQuery(block : () -> T) : T =
        withContext(Dispatchers.IO){
            transaction { block() }
        }
}