package com.android72.perludilindungi.ui.berita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.databinding.FragmentBeritaBinding
import com.android72.perludilindungi.databinding.WebBeritaBinding

class BeritaFragment : Fragment() {

//    private lateinit var beritaViewModel: BeritaViewModel
    private var _binding: FragmentBeritaBinding? = null
    private lateinit var recyclerView: RecyclerView;

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
        recyclerView.layoutManager = LinearLayoutManager(_binding!!.fragmentBerita.context);
        recyclerView.adapter = BeritaAdapter();

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
}