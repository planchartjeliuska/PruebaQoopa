package co.jeliuska.pruebaqoopa.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.jeliuska.pruebaqoopa.libs.extensions.ItemClickListener

abstract class BaseAdapter<T, U : ViewDataBinding>(val diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    lateinit var itemClickListener: ItemClickListener<T>

    protected lateinit var dataBinding: U

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun onBind(holder: DataBindingViewHolder, position: Int, item: T)

    class DataBindingViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutRes(),
            parent,
            false
        )
        return DataBindingViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBind(
            holder as DataBindingViewHolder,
            holder.adapterPosition,
            getItem(holder.adapterPosition)
        )
    }


}
