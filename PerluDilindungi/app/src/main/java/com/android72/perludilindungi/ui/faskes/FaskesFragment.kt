package com.android72.perludilindungi.ui.faskes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.databinding.FragmentFaskesBinding

class FaskesFragment : Fragment() {

    //private lateinit var faskesViewModel: FaskesViewModel
    private var _binding: FragmentFaskesBinding? = null
    //private lateinit var recyclerView: RecyclerView;

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

        _binding = FragmentFaskesBinding.inflate(inflater, container, false)

        val root: View = binding.root
        /*recyclerView = _binding!!.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(_binding!!.fragmentFaskes.context);
        recyclerView.adapter = FaskesAdapter();
         */

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