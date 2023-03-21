package com.example.repository

import com.example.data.model.Admin
import com.example.data.response.Simple_Response
import com.example.data.table.AdminTable
import com.example.repository.Database_Factory.dbQuery
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Admin_Repo
{
    suspend fun addAdmin(admin: Admin)
    {
        dbQuery {
            AdminTable.insert { at->
                at[AdminTable.Admin_name] = admin.admin_name
                at[AdminTable.Admin_email] = admin.admin_email
                at[AdminTable.Admin_password] = admin.admin_password
            }
        }
    }

    suspend fun findAdminByEmail(email : String) : Admin? = dbQuery {

            AdminTable.select { AdminTable.Admin_email.eq(email) }
                .map {
                    rowToAdmin(it) }
                .singleOrNull()
    }

    private fun rowToAdmin(row: ResultRow):Admin?{
        if (row  == null) {
            return null
        }
        return Admin(
            admin_name = row[AdminTable.Admin_name],
            admin_email = row[AdminTable.Admin_email],
            admin_password = row[AdminTable.Admin_password]
        )
    }

}