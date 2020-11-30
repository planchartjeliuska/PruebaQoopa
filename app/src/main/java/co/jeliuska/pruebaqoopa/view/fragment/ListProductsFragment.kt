package co.jeliuska.pruebaqoopa.view.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.jeliuska.pruebaqoopa.R
import co.jeliuska.pruebaqoopa.databinding.FragmentListProductsBinding
import co.jeliuska.pruebaqoopa.libs.injectors.InjectorViewModels
import co.jeliuska.pruebaqoopa.view.adapters.ListProductsAdapter
import co.jeliuska.pruebaqoopa.viewmodels.ListProductsViewModel
import kotlinx.android.synthetic.main.fragment_list_products.*
import kotlinx.coroutines.launch

class ListProductsFragment: BaseFragment<FragmentListProductsBinding>() {

    private val listProductsViewModel : ListProductsViewModel by viewModels {
        InjectorViewModels.provideListProductsViewModel(requireContext())
    }

    override fun getLayoutRes() = R.layout.fragment_list_products

    private val listProductsAdapter: ListProductsAdapter by lazy {
        ListProductsAdapter(requireContext())
    }

    override fun initView() {
        dataBinding.apply {
            rvListProducts.adapter = listProductsAdapter
            clickCreateProducts = View.OnClickListener { goToCreateProduct() }
        }
        loadProducts()
        listProductsAdapter.itemClickListener = { position, product ->
            findNavController().navigate(ListProductsFragmentDirections.actionListProductsFragmentToEditProductFragment(product.idProduct))
        }
    }

    private fun loadProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            listProductsAdapter.submitList(listProductsViewModel.getListProducts())
        }
    }

    private fun goToCreateProduct() {
        findNavController().navigate(ListProductsFragmentDirections.actionListProductsFragmentToEditProductFragment())
    }

}