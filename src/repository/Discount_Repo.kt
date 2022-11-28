package com.example.repository


import com.example.data.model.Discount
import com.example.data.table.*
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*


class Discount_Repo
{
        suspend fun addDiscount(discount: Discount)
        {
            dbQuery {
                DiscountTable.insert { d->
                    d[DiscountTable.Coupon_code] = discount.coupon_code
                    d[DiscountTable.Discount_perc] = discount.discount_perc
                    d[DiscountTable.Total_Coupon] = discount.total_coupon
                }
            }
        }

        suspend fun findDiscountByCouponCode(coupon_code : String) = dbQuery {
            DiscountTable.select {
                DiscountTable.Coupon_code.eq(coupon_code)
            }.map {
                rowToDiscount(it)
            }.singleOrNull()
        }

        suspend fun getAllCouponCode() : List<Discount?> = dbQuery {
            DiscountTable.selectAll().map {
                rowToDiscount(it)
            }
        }
//    suspend fun getAllCustomer() : List<Customer?> = dbQuery {
//        CustomerTable.selectAll().map {
//            rowToCustomer(it)
//        }
//    }


        private fun rowToDiscount(row: ResultRow):Discount
        {
            return Discount(
                coupon_code = row[DiscountTable.Coupon_code],
                discount_perc = row[DiscountTable.Discount_perc],
                total_coupon = row[DiscountTable.Total_Coupon]
            )
        }

        suspend fun deleteDiscount(coupon_code : String)
        {
            return dbQuery {
                DiscountTable.deleteWhere {
                    DiscountTable.Coupon_code.eq(coupon_code)
                }
            }
        }



}