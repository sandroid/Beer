package com.prepfully.beer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prepfully.beer.ui.main.BeerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BeerFragment.newInstance())
                .commitNow()
        }
    }
}