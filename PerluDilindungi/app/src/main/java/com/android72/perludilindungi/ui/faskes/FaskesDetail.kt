package com.android72.perludilindungi.ui.faskes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.api.RetrofitAPI
import com.android72.perludilindungi.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FaskesDetail(private var listFaskesDetailData: Faskes) : Fragment() {

    //private lateinit var faskesViewModel: FaskesViewModel
    private lateinit var _binding: FragmentDetailBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val root: View = binding.root

        _binding.lblFaskesKode.text = listFaskesDetailData.kode
        _binding.lblFaskesNama.text = listFaskesDetailData.nama
        _binding.lblFaskesAlamat.text = listFaskesDetailData.alamat
        _binding.lblFaskesTelp.text = listFaskesDetailData.telp
        _binding.lblFaskesJenis.text = listFaskesDetailData.jenis_faskes
        if (listFaskesDetailData.status == "Siap Vaksinasi") {
            _binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_check_circle_80)
            _binding.lblFaskesStatus.text = "SIAP VAKSINASI"
        }else {
            _binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_cancel_red_80)
            _binding.lblFaskesStatus.text = "TIDAK SIAP VAKSINASI"
        }

        _binding!!.btnMaps.setOnClickListener { // Creates an Intent that will load a map of San Francisco
            val gmmIntentUri = Uri.parse("geo:${listFaskesDetailData.latitude},${listFaskesDetailData.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        return root
    }
}