package com.example.repository

import com.example.data.model.Customer
import com.example.data.table.CustomerTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*

class Customer_Repo{
    // AddCustomer
    suspend fun addCustomer(customer: Customer){
        dbQuery {
            CustomerTable.insert { ct->
                ct[CustomerTable.Email] = customer.email
                ct[CustomerTable.Password] = customer.password
                ct[CustomerTable.First_name] = customer.first_name
                ct[CustomerTable.Last_name] = customer.last_name
                ct[CustomerTable.Phone_no] = customer.phone_no
                ct[CustomerTable.Cust_Address] = customer.cust_Address
                ct[CustomerTable.Delivery_Address] = customer.delivery_address
                ct[CustomerTable.Pincode] = customer.pincode
            }
        }
    }

    // Find Customer
    suspend fun findCustomerByEmail(email : String) = dbQuery {
        CustomerTable.select { CustomerTable.Email.eq(email) }
            .map {
                rowToCustomer(it) }
            .singleOrNull()
    }

    // Customer Details in row
    private fun rowToCustomer(row: ResultRow?):Customer?{
        if (row == null)
        {
            return null
        }
        return Customer(
            email = row[CustomerTable.Email],
            password = row[CustomerTable.Password],
            first_name = row[CustomerTable.First_name],
            last_name = row[CustomerTable.Last_name],
            phone_no = row[CustomerTable.Phone_no],
            cust_Address = row[CustomerTable.Cust_Address],
            delivery_address = row[CustomerTable.Delivery_Address],
            pincode = row[CustomerTable.Pincode]
        )
    }

    // Update Customer Details
    suspend fun updateCustomer(customer: Customer) = dbQuery {
        CustomerTable.update(where =  { CustomerTable.Email.eq(customer.email)} ){ c->
            c[CustomerTable.First_name] = customer.first_name
            c[CustomerTable.Last_name] = customer.last_name
            c[CustomerTable.Phone_no] = customer.phone_no
            c[CustomerTable.Cust_Address] = customer.cust_Address
            c[CustomerTable.Delivery_Address] = customer.delivery_address
            c[CustomerTable.Pincode] = customer.pincode
        }
    }

    // Get All customer
    suspend fun getAllCustomer() : List<Customer?> = dbQuery {
        CustomerTable.selectAll().map {
            rowToCustomer(it)
        }
    }

    // Customer is already exist in database
    suspend fun customerExists(email: String):Boolean{
        return dbQuery {
           CustomerTable.select {
               CustomerTable.Email.eq(email)
           }.count() == 1L
        }
    }
}



