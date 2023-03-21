package com.example.repository

import com.example.data.model.Accessories
import com.example.data.model.ProductColor
import com.example.data.model.Mobile
import com.example.data.model.Product
import com.example.data.table.*
import com.example.repository.Database_Factory.dbQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


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
                pt[ProductTable.Cate_name] = product.cate_id
            }
            product.productColor.forEach()
            {
                ColorTable.insert { ct->
//                    ct[ColorTable.Color_id] = it.color_id
                    ct[ColorTable.Color_name] = it.color_name
                    ct[ColorTable.Product_Image] = it.product_image
                    ct[ColorTable.Product_id] = product.product_id
                }
            }
            if (product.Mobile != null )
            {
                product.Mobile!!.forEach()
                {
                         MobileTable.insert { mt->
                        mt[MobileTable.Mobile_id] = it.mobile_id
                        mt[MobileTable.Product_id] = product.product_id
                        mt[MobileTable.Ram] = it.ram
                        mt[MobileTable.Storage] = it.storage
                        mt[MobileTable.Price] = it.price
                    }
                }
            }
            else
            {
                product.Accessories?.forEach()
                {
                    AccessoriesTable.insert { at->
                        at[AccessoriesTable.Access_id] = it.access_id
                        at[AccessoriesTable.Product_id] = product.product_id
                        at[AccessoriesTable.Specification] = it.specification
                        at[AccessoriesTable.Price] = it.price
                    }
                }
            }
        }
    }



    fun getOneProduct(id : Int) = transaction {
        ProductTable.select {
            ProductTable.Product_id.eq(id)
        }.map {
            rowToProduct(it)
        }.singleOrNull()
    }

    suspend fun getColorByProductId(id :Int) : List<ProductColor> = dbQuery {
        ColorTable.select {
            ColorTable.Product_id.eq(id)
        }.map {
            rowToColor(it)
        }
    }

    suspend fun getMobileByProductId(id : Int) : List<Mobile> = dbQuery {
        MobileTable.select {
            MobileTable.Product_id.eq(id)
        }.map {
            rowToMobile(it)
        }
    }

    suspend fun getAccessByProductId(id : Int) : List<Accessories> = dbQuery {
        AccessoriesTable.select {
            AccessoriesTable.Product_id.eq(id)
        }.map {
            rowToAccessories(it)
        }
    }

    suspend fun getMobileProductByMobileId(id : Int) = dbQuery {
        MobileTable.select {
            MobileTable.Mobile_id.eq(id)
        }.map {
                rowToMobile(it)
        }.singleOrNull()
    }

    suspend fun getAccessoriesProductByAccessId(id : Int) = dbQuery {
        AccessoriesTable.select {
            AccessoriesTable.Access_id.eq(id)
        }.map {
            rowToAccessories(it)
        }.singleOrNull()
    }

    suspend fun getAllProduct() : List<Product?> = dbQuery {
        ProductTable.selectAll().map {
            rowToProduct(it)
        }
    }

    suspend fun getAllMobile() : List<Mobile?> = dbQuery {
        MobileTable.selectAll().map {
            rowToMobile(it)
        }
    }

    suspend fun getAllAccessories() : List<Accessories?> = dbQuery {
        AccessoriesTable.selectAll().map {
            rowToAccessories(it)
        }
    }


    private fun rowToColor(row: ResultRow): ProductColor{
       return ProductColor(
           color_id = row[ColorTable.Color_id],
           color_name = row[ColorTable.Color_name],
           product_image = row[ColorTable.Product_Image]
       )
    }

    private fun rowToProduct(row: ResultRow) : Product{

        var productColor = mutableListOf<ProductColor>()
        CoroutineScope(Dispatchers.IO).launch {
            productColor = getColorByProductId(row[ProductTable.Product_id]).toMutableList()
        }
        var mobile = mutableListOf<Mobile>()
        CoroutineScope(Dispatchers.IO).launch {
            mobile = getMobileByProductId(row[ProductTable.Product_id]).toMutableList()
        }
        var access = mutableListOf<Accessories>()
        CoroutineScope(Dispatchers.IO).launch {
            access = getAccessByProductId(row[ProductTable.Product_id]).toMutableList()
        }
        return Product(
            product_id = row[ProductTable.Product_id],
            product_name = row[ProductTable.Product_name],
            product_desc = row[ProductTable.Product_desc],
            cate_id =  row[ProductTable.Cate_name],
            productColor = productColor,
            brand_id = row[ProductTable.Brand_id],
            Mobile = mobile,
            Accessories = access
        )
    }

    private fun rowToMobile(row: ResultRow) : Mobile{

        return Mobile(
            mobile_id = row[MobileTable.Mobile_id],
            product_id = row[MobileTable.Product_id],
            ram = row[MobileTable.Ram],
            storage = row[MobileTable.Storage],
            price = row[MobileTable.Price]
        )
    }

    private fun rowToAccessories(row: ResultRow) : Accessories{

            return Accessories(
                access_id = row[AccessoriesTable.Access_id],
                specification = row[AccessoriesTable.Specification],
                price = row[AccessoriesTable.Price]
            )
        }

    suspend fun updateProduct(product: Product) {
        dbQuery {
            ProductTable.update(where = { ProductTable.Product_id.eq(product.product_id) } ){ pu->
                pu[ProductTable.Product_id] = product.product_id
                pu[ProductTable.Product_name] = product.product_name
                pu[ProductTable.Product_desc] = product.product_desc
                pu[ProductTable.Cate_name] = product.cate_id
            }

            product.productColor.forEach()
            {
                ColorTable.update(where = {ColorTable.Color_id.eq(it.color_id)}) { ct->
                    ct[ColorTable.Product_id] = product.product_id
                    ct[ColorTable.Color_name] = it.color_name
                    ct[ColorTable.Product_Image] = it.product_image
                }
            }

            if (product.Mobile != null)
            {
                product.Mobile!!.forEach()
                {
                    MobileTable.update(where = {MobileTable.Mobile_id.eq(it.mobile_id)}) { mt->
                        mt[MobileTable.Product_id] = product.product_id
                        mt[MobileTable.Ram] = it.ram
                        mt[MobileTable.Storage] = it.storage
                        mt[MobileTable.Price] = it.price
                    }
                }
            }
            else
            {
                product.Accessories?.forEach()
                {
                    AccessoriesTable.update(where = {AccessoriesTable.Access_id.eq(it.access_id)}){ at->
                        at[AccessoriesTable.Product_id] = product.product_id
                        at[AccessoriesTable.Specification] = it.specification
                        at[AccessoriesTable.Price] = it.price
                    }
                }
            }
        }
    }

    suspend fun deleteProduct(product: Int) : Int
    {
       return dbQuery {

           ColorTable.deleteWhere { ColorTable.Product_id.eq(product) }

           MobileTable.deleteWhere {MobileTable.Product_id.eq(product) }

           AccessoriesTable.deleteWhere { AccessoriesTable.Product_id.eq(product) }

           ProductTable.deleteWhere { ProductTable.Product_id.eq(product) }

       }
    }

    suspend fun productExists(id : Int) = dbQuery {
            ProductTable.select { ProductTable.Product_id.eq(id) }.count() == 1L
        }


}

