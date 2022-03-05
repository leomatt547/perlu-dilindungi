package com.android72.perludilindungi.ui.faskes

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android72.perludilindungi.backend.model.Bookmark
import com.android72.perludilindungi.databinding.ActivityFaskesDetailBinding
import com.android72.perludilindungi.ui.bookmark.BookmarkDatabase
import com.android72.perludilindungi.ui.bookmark.BookmarkFragment
import com.android72.perludilindungi.ui.bookmark.BookmarkRepository
import com.android72.perludilindungi.ui.bookmark.BookmarkViewModel
import kotlinx.android.synthetic.main.row_faskes.*


class FaskesDetailActivity() : AppCompatActivity() {
    private lateinit var _binding: ActivityFaskesDetailBinding
    private lateinit var mBookmarkViewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFaskesDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(_binding.root)
        val extras = intent.extras

        /*val readAllData: LiveData<List<Bookmark>>
        val repository: BookmarkRepository
        val bookmarkDao = BookmarkDatabase.getDatabase(application).bookmarkDao()
        repository = BookmarkRepository(bookmarkDao)
        readAllData = repository.readAllData */

        mBookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)


        if (extras != null) {
            _binding.lblFaskesKode.text = "Kode: " + extras.getString("faskesKodeStr")
            _binding.lblFaskesNama.text = extras.getString("faskesNamaStr")
            _binding.lblFaskesAlamat.text = extras.getString("faskesAlamatStr")
            _binding.lblFaskesTelp.text = extras.getString("faskesTelpStr")
            _binding.lblFaskesJenis.text = extras.getString("faskesJenisStr")

            if (extras.getString("faskesJenisStr") == "PUSKESMAS") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#EF5DA8"))
            } else if (extras.getString("faskesJenisStr") == "RUMAH SAKIT") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#5D5FEF"))
            } else if (extras.getString("faskesJenisStr") == "KLINIK") {
                _binding.lblFaskesJenis.setBackgroundColor(Color.parseColor("#7879F1"))
            }

            if (extras.getString("status") == "Siap Vaksinasi") {
                _binding.imvFaskesStatus.setImageResource(com.android72.perludilindungi.R.drawable.ic_round_check_circle_80)
                _binding.lblFaskesStatus.text = "SIAP VAKSINASI"
            }else {
                _binding.imvFaskesStatus.setImageResource(com.android72.perludilindungi.R.drawable.ic_round_cancel_red_80)
                _binding.lblFaskesStatus.text = "TIDAK SIAP VAKSINASI"
            }

            var bookmarkStatus = false
            mBookmarkViewModel.readAllData.observe(this, Observer { bookmark ->
                if (bookmark.any{ b -> b.id == extras.getInt("id") }) {
                    // id udh ada di list bookmark
                    _binding.btnBookmark.setText("- Unbookmark")
                    bookmarkStatus = true
                }
            })

            _binding!!.btnMaps.setOnClickListener { // Creates an Intent that will load a map of San Francisco
                val gmmIntentUri = Uri.parse("geo:${extras.getString("latitude")},${extras.getString("longitude")}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }

            _binding!!.btnBookmark.setOnClickListener {
                /*if (bookmarkStatus == false) { */
                    // Create Bookmark Object
                    val bookmark = Bookmark(
                        100,
                        "Kode",
                        "nama",
                        "alamat",
                        "telp",
                        "jenis",
                        "status",
                        "lat",
                        "long"
                    )

                    /*val bookmark = Bookmark(
                        extras.getInt("id"),
                        extras.getString("faskesKodeStr"),
                        extras.getString("faskesNamaStr"),
                        extras.getString("faskesAlamatStr"),
                        extras.getString("faskesTelpStr"),
                        extras.getString("faskesJenisStr"),
                        extras.getString("status"),
                        extras.getString("latitude"),
                        extras.getString("longitude")
                    ) */

                    // Add Data to Database
                    mBookmarkViewModel.addBookmark(bookmark)
                    Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
                /*}
                else {
                    // del from Database
                    mBookmarkViewModel.deleteBookmark(extras.getInt("id"))

                    // nav to recycler view
                    val fragment: Fragment = BookmarkFragment()
                    val fragmentManager: FragmentManager = supportFragmentManager
                    fragmentManager.beginTransaction().replace(com.android72.perludilindungi.R.id.container, fragment).commit()
                } */


            }
        }
    }

}