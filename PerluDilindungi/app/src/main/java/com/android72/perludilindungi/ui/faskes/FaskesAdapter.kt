package com.android72.perludilindungi.ui.faskes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android72.perludilindungi.R

class FaskesAdapter(private var listFaskes: ArrayList<Faskes>)
    : RecyclerView.Adapter<FaskesAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var faskesNama: TextView = view.findViewById(R.id.faskesNama)
        var faskesJenis: TextView = view.findViewById(R.id.faskesJenis)
        var faskesAlamat: TextView = view.findViewById(R.id.faskesAlamat)
        var faskesTelp: TextView = view.findViewById(R.id.faskesTelp)
        var faskesKode: TextView = view.findViewById(R.id.faskesKode)

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
            faskesKode.setText(txt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaskesAdapter.MyViewHolder {
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.row_faskes, parent, false);
        return FaskesAdapter.MyViewHolder(itemView);
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
    }

    override fun getItemCount(): Int {
        return listFaskes.size;
    }

}