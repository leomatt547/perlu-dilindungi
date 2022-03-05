package com.android72.perludilindungi.ui.faskes

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R
import com.google.android.material.card.MaterialCardView


class FaskesAdapter(context: Context, private var listFaskes: ArrayList<Faskes>)
    : RecyclerView.Adapter<FaskesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var faskesNama: TextView = itemView.findViewById(R.id.faskesNama)
        var faskesJenis: TextView = itemView.findViewById(R.id.faskesJenis)
        var faskesAlamat: TextView = itemView.findViewById(R.id.faskesAlamat)
        var faskesTelp: TextView = itemView.findViewById(R.id.faskesTelp)
        var faskesKode: TextView = itemView.findViewById(R.id.faskesKode)
        var faskesLayout: MaterialCardView = itemView.findViewById(R.id.rowLayout)

        fun faskesNamaBind(txt: String) {
            faskesNama.setText(txt)
        }

        fun faskesJenisBind(txt: String) {
            faskesJenis.setText(txt)
        }

        fun faskesAlamatBind(txt: String) {
            faskesAlamat.setText(txt)
        }

        fun faskesTelpBind(txt: String) {
            faskesTelp.setText(txt)
        }

        fun faskesKodeBind(txt: String) {
            faskesKode.setText("Kode: " + txt)
        }

        fun faskesJenisBackgroundBind(txt: String) {
            if (txt == "PUSKESMAS") {
                faskesJenis.setBackgroundColor(Color.parseColor("#EF5DA8"))
            } else if (txt == "RUMAH SAKIT") {
                faskesJenis.setBackgroundColor(Color.parseColor("#5D5FEF"))
            } else if (txt == "KLINIK") {
                faskesJenis.setBackgroundColor(Color.parseColor("#7879F1"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaskesAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_faskes, parent, false))
    }

    override fun onBindViewHolder(holder: FaskesAdapter.MyViewHolder, position: Int) {
        var faskesNamaStr: String = listFaskes.get(position).nama
        var faskesJenisStr: String = listFaskes.get(position).jenis_faskes
        var faskesAlamatStr: String = listFaskes.get(position).alamat
        var faskesTelpStr: String = listFaskes.get(position).telp
        var faskesKodeStr: String = listFaskes.get(position).kode

        holder.faskesNamaBind(faskesNamaStr)
        holder.faskesJenisBind(faskesJenisStr)
        holder.faskesAlamatBind(faskesAlamatStr)
        holder.faskesTelpBind(faskesTelpStr)
        holder.faskesKodeBind(faskesKodeStr)
        holder.faskesJenisBackgroundBind(faskesJenisStr)
        holder.faskesLayout.setOnClickListener {
            //FaskesDetail(listFaskes.get(position))
//            holder.itemView.findNavController().navigate(R.id.action_listItem_to_fragmentDetail)
            val intent = Intent( it.context, FaskesDetailActivity::class.java)
            val bundle = Bundle()
            //intent.putExtra("listFaskesDetailData", listFaskes[position]as Serializable)
            intent.putExtra("faskesNamaStr", faskesNamaStr)
            intent.putExtra("faskesJenisStr", faskesJenisStr)
            intent.putExtra("faskesAlamatStr", faskesAlamatStr)
            intent.putExtra("faskesTelpStr", faskesTelpStr)
            intent.putExtra("faskesKodeStr", faskesKodeStr)
            intent.putExtra("latitude",  listFaskes.get(position).latitude)
            intent.putExtra("longitude",  listFaskes.get(position).longitude)
            intent.putExtra("status",  listFaskes.get(position).status)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listFaskes.size;
    }

}

private fun Bundle.putParcelableArrayList(s: String, get: Faskes) {

}
