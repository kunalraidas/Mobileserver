package com.example.data.table

import org.jetbrains.exposed.sql.Table

object SupplierTable : Table() {

    val supplier_id = varchar("supplier_id",10)

    override val primaryKey: PrimaryKey = PrimaryKey(supplier_id)
}