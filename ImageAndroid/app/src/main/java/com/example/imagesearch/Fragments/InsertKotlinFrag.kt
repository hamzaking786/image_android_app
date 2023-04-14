package com.example.imagesearch.Fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.imagesearch.R
import com.example.imagesearch.Utility
import com.example.imagesearch.databinding.ActivityMainBinding.inflate
import com.example.imagesearch.databinding.FragmentInsertKotlinBinding
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.concurrent.thread

class InsertKotlinFrag : Fragment() {


    private lateinit var binding : FragmentInsertKotlinBinding
    private var resulturi: Uri? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentInsertKotlinBinding.inflate(inflater, container, false)


        binding.chooseBtn.setOnClickListener {
              permissionCheck();
        }


        binding.uploadBtnt.setOnClickListener {

            if (resulturi!=null){
                binding.pb.visibility=View.VISIBLE
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(activity?.getContentResolver(), Uri.parse(resulturi.toString()))

                uploadImage(bitmap);


            }
            else{
               Toast.makeText(context,"Please insert image before uploading",Toast.LENGTH_SHORT).show()
            }

        }



        return binding.root
    }

    private fun uploadImage(bitmap:Bitmap) {

        val imgOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, imgOutputStream)
        val mediaType = MediaType.parse("image/png")
        val req = MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("image","whatever.png", RequestBody.create(mediaType, imgOutputStream.toByteArray())).build()
        val urlBuilder = HttpUrl.parse("http://api-edu.gtl.ai/api/v1/imagesearch/upload")!!.newBuilder()
        val url = urlBuilder.build().toString()
        val client = OkHttpClient()
        thread {
            val request = Request.Builder().url(url).post(req).build()
            val response = client.newCall(request).execute()
            val responseBody = response.body()?.string()
            Utility.imageUrl=responseBody.toString()

            val handler = Handler(Looper.getMainLooper())
            handler.post({
                binding.pb.visibility=View.GONE
                Toast.makeText(context,"image uploaded successfully",Toast.LENGTH_SHORT).show()
            })




        }

    }

    private fun permissionCheck() {
        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        }
        else{
            pickImageFromGallery()
        }
    }



    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            resulturi= data?.data!!
            binding.iv.setImageURI(resulturi)
        } else {
            Toast.makeText(context, "Error loading image", Toast.LENGTH_LONG)
        }
    }


}