package com.example.repository

import com.example.data.model.Color
import com.example.data.model.Product
import com.example.data.table.*
import com.example.repository.Database_Factory.dbQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.*


class Product_Repo
{
    suspend fun addproduct(product: Product)
    {
        dbQuery {
            ProductTable.insert { pt->
                pt[ProductTable.Product_id] = product.product_id
                pt[ProductTable.Product_name] = product.product_name
                pt[ProductTable.Product_desc] = product.product_desc
                pt[ProductTable.Brand_id] = product.brand_id
                pt[ProductTable.Cate_name] = product.cate_name
            }
            product.color.forEach()
            {
                ColorTable.insert { ct->
                    ct[ColorTable.Color_id] = it.color_id
                    ct[ColorTable.Color_name] = it.color_name
                    ct[ColorTable.Product_Image] = it.product_image
                    ct[ColorTable.Product_id] = product.product_id
                }
            }
            if (product.Mobile != null )
            {
                MobileTable.insert { mt->
                        mt[MobileTable.Mobile_id] = product.Mobile!!.mobile_id
                        mt[MobileTable.Product_id] = product.product_id
                        mt[MobileTable.Ram] = product.Mobile!!.ram
                        mt[MobileTable.Storage] = product.Mobile!!.storage
                        mt[MobileTable.Price] = product.Mobile!!.price
                }
            }
            else
            {
                AccessoriesTable.insert { at->
                    at[AccessoriesTable.Access_id] = product.Accessories!!.access_id
                    at[AccessoriesTable.Product_id] = product.product_id
                    at[AccessoriesTable.Specification] = product.Accessories!!.specification
                    at[AccessoriesTable.Price] = product.Accessories!!.price
                }
            }
        }

    }





    suspend fun getOneProduct(id : Int) = dbQuery {
        ProductTable.select {
            ProductTable.Product_id.eq(id)
        }.map {
            rowToProduct(it)
        }.singleOrNull()
    }

    suspend fun getAllProduct() : List<Product?> = dbQuery {
        ProductTable.selectAll().map { rowToProduct(it) }
    }



    suspend fun getColorByProductId(id :Int) : List<Color> = dbQuery {
        ColorTable.select {
            ColorTable.Product_id.eq(id)
        }.map {
            rowToColor(it)
        }
    }

    private fun rowToColor(row: ResultRow): Color{
       return Color(
           color_id = row[ColorTable.Color_id],
           color_name = row[ColorTable.Color_name],
           product_image = row[ColorTable.Product_Image]
       )
    }

    private fun rowToProduct(row: ResultRow) : Product?{

        var color = mutableListOf<Color>()
        CoroutineScope(Dispatchers.IO).launch {
            color = getColorByProductId(row[ColorTable.Product_id]).toMutableList()
        }
        return Product(
            product_id = row[ProductTable.Product_id],
            product_name = row[ProductTable.Product_name],
            product_desc = row[ProductTable.Product_desc],
            cate_name =  row[ProductTable.Cate_name],
            color = color,
            brand_id = row[BrandTable.brand_id]
        )
    }

    suspend fun updateProduct(product: Product) {
        dbQuery {
            ProductTable.update(where = { ProductTable.Product_id.eq(product.product_id) } ){ pu->
                pu[ProductTable.Product_id] = product.product_id
                pu[ProductTable.Product_name] = product.product_name
                pu[ProductTable.Product_desc] = product.product_desc
                pu[ProductTable.Cate_name] = product.cate_name
            }

            product.color.forEach()
            {
                ColorTable.update(where = {ColorTable.Color_id.eq(it.color_id)}) { ct->
                    ct[ColorTable.Product_id] = product.product_id
                    ct[ColorTable.Color_name] = it.color_name
                    ct[ColorTable.Product_Image] = it.product_image
                }
            }
        }
    }
    suspend fun deleteProduct(product: Product) : Int
    {
       return dbQuery {
            ProductTable.deleteWhere { ProductTable.Product_id.eq(product.product_id) }
        }
    }

    suspend fun productExists(id : Int) : Boolean
    {
        return dbQuery {
            ProductTable.select { ProductTable.Product_id.eq(id) }
        }.count() == 1L
    }

}

