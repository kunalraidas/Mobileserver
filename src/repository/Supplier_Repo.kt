package com.example.repository

import com.example.data.model.Supplier
import com.example.data.table.SupplierTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class Supplier_Repo
{
    suspend fun addSupplier(supplier: Supplier)
    {
        dbQuery {
            SupplierTable.insert { st->
                st[SupplierTable.Supplier_email] = supplier.supplier_email
                st[SupplierTable.Supplier_name] = supplier.supplier_name
                st[SupplierTable.Company_name] = supplier.company_name
                st[SupplierTable.Address] = supplier.address
                st[SupplierTable.Contact_no] = supplier.contact_no
            }
        }
    }

    suspend fun findSupplierByEmail(email : String) = dbQuery {
        SupplierTable.select { SupplierTable.Supplier_email.eq(email) }
            .map {
               rowToSupplier(it)
            }
    }

    private fun rowToSupplier(row: ResultRow) : Supplier?{
        return Supplier(
            supplier_email = row[SupplierTable.Supplier_email],
            supplier_name = row[SupplierTable.Supplier_name],
            company_name = row[SupplierTable.Company_name],
            address = row[SupplierTable.Address],
            contact_no = row[SupplierTable.Contact_no]
        )
    }

    // Get All Supplier Details
    suspend fun getAllSupplier() : List<Supplier?> = dbQuery {
        SupplierTable.selectAll().map {
            rowToSupplier(it)
        }
    }

     // Supplier Is Already Exists
    suspend fun supplierExist(email: String) : Boolean{
        return dbQuery {
            SupplierTable.select {
                SupplierTable.Supplier_email.eq(email)
            }.count() == 1L
        }
    }
}