package com.example.volleypractice


import android.content.Intent
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

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextBtn = findViewById<Button>(R.id.nextBtn)
        val button = findViewById<Button>(R.id.button)
        val editTextUrl = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.text)

        nextBtn.setOnClickListener {
            val intent = Intent(this, PostMethodActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val url = editTextUrl.text.toString().trim()

            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->

                var myUsers = mutableListOf<User>()

                if (url.contains("/users")) {
                    if (url.endsWith("/users") || url.endsWith("/users/")) {
                        // todo: our same logic
                    } else {
                        val obj = JSONObject(response)
                        val address = obj.getJSONObject("address")
                        val geo = address.getJSONObject("geo")
                        val company = obj.getJSONObject("company")
                        User(
                            obj.getInt("id"),
                            obj.getString("name"),
                            obj.getString("username"),
                            obj.getString("email"),
                            Address(
                                address.getString("street"),
                                address.getString("suite"),
                                address.getString("city"),
                                address.getString("zipcode"),
                                Geo(
                                    geo.getString("lat"),
                                    geo.getString("lng")
                                )
                            ),
                            obj.getString("phone"),
                            obj.getString("website"),
                            Company(
                                company.getString("name"),
                                company.getString("catchPhrase"),
                                company.getString("bs")
                            )
                        )
                    }
                }

                    val arr = JSONArray(response)
                    for (i in 0 until arr.length()) {
                        val jsonObj = arr.getJSONObject(i)
                        val address = jsonObj.getJSONObject("address")
                        val geo = address.getJSONObject("geo")
                        val company = jsonObj.getJSONObject("company")
                        myUsers.add(
                            User(
                                jsonObj.getInt("id"),
                                jsonObj.getString("name"),
                                jsonObj.getString("username"),
                                jsonObj.getString("email"),
                                Address(
                                    address.getString("street"),
                                    address.getString("suite"),
                                    address.getString("city"),
                                    address.getString("zipcode"),
                                    Geo(
                                        geo.getString("lat"),
                                        geo.getString("lng")
                                    )
                                ),
                                jsonObj.getString("phone"),
                                jsonObj.getString("website"),
                                Company(
                                    company.getString("name"),
                                    company.getString("catchPhrase"),
                                    company.getString("bs")
                                )
                            )
                        )
                    }
                    var data = ""
                    for (user in myUsers) {
                        data += "${user}\n"
                    }

                    textView.text = data
            }
                , {
                textView.text = "That didn't work!"
            })
            queue.add(stringRequest)
        }
    }
}
