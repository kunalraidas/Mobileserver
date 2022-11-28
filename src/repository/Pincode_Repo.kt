package com.example.repository


import com.example.data.model.Pincode
import com.example.repository.Database_Factory.dbQuery
import com.example.data.table.PincodeTable
import org.jetbrains.exposed.sql.*

class Pincode_Repo {
    suspend fun addPincode(pincode: Pincode)
    {
        Database_Factory.dbQuery {
            PincodeTable.insert { pt->
                pt[PincodeTable.Pincode] = pincode.pincode
                pt[PincodeTable.Area_name] = pincode.area_name
                pt[PincodeTable.Delivery_Charge] = pincode.delivery_charge
            }
        }
    }

    suspend fun findAreaByPincode(pincode : Int) = Database_Factory.dbQuery {
        PincodeTable.select {
            PincodeTable.Pincode.eq(pincode)
        }.map {
            rowToPincode(it)
        }.singleOrNull()
    }

    suspend fun getAllPincode() : List<Pincode?> = Database_Factory.dbQuery {
        PincodeTable.selectAll().map {
            rowToPincode(it)
        }
    }

    suspend fun deletePincode(pincode : Int)
    {
        return dbQuery {
            PincodeTable.deleteWhere {
                PincodeTable.Pincode.eq(pincode)
            }
        }
    }

    private fun rowToPincode(row: ResultRow) : Pincode?{
        if (row == null) return null
        return Pincode(
            pincode = row[PincodeTable.Pincode],
            area_name = row[PincodeTable.Area_name],
            delivery_charge = row[PincodeTable.Delivery_Charge]
        )
    }

}