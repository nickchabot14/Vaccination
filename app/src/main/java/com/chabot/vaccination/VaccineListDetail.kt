package com.chabot.vaccination


import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chabot.vaccination.databinding.ActivityVaccineListDetailBinding



class VaccineListDetail : AppCompatActivity() {
    private lateinit var binding: ActivityVaccineListDetailBinding
    companion object {
        val EXTRA_COUNTRY = "country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccineListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val country = intent.getParcelableExtra<Vaccine>(EXTRA_COUNTRY)
        binding.textViewCountryName.text = country?.country
        binding.textViewTimelineDetail.text = country?.timeline.toString()
    }


}