package com.example.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

         var d = ArrayList<data>()
        lateinit var recycle:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.hel)

        load()
        recycle.layoutManager = LinearLayoutManager(this)
//        recycle.adapter = myadapter(d)
    }



    fun load(){
        var queue = Volley.newRequestQueue(this)
        var url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        var jsonobj = JsonObjectRequest(Request.Method.GET,url,null,{
            Response ->
             var arr = Response.getJSONArray("articles")
            for(i in 0 until arr.length()){
                var obj = arr.getJSONObject(i)
                var a = obj.getString("title")
                var b = obj.getString("urlToImage")
                var u =obj.getString("url")
                var con = obj.getString("content")
                var au = obj.getString("author")
                d.add(data(a,b,u,con,au))
            }
            recycle.adapter = myadapter(d)
//                recycle.adapter?.notifyDataSetChanged()
        },{
            Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
        })
        queue.add(jsonobj)
    }

}