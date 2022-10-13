package com.example.repository

import com.example.data.model.Admin
import com.example.data.table.AdminTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AdminRepo
{
   // When Admin Enter In Server
    suspend fun addAdmin(admin: Admin){
        Database_Factory.dbQuery {
            AdminTable.insert { at->
                at[AdminTable.Admin_id] = admin.admin_id
                at[AdminTable.Admin_name] = admin.admin_name
                at[AdminTable.Admin_email] = admin.admin_email
                at[AdminTable.Admin_password] = admin.admin_password
            }
        }
    }

    // When Admin Is Already Exists
    suspend fun findAdminById(adminId : Int) = Database_Factory.dbQuery {
        AdminTable.select {
            AdminTable.Admin_id.eq(adminId)
        }.map {
            rowToAdmin(it)
        }.singleOrNull()
    }


    private  fun rowToAdmin(row: ResultRow?) : Admin?{
        if (row == null)
        {
            return null
        }
        return Admin(
            admin_id = row[AdminTable.Admin_id],
            admin_name = row[AdminTable.Admin_name],
            admin_email = row[AdminTable.Admin_email],
            admin_password = row[AdminTable.Admin_password]
        )
    }
}


//    private fun rowToUser(row: ResultRow?): User? {
//        if (row == null) {
//            return null
//        }
//        return User(
//            UserName = row[UserTable.username],
//            Email = row[UserTable.email],
//            Password = row[UserTable.password]
//        )
//    }
//
//}