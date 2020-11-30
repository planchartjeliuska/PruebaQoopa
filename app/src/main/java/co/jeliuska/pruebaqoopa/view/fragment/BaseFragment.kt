package co.jeliuska.pruebaqoopa.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<U : ViewDataBinding> : Fragment() {

    protected lateinit var dataBinding: U

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        initView()

        return dataBinding.root
    }

    fun showToast(message: String){
        Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
    }
}
