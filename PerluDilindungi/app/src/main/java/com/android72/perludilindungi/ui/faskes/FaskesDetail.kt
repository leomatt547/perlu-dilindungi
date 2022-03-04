package com.android72.perludilindungi.ui.faskes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.api.RetrofitAPI
import com.android72.perludilindungi.ui.faskes.FaskesData
import com.android72.perludilindungi.databinding.FragmentDetailBinding
import com.android72.perludilindungi.ui.berita.Berita
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FaskesDetail : Fragment() {

    //private lateinit var faskesViewModel: FaskesViewModel
    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var latitude: Float? =null
    private var longitude: Float? =null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*faskesViewModel =
            ViewModelProvider(this).get(FaskesViewModel::class.java)
         */

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val root: View = binding.root
        _binding!!.btnMaps.setOnClickListener { // Creates an Intent that will load a map of San Francisco
            val gmmIntentUri = Uri.parse("geo:${latitude!!},${longitude!!}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        /*val textView: TextView = binding.textHome
        faskesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFaskesData(province: String, city: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
        val retrofitData = retrofitAPI.getFaskes(province, city);
        retrofitData.enqueue(object : Callback<FaskesData?> {
            override fun onResponse(call: Call<FaskesData?>?, response: Response<FaskesData?>?) {
                val responseBody = response!!.body()!!

                if (responseBody.success) {
                    for (faskes in responseBody.results) {
                        val faskesKode = faskes.kode
                        val faskesNama = faskes.nama
                        val faskesAlamat = faskes.alamat
                        val faskesTelp = faskes.telp
                        val faskesJenis = faskes.jenis_faskes
                        val faskesStatus = faskes.status

                        if(faskesStatus == "Siap Vaksinasi"){
                            binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_check_circle_80)
                            binding.lblFaskesStatus.text = "SIAP VAKSINASI"
                        }else {
                            binding.imvFaskesStatus.setImageResource(R.drawable.ic_round_cancel_red_80)
                            binding.lblFaskesStatus.text = "TIDAK SIAP VAKSINASI"
                        }
                    }
                }
                else {
                    Log.v("TAG", "Cannot get Data from API");
                }
            }

            override fun onFailure(call: Call<FaskesData?>?, t: Throwable?) {
                Log.d("BeritaFragment","onFailure"+t!!.message)
            }
        })
    }
}