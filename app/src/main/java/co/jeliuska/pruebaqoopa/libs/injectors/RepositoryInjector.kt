package co.jeliuska.pruebaqoopa.libs.injectors

import android.content.Context
import co.jeliuska.pruebaqoopa.data.local.AppDatabase
import co.jeliuska.pruebaqoopa.data.repository.ProductsRepository

object RepositoryInjector {
    fun getProductsRepository(
        context: Context
    ): ProductsRepository {
        return ProductsRepository.getInstance(
            AppDatabase.getInstance(context).productsDao()
        )
    }

}
