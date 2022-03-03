package com.android72.perludilindungi.ui.berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R

class BeritaAdapter(
    private var listBerita: ArrayList<Berita>,
    private var listener: linkClickListener)
    : RecyclerView.Adapter<BeritaAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsTitle: TextView = view.findViewById(R.id.newsTitle);
        var newsDate: TextView = view.findViewById(R.id.newsDate);
        var newsImg: ImageView = view.findViewById(R.id.newsImg);
        var newsLayout: LinearLayout = view.findViewById(R.id.linearLayout1);

        fun newsTitleBind(txt: String) {
            newsTitle.setText(txt);
        }

        fun newsDateBind(txt: String) {
            newsDate.setText(txt);
        }

        fun newsImgBind(txt: String) {
            BeritaImageURL(newsImg).execute(txt);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaAdapter.MyViewHolder {
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_berita, parent, false);
        return MyViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: BeritaAdapter.MyViewHolder, position: Int) {
        var newsTitleStr: String = listBerita.get(position).title;
        var newsDateStr: String = listBerita.get(position).pubDate;
        var newsImgStr: String = listBerita.get(position).imgUrl;
        var newsLink: String = listBerita.get(position).link;

        holder.newsTitleBind(newsTitleStr);
        holder.newsDateBind(newsDateStr);
        holder.newsImgBind(newsImgStr);
        holder.newsLayout.setOnClickListener {
            listener.navigateToWeb(newsLink);
        }
    }

    override fun getItemCount(): Int {
        return listBerita.size;
    }

}