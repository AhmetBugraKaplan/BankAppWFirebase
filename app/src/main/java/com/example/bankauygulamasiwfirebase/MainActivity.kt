package com.example.bankauygulamasiwfirebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bankauygulamasiwfirebase.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var cardsList : ArrayList<Cards>
    private lateinit var adapter: CreditCardsAdapter
    private lateinit var referenceCards : DatabaseReference
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Main Activity üzerinde oluşturduğumuz Navigation Host Fragmenti id si ile yakalayip belirtiyoruz
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        /*Sonrasında navHostFragment ile bottomNavigation u birbirine bağlıyoruz, idleri zaten menu dosyası
        üzerinde fragment id leri ile aynı yaptığımız için otomatik id e göre geçiş gerçekleşecek
        yani bizim tek yapmamız gereken önce host fragmenti belirtip sonrasında bottom nav ile bağlamak
         */
        NavigationUI.setupWithNavController(binding.bottomNav,navHostFragment.navController)









    }



}