package com.android72.perludilindungi.ui.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.model.Bookmark
import kotlinx.android.synthetic.main.row_faskes.view.*
import androidx.navigation.findNavController

class BookmarkAdapter: RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>() {

    private var bookmarkList = emptyList<Bookmark>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_faskes, parent, false))
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookmarkList[position]
        holder.itemView.faskesNama.text = currentItem.nama
        holder.itemView.faskesJenis.text = currentItem.jenis_faskes
        holder.itemView.faskesAlamat.text = currentItem.alamat
        holder.itemView.faskesTelp.text = currentItem.telp
        holder.itemView.faskesKode.text = currentItem.kode

        // below buat click navigate to faskes detail
        holder.itemView.rowLayout.setOnClickListener {
            //val action = ListItemDirections.actionListItemToFragmentBookmarkDetail(currentItem)
            holder.itemView.findNavController().navigate(R.id.action_listItem_to_fragmentBookmarkDetail)
        }
    }

    fun setData(bookmark: List<Bookmark>){
        this.bookmarkList = bookmark
        notifyDataSetChanged()
    }
}