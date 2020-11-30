package co.jeliuska.pruebaqoopa.view.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dmax.dialog.SpotsDialog

abstract class BaseActivity<D : ViewDataBinding>: AppCompatActivity() {

    var dialog: AlertDialog? = null

    lateinit var dataBinding: D

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes())

    }


    fun showProgressDialog() {
        dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Cargando")
            .setCancelable(false)
            .build()
            .apply {
                show()
            }
    }

    fun closeProgressDialog(){
        dialog?.dismiss()
    }


}
