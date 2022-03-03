package com.android72.perludilindungi.ui.berita

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.api.RetrofitAPI
import com.android72.perludilindungi.databinding.FragmentBeritaBinding
import com.android72.perludilindungi.databinding.WebBeritaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeritaFragment : Fragment(), linkClickListener {

//    private lateinit var beritaViewModel: BeritaViewModel
    private var _binding: FragmentBeritaBinding? = null
    private lateinit var recyclerView: RecyclerView;
    var listBerita: ArrayList<Berita> = ArrayList();

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        beritaViewModel =
//            ViewModelProvider(this).get(BeritaViewModel::class.java)

        _binding = FragmentBeritaBinding.inflate(inflater, container, false)

        val root: View = binding.root
        recyclerView = _binding!!.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this@BeritaFragment.context);
        getBeritaData();
//        recyclerView.adapter = BeritaAdapter(listBerita);

//        val textView: TextView = binding.textDashboard
//        beritaViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getBeritaData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
        val retrofitData = retrofitAPI.beritaData();
        retrofitData.enqueue(object : Callback<BeritaData?> {
            override fun onResponse(call: Call<BeritaData?>?, response: Response<BeritaData?>?) {
                val responseBody = response!!.body()!!

                if (responseBody.success) {
                    for (berita in responseBody.results) {
                        val beritaTitle = berita.title
                        val beritaLink = berita.link[0]
                        val beritaDate = berita.pubDate
                        val beritaImageURL = berita.enclosure._url
                        listBerita.add(Berita(beritaTitle, beritaLink, beritaDate, beritaImageURL))
                    }
                    recyclerView.adapter = BeritaAdapter(listBerita, this@BeritaFragment);
                }
                else {
                    Log.v("TAG", "Cannot get Data from API");
                }
            }

            override fun onFailure(call: Call<BeritaData?>?, t: Throwable?) {
                Log.d("BeritaFragment","onFailure"+t!!.message)
            }
        })
    }

    override fun navigateToWeb(url: String) {
        val action = BeritaFragmentDirections.actionBeritaPage(url)
        findNavController().navigate(action)
    }
}