package org.andmar1x.remote.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_power_control.view.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.extension.inflate
import org.andmar1x.remote.model.PowerControl
import java.util.*

class PowerControlsAdapter(context: Context) :
        RecyclerView.Adapter<PowerControlsAdapter.PowerControlsViewHolder>() {

    val items = ArrayList<PowerControl>()

    init {
        val icons = context.resources.obtainTypedArray(R.array.power_controls_icons)
        val titles = context.resources.obtainTypedArray(R.array.power_controls_titles)
        val descriptions = context.resources.obtainTypedArray(R.array.power_controls_descriptions)
        for (i in 0..icons.length() - 1) {
            items.add(PowerControl(PowerControl.Type.values()[i], icons.getResourceId(i, -1),
                    titles.getString(i), descriptions.getString(i)))
        }
        icons.recycle()
        titles.recycle()
        descriptions.recycle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerControlsViewHolder {
        return PowerControlsViewHolder(parent.inflate(R.layout.list_item_power_control))
    }

    override fun onBindViewHolder(holder: PowerControlsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PowerControlsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PowerControl) = with(itemView) {
            icon.setImageResource(item.icon)
            DrawableCompat.setTint(icon.drawable,
                    ContextCompat.getColor(context, R.color.icons_active_dark))
            title.text = item.title
            description.text = item.description
        }
    }
}
