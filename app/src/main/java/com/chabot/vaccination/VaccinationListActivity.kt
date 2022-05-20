package com.chabot.vaccination

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chabot.vaccination.databinding.ActivityVaccineListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.random.Random


class VaccinationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVaccineListBinding
    lateinit var adapter: VaccineAdapter
    val TAG = "VaccinationListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccineListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)

        vaccineCall.enqueue(object : Callback<List<Vaccine>> {
            override fun onResponse(
                call: Call<List<Vaccine>>,
                response: Response<List<Vaccine>>
            ) {
                response.body()
                Log.d(ContentValues.TAG, "onResponse: ${response.body()}")
                var vaccineList = response.body() ?: listOf<Vaccine>()
                    adapter = VaccineAdapter(vaccineList)
                binding.vaccineRecycleView.adapter = adapter
                binding.vaccineRecycleView.layoutManager =
                    LinearLayoutManager(this@VaccinationListActivity)
                val country1 = vaccineList[0]
                val firstDay = country1.timeline.firstKey()
                val lastDay = country1.timeline.lastKey()
                country1.timeline.get(firstDay)
                country1.timeline.toList().sortedBy {
                    it.second
                }[0]
            }

            override fun onFailure(call: Call<List<Vaccine>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        val WorldwideInfo = RetrofitHelper.getInstance().create(WorldwideInfo::class.java)
        val Worldwidecall = WorldwideInfo.getWorldwideStats()
        Worldwidecall.enqueue(object: Callback<Worldwide> {
            override fun onResponse(
                call: Call<Worldwide>,
                response: Response<Worldwide>
            ){
                response.body()
                Log.d(TAG, "onResponse: ${response.body()}")
                val worldwide = response.body() !!
                Log.d(TAG, "onResponse: ${worldwide}")
            }

            override fun onFailure(call: Call<Worldwide>, t: Throwable) {
                TODO()
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sortingmenu, menu)
        return true
    }

    fun onOptionsItenSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.country -> {
                Toast.makeText(this, "country", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy { it.country }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.Increase -> {
                Toast.makeText(this, "increase", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy {
                    (it.timeline.get(
                        it.timeline.lastKey() ?: 0L
                    ))?.minus((it.timeline.get(it.timeline.firstKey()) ?: 0L))
                }
                adapter.notifyDataSetChanged()
                true
            }

            R.id.Vaccinenumber -> {
                Toast.makeText(this, "vaccine number", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedByDescending {
                    (it.timeline.get(
                        it.timeline.lastKey() ?: 0L
                    ))?.minus((it.timeline.get(it.timeline.firstKey())?: 0L))
                }

                adapter.notifyDataSetChanged()
                val Random = Random.nextInt(1, 25)
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }



}