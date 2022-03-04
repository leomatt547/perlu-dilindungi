package com.android72.perludilindungi.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.model.Bookmark
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import kotlinx.android.synthetic.main.fragment_bookmark_detail.view.*

class BookmarkDetailFragment : Fragment() {
    //private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mBookmarkViewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bookmark_detail, container, false)

        mBookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)

        /*view.lbl_faskes_nama2.setText(args.currentBookmark.faskesNama)
        view.lbl_faskes_kode2.setText(args.currentBookmark.faskesKode)
        view.lbl_faskes_jenis2.setText(args.currentBookmark.faskesJenis)
        view.lbl_faskes_alamat2.setText(args.currentBookmark.faskesAlamat)
        view.lbl_faskes_telp2.setText(args.currentBookmark.faskesTelp) */
        //view.imv_faskes_status2.contentDescription(args.currentBookmark.faskesStatus)

        view.btn_bookmark2.setOnClickListener {
            //below to check if data exist, masih eror tapi fungsinya :(
            // val result = checkBookmarkExist()

            // to add to database
            // insertDataToDatabase()

            // to change button text
            view.btn_bookmark2.setText("- Unbookmark")

            // to delete from database
            deleteDataFromDatabase()

            //Toast.makeText(requireContext(), "Successfully clicked!", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val id = 101
        val faskesNama = "KLINIK MPR RI"
        val faskesJenis = "KLINIK"
        val faskesAlamat = "Gedung MPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia"
        val faskesTelp = "(021) 100001"
        val faskesKode = "N0000002"
        val faskesStatus = "Tidak Siap Vaksinasi"

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
        findNavController().navigate(R.id.action_fragmentBookmarkDetail_to_listItem)
    }

    private fun checkBookmarkExist() {
        val id = 101

        // Check Data in Database
        val result = mBookmarkViewModel.checkBookmarkExist(id)

        Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_LONG).show()

        return result
    }

}