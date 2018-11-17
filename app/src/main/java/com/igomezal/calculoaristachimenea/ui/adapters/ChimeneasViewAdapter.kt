package com.igomezal.calculoaristachimenea.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea

class ChimeneasViewAdapter(private val chimeneas: List<Chimenea>) : RecyclerView.Adapter<ChimeneasViewAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return chimeneas.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val chimenea: Chimenea = chimeneas[position]

        viewHolder.chimeneaTitle.text = """Lado a: ${chimenea.x} Lado b: ${chimenea.y} Altura: ${chimenea.height}"""
        viewHolder.chimeneaResult.text = """Resultado: ${chimenea.edge}"""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val chimeneaView: View = layoutInflater.inflate(R.layout.chimenea_view, parent, false)

        return ViewHolder(chimeneaView)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var chimeneaTitle: TextView = itemView.findViewById(R.id.chimenea_id)
        var chimeneaResult: TextView = itemView.findViewById(R.id.chimenea_result)
    }
}