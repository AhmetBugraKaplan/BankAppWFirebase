package com.example.bankauygulamasiwfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.bankauygulamasiwfirebase.databinding.FragmentLoginPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class LoginPageFragment : Fragment() {
    private lateinit var binding : FragmentLoginPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginPageBinding.inflate(inflater,container,false)


        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.visibility = View.GONE

        val scope = CoroutineScope(Dispatchers.Main)

        binding.buttonLogin.setOnClickListener {
            val enteredText = binding.editTextNumber.text.toString()
            if (enteredText == "123456"){
                scope.launch {
                    delay(500)
                    Navigation.findNavController(it).navigate(R.id.loginToHome)
                }

            }else
                Toast.makeText(requireContext(),"Incorrect password!",Toast.LENGTH_SHORT).show()



        }

        return binding.root
    }
}