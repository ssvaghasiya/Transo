package com.example.transoapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.transoapp.MyApp
import com.example.transoapp.R
import com.example.transoapp.databinding.ActivityDetailBinding
import com.example.transoapp.listener.EventListener
import com.example.transoapp.pojo.ExampleData
import com.example.transoapp.ui.adapter.DetailItemAdapter
import com.example.transoapp.viewmodels.MainViewModel
import com.example.transoapp.viewmodels.MainViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityDetailBinding
    lateinit var adapter: DetailItemAdapter
    var exampleList = ArrayList<ExampleData>()
    var selectedPosition: Int = 0

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        (application as MyApp).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        supportActionBar?.hide()
        getArgs()
        initView()
    }

    private fun getArgs() {
        selectedPosition = intent.getIntExtra("POSITION", 0)
        val jsonData = intent.getStringExtra("DATA_LIST")
        exampleList = Gson().fromJson(
            jsonData,
            object : TypeToken<ArrayList<ExampleData>>() {}.type
        )
    }

    private fun initView() = with(binding) {
        setAdapter()
    }

    private fun setAdapter() = with(binding) {
        adapter = DetailItemAdapter(exampleList, object : EventListener<ExampleData> {
            override fun onItemClick(pos: Int, item: ExampleData, view: View) {

            }
        })
        recyclerview.adapter = adapter
        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(recyclerview)
        recyclerview.scrollToPosition(selectedPosition)
    }

}