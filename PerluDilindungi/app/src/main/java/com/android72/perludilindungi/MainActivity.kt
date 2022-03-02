package com.android72.perludilindungi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.android72.perludilindungi.databinding.ActivityMainBinding
import com.android72.perludilindungi.ui.berita.Berita
import com.android72.perludilindungi.ui.berita.BeritaAdapter
import com.android72.perludilindungi.ui.checkin.CheckinActivity
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var beritadata: ArrayList<Berita>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_berita);
//        recyclerView = findViewById(R.id.recyclerView);
//
//        setAdapter();



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fab: View = binding.fab
        fab.setOnClickListener {
            val myIntent = Intent(this, CheckinActivity::class.java)
            this.startActivity(myIntent)
        }
        setUpTabBar()
    }

//    private fun setAdapter() {
//        var adapter: BeritaAdapter = BeritaAdapter();
////        beritadata = adapter.listBerita;
//        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext);
//        recyclerView.layoutManager = layoutManager;
////        recyclerView.itemAnimator = DefaultItemAnimator();
//        recyclerView.adapter = adapter;
//    }


    private fun setUpTabBar()
    {
        val adapter = TabPageAdapter(this, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}