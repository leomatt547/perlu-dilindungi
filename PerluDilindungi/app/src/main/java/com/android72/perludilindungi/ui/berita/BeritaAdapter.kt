package com.android72.perludilindungi.ui.berita

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.android72.perludilindungi.WebViewActivity
import com.android72.perludilindungi.backend.api.RetrofitAPI
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.logging.Handler

class BeritaAdapter : RecyclerView.Adapter<BeritaAdapter.MyViewHolder>() {

    var listBerita: ArrayList<Berita> = arrayListOf();

//    init {
//        getBeritaData();
//    }

    fun getBeritaData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://perludilindungi.herokuapp.com")
            .build()
        val retrofitAPI: RetrofitAPI = retrofitBuilder.create(RetrofitAPI::class.java)
        val retrofitData = retrofitAPI.beritaData();
        retrofitData.enqueue(object : Callback<BeritaData?> {
            override fun onResponse(call: Call<BeritaData?>?, response: Response<BeritaData?>?) {
                val responseBody = response!!.body()!!

                if (responseBody.success) {
                    for (berita in responseBody.results) {
                        val beritaTitle = berita.title
                        val beritaLink = berita.link[0]
                        val beritaDate = berita.pubDate
                        val beritaImageURL = berita.enclosure._url
                        listBerita.add(Berita(beritaTitle,beritaLink,beritaDate,beritaImageURL))
                        Log.v(null, beritaTitle);
                        Log.v(null, beritaLink);
                        Log.v(null, beritaDate);
                        Log.v(null, beritaImageURL);
                    }
                }
                else {
                    Log.v("TAG", "Cannot get Data from API");
                }
            }

            override fun onFailure(call: Call<BeritaData?>?, t: Throwable?) {
                Log.d("BeritaFragment","onFailure"+t!!.message)
            }
        })
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsTitle: TextView = view.findViewById(R.id.newsTitle);
        var newsDate: TextView = view.findViewById(R.id.newsDate);
        var newsImg: ImageView = view.findViewById(R.id.newsImg);
        var newsLink: TextView = view.findViewById(R.id.newsLink);

//        fun bindBerita(berita: Berita) {
//            newsTitle.setText(berita.title);
//            newsDate.setText(berita.pubDate);
//            newsLink.setText(berita.link);
//            BeritaImageURL(newsImg).execute(berita.imgUrl);
//        }
        fun newsTitleBind(txt: String) {
            newsTitle.setText(txt);
            newsTitle.setOnClickListener {

            }
        }

        fun newsDateBind(txt: String) {
            newsDate.setText(txt);
        }

        fun newsImgBind(txt: String) {
            BeritaImageURL(newsImg).execute(txt);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaAdapter.MyViewHolder {
        this.getBeritaData();
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_berita, parent, false);
        return MyViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: BeritaAdapter.MyViewHolder, position: Int) {
        var newsTitleStr: String = listBerita.get(position).title;
        var newsDateStr: String = listBerita.get(position).pubDate;
        var newsImgStr: String = listBerita.get(position).imgUrl;
        var newsLinkStr: String = listBerita.get(position).link;

        (holder).newsTitleBind(newsTitleStr);
        holder.newsDateBind(newsDateStr);
        holder.newsImgBind(newsImgStr);
    }

    override fun getItemCount(): Int {
        return listBerita.size;
    }

}