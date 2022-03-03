package com.android72.perludilindungi.ui.faskes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android72.perludilindungi.databinding.FragmentDetailBinding

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
}