package com.example.queenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recycle by lazy { findViewById<RecyclerView>(R.id.recycleMain) }
    private val loading by lazy { findViewById<ProgressBar>(R.id.loading) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(QueenViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.errorMessage.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }

        viewModel.loading.observe(this){
            if (it){
                loading.visibility = VISIBLE
            }else{
                loading.visibility = GONE
            }
        }

        recycle.layoutManager = LinearLayoutManager(this)
        viewModel.listQueen.observe(this){ queens->
            recycle.adapter = QueenAdapter(queens)
        }
    }
}