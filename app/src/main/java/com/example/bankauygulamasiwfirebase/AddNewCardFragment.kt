package com.example.bankauygulamasiwfirebase

import SharedViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankauygulamasiwfirebase.databinding.FragmentAddNewCardBinding

class AddNewCardFragment : Fragment() {
    private lateinit var binding : FragmentAddNewCardBinding
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddNewCardBinding.inflate(inflater,container,false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.buttonActivate.setOnClickListener {
            val cardNumber:String = binding.editTextCardNo.text.toString()
            val cardName:String = binding.editTextCardName.text.toString()
            val cardExpirDate:String = binding.editTextExpirDate.text.toString()

            val card = Cards(cardNumber,cardName,"icon_visa",cardExpirDate)

            sharedViewModel.referenceCards.push().setValue(card)
            findNavController().popBackStack()
        }





        return binding.root
    }
}