package br.edu.up.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SobreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}