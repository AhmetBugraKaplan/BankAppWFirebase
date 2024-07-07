package com.example.bankauygulamasiwfirebase

import SharedViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bankauygulamasiwfirebase.databinding.FragmentHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var Cardadapter: CreditCardsAdapter
    private lateinit var cardsList: ArrayList<Cards>
    private lateinit var ACadapter:ACAdapter
    private lateinit var ACList:ArrayList<ACDesign>
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)




        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.visibility = View.VISIBLE

        binding.RecyclerView.setHasFixedSize(true)
        binding.RecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        // Adapter ve listeyi başlatma
        cardsList = ArrayList()
        Cardadapter = CreditCardsAdapter(requireContext(), cardsList)
        binding.RecyclerView.adapter = Cardadapter


        binding.RecyclerViewAC.setHasFixedSize(true)
        binding.RecyclerViewAC.layoutManager = LinearLayoutManager(requireContext())

        ACList = ArrayList()
        ACadapter = ACAdapter(requireContext(),ACList)
        binding.RecyclerViewAC.adapter = ACadapter




        binding.floatingActionButton.setOnClickListener{
                bottomNavigationView.visibility = View.GONE
                Navigation.findNavController(it).navigate(R.id.homeToAddNewCard)
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel'i başlatma
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java) // Bu satır onViewCreated içinde

        val referenceCards = sharedViewModel.referenceCards
        getAllCards(referenceCards)

        val card = Cards("1234 5678 9123 4567","Family Card","icon_visa","04/29")

        //sharedViewModel.referenceCards.push().setValue(card)



        val referenceAC = sharedViewModel.referenceAC

        val AC = ACDesign(
            "icon_paypal","Paypal","Kaplan Card","+100 $","24 June 2024")
        //sharedViewModel.referenceAC.push().setValue(AC)



        getAllAc(referenceAC)





    }

    private fun getAllCards(referenceCards: DatabaseReference) {
        referenceCards.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                cardsList.clear()
                for (c in snapshot.children) {
                    val card = c.getValue(Cards::class.java)
                    if (card != null) {
                        card.cardKey = c.key
                        cardsList.add(card)
                    }
                }
                Cardadapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata yönetimi yapılabilir
            }
        })
    }

    private fun getAllAc(referenceAC: DatabaseReference) {
        referenceAC.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                ACList.clear()
                for (c in snapshot.children) {
                    val AC = c.getValue(ACDesign::class.java)
                    if (AC != null) {
                        ACList.add(AC)
                    }
                }
                ACadapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata yönetimi yapılabilir
            }
        })
    }



}
