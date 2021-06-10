package com.sinue.pagatodo.mx.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinue.pagatodo.mx.data.api.ApiHelper
import com.sinue.pagatodo.mx.data.api.RetrofitBuilder
import com.sinue.pagatodo.mx.data.model.UserTransaction
import com.sinue.pagatodo.mx.data.repository.MainRepository
import com.sinue.pagatodo.mx.databinding.ActivityMainBinding
import com.sinue.pagatodo.mx.ui.base.ViewModelFactory
import com.sinue.pagatodo.mx.ui.main.adapter.MainAdapter
import com.sinue.pagatodo.mx.ui.main.viewmodel.MainViewModel
import com.sinue.pagatodo.mx.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getTransactions(MainRepository.ResponseType.SUCCESS)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            resource.data?.let { transactions -> retrieveList(transactions) }
                        }
                        Status.ERROR -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
    }

    private fun retrieveList(transaction: List<UserTransaction>) {
        adapter.apply {
            addTransactions(transaction)
            notifyDataSetChanged()
        }
    }
}