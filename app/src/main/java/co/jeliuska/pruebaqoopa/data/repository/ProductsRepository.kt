package co.jeliuska.pruebaqoopa.data.repository

import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.data.local.dao.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository(private val daoProducts: ProductsDao){
    suspend fun getProducts(): List<ProductsEntity> = daoProducts.getProducts()

    suspend fun createProduct(products: ProductsEntity) {
        withContext(Dispatchers.IO) {
            daoProducts.createProduct(products)
        }
    }

    suspend fun updateProduct(products: ProductsEntity) {
        withContext(Dispatchers.IO) {
            daoProducts.updateProduct(products)
        }
    }

    suspend fun getProduct(idProduct: Long): ProductsEntity {
        return daoProducts.getProduct(idProduct)
    }


    companion object {
        @Volatile
        private var instance: ProductsRepository? = null
        fun getInstance(daoProducts: ProductsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: ProductsRepository(daoProducts).also { instance = it }
            }
    }
}
