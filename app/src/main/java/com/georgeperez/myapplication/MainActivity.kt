package com.georgeperez.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.georgeperez.myapplication.view.CarListFragment

class MainActivity : AppCompatActivity() {

    private val listFragment = CarListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_framelayout, listFragment)
            .addToBackStack(listFragment.tag)
            .commit()
    }

}