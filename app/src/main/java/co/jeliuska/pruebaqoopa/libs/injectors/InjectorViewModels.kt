package co.jeliuska.pruebaqoopa.libs.injectors

import android.content.Context
import co.jeliuska.pruebaqoopa.viewmodels.ListProductsViewModelFactory
import co.jeliuska.pruebaqoopa.viewmodels.ProductViewModelFactory

object InjectorViewModels {


    fun provideListProductsViewModel(context: Context): ListProductsViewModelFactory {
        return ListProductsViewModelFactory(
            RepositoryInjector.getProductsRepository(context)
        )
    }


    fun provideProductViewModel(context: Context): ProductViewModelFactory {
        return ProductViewModelFactory(
            RepositoryInjector.getProductsRepository(context)
        )
    }
}
