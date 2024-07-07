package com.example.bankauygulamasiwfirebase

import SharedViewModel
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.os.IResultReceiver._Parcel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bankauygulamasiwfirebase.databinding.FragmentSelectedCardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SelectedCardFragment : Fragment() {
    private lateinit var binding: FragmentSelectedCardBinding
    private lateinit var listView: ListView
    private lateinit var key :String
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var sharedViewModel: SharedViewModel

    @SuppressLint("DiscouragedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelectedCardBinding.inflate(inflater, container, false)


       //Home Pagedeki gelen veriyi alıyoruz ve kartın üzerine yazdırıyoruz
        val bundle:SelectedCardFragmentArgs by navArgs()

        val name = bundle.Name
        val expirDate = bundle.cardExpirationDate
        val logo = bundle.Logo
        val no = bundle.No
        key = bundle.Key

        binding.imageViewCardType.setImageResource(requireContext().resources.getIdentifier(logo,"drawable",requireContext().packageName))
        binding.textViewCardExpirDate.text = expirDate
        binding.textViewCardNo.text = no
        binding.textViewCardName.text = name
        //////////////

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)


        setupPopupMenu()

        return binding.root
    }


    private fun setupPopupMenu() {
        val popUpButton = binding.imageViewSettings

        val popupMenu = PopupMenu(requireContext(), popUpButton)
        popupMenu.inflate(R.menu.popup_menu_item)

        popUpButton.setOnClickListener { view ->
            popupMenu.show()
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item1 -> {
                    val alertDialogBuilder = AlertDialog.Builder(requireContext())
                    alertDialogBuilder.setMessage("Are you sure want to delete this card ?")
                    alertDialogBuilder.setTitle("Attention!")
                    alertDialogBuilder.setCancelable(false)
                    alertDialogBuilder.setPositiveButton("Yes"){_,_ ->
                        sharedViewModel.referenceCards.child(key).removeValue()
                        Toast.makeText(requireContext(), "Card Deleted", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()

                    }
                    alertDialogBuilder.setNegativeButton("No"){_,_ ->
                    }
                    alertDialogBuilder.show()


                    true
                }
                else -> false
            }
        }
    }




}
