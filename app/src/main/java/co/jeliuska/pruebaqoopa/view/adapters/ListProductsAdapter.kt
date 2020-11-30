package co.jeliuska.pruebaqoopa.view.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import co.jeliuska.pruebaqoopa.R
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.databinding.ItemListProductsBinding
import com.bumptech.glide.Glide
import java.util.*

class ListProductsAdapter(private val context: Context) :
    BaseAdapter<ProductsEntity, ItemListProductsBinding>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<ProductsEntity>() {
        override fun areItemsTheSame(
            oldItem: ProductsEntity,
            newItem: ProductsEntity
        ): Boolean {
            return oldItem.idProduct == newItem.idProduct
        }

        override fun areContentsTheSame(
            oldItem: ProductsEntity,
            newItem: ProductsEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes() = R.layout.item_list_products

    override fun onBind(holder: DataBindingViewHolder, position: Int, item: ProductsEntity) {
        dataBinding.apply {
            dataProducts = item
            clickProducts = View.OnClickListener {
                itemClickListener(position, item)
            }

        }
        if (item.image != null)
            Glide.with(dataBinding.root).load(item.image).into(dataBinding.profileImage)

    }
}