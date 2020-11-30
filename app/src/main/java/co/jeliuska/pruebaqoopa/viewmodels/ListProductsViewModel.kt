package co.jeliuska.pruebaqoopa.viewmodels

import androidx.lifecycle.*
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.data.repository.ProductsRepository
import kotlinx.coroutines.cancel

class ListProductsViewModel(
    val productsRepository: ProductsRepository) : ViewModel(){

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    suspend fun getListProducts():List<ProductsEntity>{
        return productsRepository.getProducts()
    }


}

class ListProductsViewModelFactory(
    val productsRepository: ProductsRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ListProductsViewModel(
            productsRepository
        ) as T
}
