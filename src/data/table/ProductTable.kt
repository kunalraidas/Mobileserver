package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ProductTable : Table()
{
    val Product_id = varchar("product_id",10)
    val Product_name = varchar("product_name",50)
    val Product_desc = varchar("product_desc",100)
    val Brand_id = varchar("brand_",10).references(BrandTable.Brand_id)
    val Cate_id = varchar("cate_id",10).references(CategoryTable.Cate_id)
    val Supplier_id = varchar("supplier_id",10).references(SupplierTable.supplier_id)
    val Modal_Number = varchar("modal_number",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Product_id)
}