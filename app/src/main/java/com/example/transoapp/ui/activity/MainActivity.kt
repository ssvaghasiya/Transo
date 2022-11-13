package com.example.transoapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.transoapp.MyApp
import com.example.transoapp.R
import com.example.transoapp.databinding.ActivityMainBinding
import com.example.transoapp.listener.EventListener
import com.example.transoapp.pojo.ExampleData
import com.example.transoapp.ui.adapter.ExampleAdapter
import com.example.transoapp.utils.Utils.isNetworkAvailable
import com.example.transoapp.utils.Utils.setProgressDialog
import com.example.transoapp.viewmodels.MainViewModel
import com.example.transoapp.viewmodels.MainViewModelFactory
import com.google.gson.Gson
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ExampleAdapter
    var exampleList = ArrayList<ExampleData>()
    var dialog: AlertDialog? = null

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as MyApp).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        dialog = setProgressDialog(this, "Loading..")

        setObserver()
        initView()
    }

    private fun setObserver() {
        mainViewModel.exampleLiveData.observe(this, Observer {
            dialog?.dismiss()
            if (it != null) { //set response data
                exampleList.clear()
                exampleList.addAll(it)
                adapter.notifyDataSetChanged()
            } else {
                showToast(getString(R.string.something_wrong_validation))
            }
        })
    }

    private fun initView() = with(binding) {
        setAdapter()
        dialog?.show()
        callApi()
    }

    private fun setAdapter() = with(binding) {
        adapter = ExampleAdapter(exampleList, object : EventListener<ExampleData> {
            override fun onItemClick(pos: Int, item: ExampleData, view: View) {
                val i = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra("POSITION", pos)
                i.putExtra("DATA_LIST", Gson().toJson(exampleList))
                startActivity(i)
            }
        })
        recyclerview.adapter = adapter

    }


    private fun callApi() = with(binding) {
        if (isNetworkAvailable(this@MainActivity)) {
            mainViewModel.callApi()
        } else {
            showToast(getString(R.string.internet_validation))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}