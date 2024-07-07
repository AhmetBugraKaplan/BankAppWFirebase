package com.example.bankauygulamasiwfirebase

import SharedViewModel
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankauygulamasiwfirebase.databinding.FragmentForexBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class ForexFragment : Fragment() {
    private lateinit var binding: FragmentForexBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var ForexList: ArrayList<Forexs>
    private lateinit var forexAdapter: ForexsAdapter  // Burada Companion sınıfı olmamalı

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentForexBinding.inflate(inflater, container, false)

        binding.RecyclerViewForex.setHasFixedSize(true)
        binding.RecyclerViewForex.layoutManager = LinearLayoutManager(requireContext())

        ForexList = ArrayList()
        forexAdapter = ForexsAdapter(requireContext(), ForexList)
        binding.RecyclerViewForex.adapter = forexAdapter  // Adapter'ı RecyclerView'a bağlayın

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val referenceForexs = sharedViewModel.referenceForex
        getAllForexs(referenceForexs)

        //val forex = Forexs("USD", "1.12", "1.20", "icon_dolar")
        //sharedViewModel.referenceForex.push().setValue(forex)


        //Pie chart kısmı

        val pieChart:PieChart = binding.chart1

        // PieChart verilerini oluştur
        val entries = mutableListOf<PieEntry>()
        entries.add(PieEntry(40f, "APPLE"))
        entries.add(PieEntry(30f, "EUR"))
        entries.add(PieEntry(20f, "GOLD"))
        entries.add(PieEntry(10f, "BTC"))

        val dataSet = PieDataSet(entries, "Forex Data")
        dataSet.colors = listOf(
            Color.rgb(76, 175, 80),   // Koyu Yeşil
            Color.rgb(97, 97, 97),    // Koyu Gri
            Color.rgb(183, 28, 28),   // Koyu Kırmızı
            Color.rgb(33, 150, 243)   // Koyu Mavi
        )

        dataSet.valueTextSize = 20f
        dataSet.valueTextColor = Color.BLACK

        val data = PieData(dataSet)
        pieChart.data = data

        // PieChart'ı güncelle
        pieChart.invalidate()

        return binding.root
    }

    private fun getAllForexs(referenceForex: DatabaseReference) {
        referenceForex.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                ForexList.clear()
                for (c in snapshot.children) {
                    val forex = c.getValue(Forexs::class.java)
                    if (forex != null) {
                        ForexList.add(forex)
                    }
                }
                forexAdapter.notifyDataSetChanged()  // Burada forexAdapter'ı kullanın
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata yönetimi yapılabilir
            }
        })
    }
}
