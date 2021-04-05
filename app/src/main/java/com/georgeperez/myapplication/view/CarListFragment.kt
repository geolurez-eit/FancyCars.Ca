package com.georgeperez.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.georgeperez.myapplication.R
import com.georgeperez.myapplication.viewmodel.FancyCarsViewModel
import com.google.android.material.appbar.MaterialToolbar

class CarListFragment : Fragment() {

    private lateinit var carRecyclerView: RecyclerView
    private lateinit var topAppBar: MaterialToolbar

    private val listAdapter = ListAdapter()

    private var isLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            carRecyclerView = findViewById(R.id.list_recyclerview)
            topAppBar = findViewById(R.id.list_toolBar)
        }

        carRecyclerView.adapter = listAdapter

        /*FancyCarsViewModel.getCar()
        FancyCarsViewModel.cars.observe(this.viewLifecycleOwner,{
            listAdapter.update()
        })*/

        listAdapter.update()

        topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_item_sort_name -> {
                    FancyCarsViewModel.sortByName()
                    listAdapter.update()
                    true
                }
                R.id.menu_item_sort_availability -> {
                    FancyCarsViewModel.sortByAvailability()
                    listAdapter.update()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

}