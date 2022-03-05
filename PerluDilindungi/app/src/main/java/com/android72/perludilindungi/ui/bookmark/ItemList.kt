package com.android72.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android72.perludilindungi.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemList : Fragment() {

    private lateinit var mBookmarkViewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.list_item, container, false)

        // Recyclerview
        val adapter = BookmarkAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mBookmarkViewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]
        mBookmarkViewModel.readAllData.observe(viewLifecycleOwner, adapter::setData)

        return view
    }
}