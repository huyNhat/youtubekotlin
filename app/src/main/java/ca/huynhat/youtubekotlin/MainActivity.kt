package ca.huynhat.youtubekotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ca.huynhat.youtubekotlin.Model.HomeFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapterRecycler()


        fetchJson()



    }

    fun fetchJson(){
        println("Attempting to fetch JSON")

        val url ="https://raw.githubusercontent.com/lsv/fifa-worldcup-2018/master/data.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        //URL Request
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                //ASYNC HERE
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapterRecycler(homeFeed)
                }
            }
        })
    }
}
