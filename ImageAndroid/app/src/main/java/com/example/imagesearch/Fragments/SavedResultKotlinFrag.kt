package com.example.imagesearch.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearch.DatabaseConnect
import com.example.imagesearch.Models.ResultModel
import com.example.imagesearch.SavedAdapter
import com.example.imagesearch.databinding.FragmentSavedResultKotlinBinding
import kotlin.collections.ArrayList


class SavedResultKotlinFrag : Fragment() {

    private lateinit var binding : FragmentSavedResultKotlinBinding
    var adapter: SavedAdapter? = null
    var resultList: ArrayList<ResultModel> = ArrayList()
    var databaseHelper: DatabaseConnect? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSavedResultKotlinBinding.inflate(inflater, container, false)
        binding.rv.layoutManager= LinearLayoutManager(context)

        databaseHelper = DatabaseConnect(context)

        resultList = databaseHelper!!.fetchResults()
        adapter = SavedAdapter(resultList, requireContext())
        binding.rv.setAdapter(adapter)


        return binding.root
    }

}