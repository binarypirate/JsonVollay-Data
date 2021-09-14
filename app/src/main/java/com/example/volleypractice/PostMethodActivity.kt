package com.example.volleypractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class PostMethodActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_method)

        val button = findViewById<Button>(R.id.button1)
        val editTextUrl = findViewById<EditText>(R.id.editText1)
        val textView = findViewById<TextView>(R.id.text1)

        button.setOnClickListener {
            val url = editTextUrl.text.toString().trim()

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->

                var users = mutableListOf<PostUser>()

                val array = JSONArray(response)

                for (a in 0 until array.length()){
                    val jsonArr = array.getJSONObject(a)
                    PostUser(
                        jsonArr.getInt("userId"),
                        jsonArr.getInt("id"),
                        jsonArr.getString("title"),
                        jsonArr.getString("body")
                    )
                }
                var postData = ""
                for (user in users){
                    postData += "${user}\n\n"
                }
                textView.text = postData

            if (url.contains("/posts")) {
            if (url.endsWith("/posts") || url.endsWith("/posts/")) {

            } else {


            }
        }

            }, {
                textView.text = "That didn't work!"
            })
            queue.add(stringRequest)

        }


    }


}