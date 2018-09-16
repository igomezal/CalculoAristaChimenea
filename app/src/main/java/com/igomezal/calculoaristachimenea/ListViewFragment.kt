package com.igomezal.calculoaristachimenea


import android.content.Context
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ListViewFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return ListViewFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val activity: MainActivity = this.activity as MainActivity
        val submitActionButton = activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)
        val view = inflater.inflate(R.layout.fragment_list_view, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.chimeneaList)

        activity.currentState = States.HOME
        recyclerView.adapter = ChimeneasViewAdapter(AppDatabase.getInstance(activity.applicationContext).chimeneaDao().getAllChimeneas())
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        submitActionButton?.contentDescription = resources.getString(R.string.add_new_chimenea_navigation)
        submitActionButton?.setOnClickListener{

            activity.findViewById<BottomAppBar>(R.id.bottomAppBar)?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            submitActionButton.setImageResource(R.drawable.ic_done_36)
            activity.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.homeContainer, AddCalculatedSizeFragment.newInstance())
                    ?.commit()
        }

        return view
    }


}

class ChimeneasViewAdapter(private val chimeneas: List<Chimenea>) : RecyclerView.Adapter<ChimeneasViewAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return chimeneas.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val chimenea: Chimenea = chimeneas[position]

        viewHolder.chimeneaTitle.setText("""Lado a: ${chimenea.x} Lado b: ${chimenea.y} Altura: ${chimenea.height}""")
        viewHolder.chimeneaResult.setText("""Resultado: ${chimenea.edge}""")
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
