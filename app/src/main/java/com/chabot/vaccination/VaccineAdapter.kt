package com.chabot.vaccination

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccineAdapter (var dataSet: List<Vaccine>) :
    RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewCountry: TextView
            val textViewTimeline: TextView
            val layout: ConstraintLayout

            init {
                textViewCountry = view.findViewById(R.id.textView_vaccine_country)
                textViewTimeline = view.findViewById(R.id.textView_vaccine_timeline)
                layout = view.findViewById(R.id.layoutVaccine)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_vaccine, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val vaccine = dataSet[position]
            viewHolder.textViewCountry.text = vaccine.country
            val key = vaccine.timeline.lastKey()
            viewHolder.textViewTimeline.text = vaccine.timeline[key].toString()
            viewHolder.layout.setOnClickListener {
                val context = viewHolder.layout.context
                val vaccinedetailintent = Intent(context, VaccineListDetail::class.java).apply{
                    putExtra(VaccineListDetail.EXTRA_COUNTRY,vaccine)
                }
                context.startActivity(vaccinedetailintent)
            }
        }

        override fun getItemCount() = dataSet.size
    }
