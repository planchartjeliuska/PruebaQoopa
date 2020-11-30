package co.jeliuska.pruebaqoopa.view.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.jeliuska.pruebaqoopa.R
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.databinding.FragmentEditProductBinding
import co.jeliuska.pruebaqoopa.libs.injectors.InjectorViewModels
import co.jeliuska.pruebaqoopa.viewmodels.ProductViewModel
import com.google.android.material.textfield.TextInputLayout
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.coroutines.launch


class EditProductFragment: BaseFragment<FragmentEditProductBinding>() {

    private val args: EditProductFragmentArgs by navArgs()

    private val productViewModel : ProductViewModel by viewModels {
        InjectorViewModels.provideProductViewModel(requireContext())
    }

    override fun getLayoutRes() = R.layout.fragment_edit_product

    override fun initView() {

        dataBinding.apply {
            clickSaveProduct = View.OnClickListener { validateForm() }
            clickSelectDate = View.OnClickListener { selectDate() }
        }

        if (args.idProduct > -1) {
            dataBinding.toolbar.title = "Edita el producto"
            getLocalProduct()
        }else
            dataBinding.toolbar.title = "Crea un producto nuevo"

    }

    private fun selectDate() {
        DatePickerDialog.newInstance { view, year, monthOfYear, dayOfMonth ->
            dataBinding.tvDateProduct.text = "$year/$monthOfYear/$dayOfMonth"
        }.show(parentFragmentManager,"")
    }

    private fun getLocalProduct() {
        viewLifecycleOwner.lifecycleScope.launch {
            fillDataProduct(productViewModel.getProduct(args.idProduct))
        }
    }

    private fun fillDataProduct(productsEntity: ProductsEntity) {
        dataBinding.apply {
            editDescriptionProduct.editText?.setText(productsEntity.productsDescription)
            editNameProduct.editText?.setText(productsEntity.productName)
            editUrlImageProduct.editText?.setText(productsEntity.image)
            tvDateProduct.text = productsEntity.date
        }
    }

    private fun validateForm() {
        val nameProduct = dataBinding.editNameProduct.getText()
        val descriptionProduct = dataBinding.editDescriptionProduct.getText()
        val urlImageProduct = dataBinding.editUrlImageProduct.getText()
        val dateProduct = dataBinding.tvDateProduct.text.let {
            if (it.isNullOrBlank())
                null
            else
                it.toString()
        }


        if (nameProduct != null && descriptionProduct != null && dateProduct != null){
            saveOrUpdateProduct(ProductsEntity(nameProduct,dateProduct,descriptionProduct,urlImageProduct))
        }else{
            showToast("Debe ingresar todos los datos requeridos")
        }

    }

    private fun saveOrUpdateProduct(product : ProductsEntity) {
        if (args.idProduct > -1) {
            product.idProduct = args.idProduct
            productViewModel.updateProduct(product)
        }else
            productViewModel.createProduct(product)
        findNavController().popBackStack()
    }


}

private fun TextInputLayout.getText(): String? {
    return editText?.text.let {
        if (it.isNullOrBlank())
            null
        else
            it.toString()
    }
}
