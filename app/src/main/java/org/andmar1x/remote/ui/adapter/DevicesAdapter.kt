package org.andmar1x.remote.ui.adapter

import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_device.view.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.extension.inflate
import org.andmar1x.remote.model.Device
import java.util.*

class DevicesAdapter() :
        RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>() {

    private var items = ArrayList<Device>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(parent.inflate(R.layout.list_item_device))
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Device {
        return this.items[position]
    }

    fun setItems(items: ArrayList<Device>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun addItem(item: Device) {
        this.items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Device) = with(itemView) {
            DrawableCompat.setTint(deviceIcon.drawable, ContextCompat.getColor(context, R.color.icons_active_dark))
            hostNameText.text = item.hostName
        }
    }
}
