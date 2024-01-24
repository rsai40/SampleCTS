package com.sample.samplelistcts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplelistcts.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this@MainActivity, (R.layout.activity_main))

        lifecycleScope.launch(Dispatchers.IO) {
            val jsonString =
                applicationContext.assets.open("fileName").bufferedReader().use { it.readText() }

            val sampleList = mutableListOf<SampleModel>()
            jsonString?.forEach { fileName ->
                val jsonString =
                    assets.open("json/$fileName").bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonString)
                val sample = SampleModel(
                    Art = jsonObject.getString("Art"),
                    Nr = jsonObject.getString("Nr"),
                    Qnt = jsonObject.getString("Qnt"),
                    Prz = jsonObject.getString("Prz"),
                    Tot = jsonObject.getString("tot")
                )
                sampleList.add(sample)
            }

            launch(Dispatchers.Main)
            {
                showList(sampleList)
            }
        }

    }

    private fun showList(sampleList: List<SampleModel>) {
        val recyclerView: RecyclerView = mainBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(sampleList)
    }

}