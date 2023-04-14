package com.example.imagesearch.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.imagesearch.DatabaseConnect
import com.example.imagesearch.Models.ResultModel
import com.example.imagesearch.ResultsAdapter
import com.example.imagesearch.Utility
import com.example.imagesearch.databinding.FragmentResultsKotlinBinding


class ResultsKotlinFrag : Fragment() {

    private lateinit var binding : FragmentResultsKotlinBinding
    private lateinit var databaseHelper: DatabaseConnect
    var adapter: ResultsAdapter? = null
    var resultList: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentResultsKotlinBinding.inflate(inflater, container, false)

        databaseHelper=DatabaseConnect(context)

        binding.rv.layoutManager=GridLayoutManager(context,2)


        binding.showBtn.setOnClickListener {



            getResults()

        }

        binding.saveBtnt.setOnClickListener {

            if (resultList.size < 1) {
                Toast.makeText(context, "Nothing to save", Toast.LENGTH_SHORT).show()
            } else {
                val result = ResultModel(
                    0, Utility.imageUrl,
                    resultList[0], resultList[1], resultList[2], resultList[3], resultList[4]
                )
                databaseHelper.insertNewResult(result)
                Toast.makeText(context, "Result saved successfully", Toast.LENGTH_SHORT).show()
            }


        }





        return binding.root
    }

    private fun getResults() {

        if (Utility.imageUrl == null) {
            Toast.makeText(context, "Please upload image to fetch results", Toast.LENGTH_SHORT).show()
            return
        }

        binding.pb.visibility=View.VISIBLE



        val url = "http://api-edu.gtl.ai/api/v1/imagesearch/bing?url=" + Utility.imageUrl


        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val link = jsonObject.getString("image_link")
                        resultList.add(link)
                        adapter = ResultsAdapter(resultList, requireContext())
                        binding.rv.setAdapter(adapter)
                        adapter?.notifyDataSetChanged()
                    }
                    binding.pb.visibility = View.GONE
                } catch (exp: Exception) {
                }
            }) { error ->
             binding.pb.visibility=View.GONE
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show()
        }
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)

    }


}