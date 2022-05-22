package com.example.elemestest.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.elemestest.R
import com.example.elemestest.databinding.ActivityHomeBinding
import com.example.elemestest.feature.homeFragment.HomeFragment
import com.example.elemestest.feature.myWatchList.WatchlistFragment
import com.example.elemestest.feature.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), HomeView {
    lateinit var binding : ActivityHomeBinding
    lateinit var presenter: HomePresenter
    var CURENT_PAGE = ""
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    CURENT_PAGE = "Home"
                    true
                }
                R.id.menu_wathslist -> {
                    loadFragment(WatchlistFragment())
                    CURENT_PAGE = "Watch List"
                    true
                }
                R.id.menu_pengaturan -> {
                    loadFragment(SettingsFragment())
                    CURENT_PAGE = "More"
                    true
                }
            }
            false
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = HomePresenter(this)
        loadFragment(HomeFragment())
        binding.navBottomHome.apply {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
    }
    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment, "$CURENT_PAGE")
        transaction.commit()
        invalidateOptionsMenu()
    }
    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDeAttachView() {
        presenter.onDeAttach()
    }
}