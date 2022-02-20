package com.android72.perludilindungi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android72.perludilindungi.ui.berita.BeritaFragment
import com.android72.perludilindungi.ui.faskes.FaskesFragment
import com.android72.perludilindungi.ui.bookmark.BookmarkFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when (position)
        {
            0 -> BeritaFragment()
            1 -> FaskesFragment()
            2 -> BookmarkFragment()
            else -> BeritaFragment()
        }
    }

}