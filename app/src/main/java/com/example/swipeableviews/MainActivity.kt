package com.example.swipeableviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var images = listOf(
                R.drawable.case1,
                R.drawable.case2,
                R.drawable.case3,
                R.drawable.case4,
                R.drawable.case5,
                R.drawable.case6
        )
        val adapter = ViewPagerAdapter(images)
        ViewPager.adapter = adapter

        TabLayoutMediator(tabLayout, ViewPager) {tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,"Re-selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,"Unselected ${tab?.text}",Toast.LENGTH_LONG).show()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Toast.makeText(this@MainActivity,"Selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
        })
    }
}