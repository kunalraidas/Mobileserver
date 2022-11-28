package com.example.repository

import com.example.data.model.IMEI_no
import com.example.data.model.Mobile
import com.example.data.table.IMEI_NO_Table
import com.example.data.table.MobileTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*

class IMEI_NO_Repo
{
    suspend fun addIMEI(imeiNo: IMEI_no)
    {
        dbQuery {
            IMEI_NO_Table.insert { im->
                im[IMEI_NO_Table.IMEI_NO] = imeiNo.imei_no
                im[IMEI_NO_Table.Mobile_id] = imeiNo.mobile_id
            }
        }
    }

    suspend fun getIMEINO(imei : Long) = dbQuery {
        IMEI_NO_Table.select {
            IMEI_NO_Table.IMEI_NO.eq(imei)
        }.map {
            rowToIMEINO(it)
        }.singleOrNull()
    }

    suspend fun getIMEINOByMobileID(id : Int) = dbQuery {
        MobileTable.select {
            MobileTable.Mobile_id.eq(id)
        }.map {
            rowToIMEINO(it)
        }.singleOrNull()
    }

    suspend fun getAllIMEINo() : List<IMEI_no?> = dbQuery {
        IMEI_NO_Table.selectAll().map { rowToIMEINO(it) }
    }



    suspend fun deleteIMEINo(imei : Long)
    {
        return dbQuery {
            IMEI_NO_Table.deleteWhere { IMEI_NO_Table.IMEI_NO.eq(imei) }
        }
    }

    private fun rowToIMEINO(row: ResultRow) : IMEI_no?{
        if (row == null)
        {
            return null
        }
        return IMEI_no(
            imei_no = row[IMEI_NO_Table.IMEI_NO],
            mobile_id = row[IMEI_NO_Table.Mobile_id]
        )
    }


}