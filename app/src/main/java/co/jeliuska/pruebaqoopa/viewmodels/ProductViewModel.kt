package co.jeliuska.pruebaqoopa.viewmodels

import androidx.lifecycle.*
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.data.repository.ProductsRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ProductViewModel(
    val productsRepository: ProductsRepository) : ViewModel(){

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun updateProduct(product: ProductsEntity) {
        viewModelScope.launch {
            productsRepository.updateProduct(product)
        }
    }
    fun createProduct(product: ProductsEntity) {
        viewModelScope.launch {
            productsRepository.createProduct(product)
        }
    }

    suspend fun getProduct(idProduct: Long): ProductsEntity {
        return productsRepository.getProduct(idProduct)
    }

}

class ProductViewModelFactory(
    val productsRepository: ProductsRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ProductViewModel(
            productsRepository
        ) as T
}
