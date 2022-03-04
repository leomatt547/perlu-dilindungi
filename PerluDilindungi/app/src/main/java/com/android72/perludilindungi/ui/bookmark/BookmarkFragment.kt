package com.android72.perludilindungi.ui.bookmark

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.model.Bookmark
import com.android72.perludilindungi.databinding.FragmentBookmarkBinding
import com.android72.perludilindungi.ui.berita.BeritaAdapter
import kotlinx.android.synthetic.main.fragment_berita.*
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment() {

    // private lateinit var bookmarkViewModel: BookmarkViewModel
    private var _binding: FragmentBookmarkBinding? = null
    private lateinit var recyclerView: RecyclerView;
    private lateinit var mBookmarkViewModel: BookmarkViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // bookmarkViewModel =
            // ViewModelProvider(this).get(BookmarkViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mBookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)

        /*add_btn.setOnClickListener {
            insertDataToDatabase()
        } */

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertDataToDatabase() {
        val id = 101
        val faskesNama = "KLINIK MPR RI"
        val faskesJenis = "KLINIK"
        val faskesAlamat = "Gedung MPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia"
        val faskesTelp = "(021) 100001"
        val faskesKode = "N0000002"

        // Create User Object
        val bookmark = Bookmark(
            id,
            faskesKode,
            faskesNama,
            faskesAlamat,
            faskesTelp,
            faskesJenis,
        )

        // Add Data to Database
        mBookmarkViewModel.insert(bookmark)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

        // Navigate Back
        // findNavController().navigate(R.id.action_addFragment_to_listFragment)
        // refresh fragment instead
    }
}