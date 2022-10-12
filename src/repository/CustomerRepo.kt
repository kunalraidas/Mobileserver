package com.example.repository

import com.example.data.model.Customer
import com.example.data.table.CustomerTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select


class CustomerRepo
{
    // When Customer Enter In Server
    suspend fun addCustomer(customer: Customer)
    {
        Database_Factory.dbQuery {
           CustomerTable.insert { ct ->
               ct[CustomerTable.Cust_id] = customer.cust_id
               ct[CustomerTable.Cust_name] = customer.cust_name
               ct[CustomerTable.Cust_address] = customer.cust_address
               ct[CustomerTable.Address_pincode] = customer.address_pincode
               ct[CustomerTable.Cust_email] = customer.cust_email
               ct[CustomerTable.Cust_password] = customer.cust_password
               ct[CustomerTable.Cust_phone] = customer.cust_phone
           }
        }
    }

    // When User Exist in  table

    suspend fun findCustomerById(id : String) = Database_Factory.dbQuery {
        CustomerTable.select { CustomerTable.Cust_id.eq(id) }
            .map {
                rowToCustomer(it)
            }.singleOrNull()
    }


    private  fun rowToCustomer(row: ResultRow) : Customer?{
        if (row == null) {

            return null
        }
        return Customer(
            cust_id = row[CustomerTable.Cust_id],
            cust_name =  row[CustomerTable.Cust_name],
            cust_address =  row[CustomerTable.Cust_address],
            address_pincode = row[CustomerTable.Address_pincode],
            cust_email = row[CustomerTable.Cust_email],
            cust_password = row[CustomerTable.Cust_password],
            cust_phone = row[CustomerTable.Cust_phone]
        )
    }
}

