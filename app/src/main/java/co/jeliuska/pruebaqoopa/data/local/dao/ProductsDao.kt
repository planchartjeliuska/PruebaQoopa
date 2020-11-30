package co.jeliuska.pruebaqoopa.data.local.dao

import androidx.room.*
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductsEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProduct(products: ProductsEntity):Long

    @Update
    suspend fun updateProduct(products: ProductsEntity)

    @Query("SELECT * FROM products WHERE idProduct =:idProduct")
    suspend fun getProduct(idProduct: Long): ProductsEntity
}
