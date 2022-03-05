package com.android72.perludilindungi.ui.faskes

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android72.perludilindungi.R
import com.android72.perludilindungi.databinding.ActivityFaskesDetailBinding

class FaskesDetailActivity() : AppCompatActivity() {
    private lateinit var _binding: ActivityFaskesDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFaskesDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(_binding.root)
        val extras = intent.extras

        if (extras != null) {
            _binding.lblFaskesKode.text = "Kode: " + extras.getString("faskesKodeStr")
            _binding.lblFaskesNama.text = extras.getString("faskesNamaStr")
            _binding.lblFaskesAlamat.text = extras.getString("faskesAlamatStr")
            _binding.lblFaskesTelp.text = extras.getString("faskesTelpStr")
            _binding.lblFaskesJenis.text = extras.getString("faskesJenisStr")

            if (extras.getString("faskesJenisStr") == "PUSKESMAS") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#EF5DA8"))
            } else if (extras.getString("faskesJenisStr") == "RUMAH SAKIT") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#5D5FEF"))
            } else if (extras.getString("faskesJenisStr") == "KLINIK") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#7879F1"))
            }

            if (extras.getString("status") == "Siap Vaksinasi") {
                _binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_check_circle_80)
                _binding.lblFaskesStatus.text = "SIAP VAKSINASI"
            }else {
                _binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_cancel_red_80)
                _binding.lblFaskesStatus.text = "TIDAK SIAP VAKSINASI"
            }

            _binding!!.btnMaps.setOnClickListener { // Creates an Intent that will load a map of San Francisco
                val gmmIntentUri = Uri.parse("geo:${extras.getString("latitude")},${extras.getString("longitude")}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}