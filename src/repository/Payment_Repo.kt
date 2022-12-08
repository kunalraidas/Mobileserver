package com.example.repository

import com.example.data.model.Order
import com.example.data.model.Payment
import com.example.data.table.InvoiceTable
import com.example.data.table.PaymentTable
import com.example.data.table.UserInvoiceTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDate
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

class Payment_Repo {

    suspend fun addTransaction(order: Order,delivery_charge: Float) = dbQuery {

        val paymentId = "transaction_id" + System.currentTimeMillis()

        val invoiceId = "invoice_id" + System.currentTimeMillis()

        PaymentTable.insert {  pt->
            pt[PaymentTable.payment_id] =  paymentId
            pt[email] = order.Email
            pt[Payment_method] = 0
            pt[PaymentTable.delivery_charge] = delivery_charge
            pt[date] = LocalDate.now()
            pt[PaymentTable.amount] = order.total + delivery_charge
            pt[PaymentTable.order_id] = order.order_id
        }

        InvoiceTable.insert { invoice->
            invoice[InvoiceTable.Invoice_id] = invoiceId
            invoice[date] = LocalDate.now()
            invoice[InvoiceTable.delivery_charge] = delivery_charge
            invoice[InvoiceTable.payment_id] = paymentId
            invoice[email_id] = order.Email
        }

        UserInvoiceTable.insert { ut->
            ut[UserInvoiceTable.invoice_id] = invoiceId
            ut[UserInvoiceTable.email] = order.Email
        }

    }

    suspend fun getAllTransaction() : List<Payment> = dbQuery {
        PaymentTable.selectAll().map {
            rowToPayment(it)
        }
    }

    suspend fun getPaymentIdByEmailId(email : String): List<Payment> = dbQuery {
        PaymentTable.select {
            PaymentTable.email.eq(email)
        }.map {
            rowToPayment(it)
        }
    }

    suspend fun filterPayment(startDate : LocalDate,endDate : LocalDate) : List<Payment> = dbQuery {
        PaymentTable.select {
            PaymentTable.date.between(startDate,endDate)
        }.map {
            rowToPayment(it)
        }
    }

    fun rowToPayment(row: ResultRow) : Payment{
        return Payment(
            payment_id = row[PaymentTable.payment_id],
            emailId = row[PaymentTable.email],
            payment_method = row[PaymentTable.Payment_method],
            delivery_charge = row[PaymentTable.delivery_charge],
            date = row[PaymentTable.date],
            amount = row[PaymentTable.amount],
            orderId = row[PaymentTable.order_id]
        )
    }

}