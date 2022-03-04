package com.android72.perludilindungi.ui.faskes

import android.content.ContentValues.TAG
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.backend.api.RetrofitAPI
import com.android72.perludilindungi.databinding.FragmentFaskesBinding
import com.google.android.material.internal.VisibilityAwareImageButton
import kotlinx.android.synthetic.main.fragment_faskes.*
import kotlinx.android.synthetic.main.row_faskes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FaskesFragment : Fragment() {

    //private lateinit var faskesViewModel: FaskesViewModel
    private lateinit var _binding: FragmentFaskesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var provinceSelect: String
    private lateinit var citySelect: String
    var listProvince: ArrayList<String> = ArrayList()
    var listCity: ArrayList<String> = ArrayList()
    var listFaskes: ArrayList<Faskes> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*faskesViewModel =
            ViewModelProvider(this).get(FaskesViewModel::class.java)
         */
//        val config = resources.configuration
//        _binding = if (config.orientation == ORIENTATION_LANDSCAPE){
//            FragmentFaskesBinding.inflate(inflater, container, false)
//        }else{
//            FragmentFaskesBinding.inflate(inflater, container, false)
//        }
        _binding = FragmentFaskesBinding.inflate(inflater, container, false)

        val root: View = binding.root
        getProvince()
        _binding.spinnerCity.visibility = INVISIBLE
        recyclerView = _binding.recyclerViewFaskes
        recyclerView.layoutManager = LinearLayoutManager(this@FaskesFragment.context)

        _binding.btnSearchFaskes.setOnClickListener {
            getFaskesData(provinceSelect, citySelect)
        }
        /*val textView: TextView = binding.textHome
        faskesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */
        return root
    }

    private fun getProvince() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
        val retrofitData = retrofitAPI.getProvince()
        provinceSelect = "ACEH"
        retrofitData.enqueue(object : Callback<ProvinceData?> {
            override fun onResponse(call: Call<ProvinceData?>?, response: Response<ProvinceData?>?) {
                val responseBody = response!!.body()!!

                if (responseBody.curr_val != null) {
                    for (province in responseBody.results) {
                        val provinceValue = province.value
                        listProvince.add(provinceValue)
                    }
                    val spinProvinceAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listProvince) }
                    spinner_province.adapter = spinProvinceAdapter

                    spinner_province.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            //Toast.makeText(context, listProvince[position], Toast.LENGTH_SHORT).show()
                            if(listProvince[position] != null){
                                provinceSelect = listProvince[position]
                                _binding.spinnerCity.visibility = VISIBLE
                                getCity(provinceSelect)
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }
                }
                else {
                    Log.v("TAG", "Cannot get Data from API");
                }
            }

            override fun onFailure(call: Call<ProvinceData?>?, t: Throwable?) {
                Log.d("FaskesFragment","onFailure"+t!!.message)
            }
        })
    }

    private fun getCity(province: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
        val retrofitData = retrofitAPI.getCity(province)
        retrofitData.enqueue(object : Callback<CityData?> {
            override fun onResponse(call: Call<CityData?>?, response: Response<CityData?>?) {
                val responseBody = response!!.body()!!
                listCity.clear()
                if (responseBody.curr_val != null) {
                    for (city in responseBody.results) {
                        val cityValue = city.value
                        listCity.add(cityValue)
                    }
                    val spinCityAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listCity) }
                    spinner_city.adapter = spinCityAdapter

                    spinner_city.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            //Toast.makeText(context, listCity[position], Toast.LENGTH_SHORT).show()
                            citySelect = listCity[position]
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }
                }
                else {
                    Log.v("TAG", "Cannot get Data from API");
                }
            }

            override fun onFailure(call: Call<CityData?>?, t: Throwable?) {
                Log.d("FaskesFragment","onFailure"+t!!.message)
            }
        })
    }

    private fun getFaskesData(province: String, city: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
//        Log.d(TAG, "onResponse: province =$province")
//        Log.d(TAG, "onResponse: city =$city")
        val retrofitData = retrofitAPI.getFaskes(province, city)
        retrofitData.enqueue(object : Callback<FaskesData?> {
            override fun onResponse(call: Call<FaskesData?>?, response: Response<FaskesData?>?) {
                val responseBody = response!!.body()!!
                if (call != null) {
                    Log.d(TAG, "onResponse: call =" + call.request().url())
                }
                listFaskes.clear()
//                listFaskes = ArrayList<Faskes>()
                if (responseBody.success) {
                    var i = 0
                    if (responseBody.data.isNotEmpty()){
                        for (faskes in responseBody.data) {
                            val faskesId = faskes.id
                            val faskesKode = faskes.kode
                            val faskesNama = faskes.nama
                            var faskesAlamat = ""
                            if(faskes.alamat != null){
                                faskesAlamat = faskes.alamat
                            }
                            var faskesTelp = ""
                            if(faskes.telp != null){
                                faskesTelp = faskes.telp
                            }
                            val faskesJenis = faskes.jenis_faskes
                            val faskesStatus = faskes.status
                            val faskesLatitude = faskes.latitude
                            val faskesLongitude = faskes.longitude
                            listFaskes.add(Faskes(faskesId, faskesKode, faskesNama, faskesAlamat, faskesTelp, faskesJenis, faskesStatus, faskesLatitude, faskesLongitude))
                            i = i + 1
                            if (i == 5) {
                                break
                            }
                        }
                        recyclerView.adapter =
                            this@FaskesFragment.context?.let { FaskesAdapter(it, listFaskes) }
                    }else {
                        Log.v("TAG", "Cannot get Results from API")
                    }
                }
                else {
                    Log.v("TAG", "Cannot get Data from API")
                }
            }

            override fun onFailure(call: Call<FaskesData?>?, t: Throwable?) {
                Log.d("BeritaFragment","onFailure"+t!!.message)
            }
        })
    }
}