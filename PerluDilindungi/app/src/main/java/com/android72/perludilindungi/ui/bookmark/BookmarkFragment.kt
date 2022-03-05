package com.android72.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.model.Bookmark
import com.android72.perludilindungi.databinding.FragmentBookmarkBinding
import kotlinx.android.synthetic.main.fragment_berita.*
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_bookmark.view.*

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

        mBookmarkViewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]

        /*root.add_btn.setOnClickListener {
            //below to check if data exist, masih eror tapi fungsinya :(
            // val result = checkBookmarkExist()

            // to add to database
            //insertDataToDatabase()

            // to delete from database
            // deleteDataFromDatabase()

            //Toast.makeText(requireContext(), "Successfully clicked!", Toast.LENGTH_LONG).show()
        } */

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* private fun insertDataToDatabase() {
        val id = 101
        val faskesNama = "RS RI"
        val faskesJenis = "RUMAH SAKIT"
        val faskesAlamat = "Gedung Mana Hayo RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia"
        val faskesTelp = "(021) 1222001"
        val faskesKode = "N0000300"
        val faskesStatus = " Siap Vaksinasi"

        // Create User Object
        val bookmark = Bookmark(
            id,
            faskesKode,
            faskesNama,
            faskesAlamat,
            faskesTelp,
            faskesJenis,
            faskesStatus
        )

        // Add Data to Database
        mBookmarkViewModel.addBookmark(bookmark)
        //Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

    }

    private fun deleteDataFromDatabase() {
        val id = 101

        mBookmarkViewModel.deleteBookmark(id)
        findNavController().navigate(R.id.action_fragmentDetail_to_listItem)
    }

    private fun checkBookmarkExist() {
        val id = 101

        // Check Data in Database
        val result = mBookmarkViewModel.checkBookmarkExist(id)

        Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_LONG).show()

        return result
    } */
}