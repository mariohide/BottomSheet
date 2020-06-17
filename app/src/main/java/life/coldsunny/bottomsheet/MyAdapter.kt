package life.coldsunny.bottomsheet

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import life.coldsunny.bottomsheet.MyAdapter.ViewHolder

class MyAdapter(val dataList: List<Bean>) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var listener: OnItemClickListener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDesc: TextView = itemView.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_demo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = dataList[position].name
        holder.tvDesc.text = dataList[position].desc
        holder.itemView.setOnClickListener { v ->
            listener.oItemClick(
                position,
                dataList[position]
            )
        }
    }

    interface OnItemClickListener {
        fun oItemClick(position: Int, bean: Bean)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
