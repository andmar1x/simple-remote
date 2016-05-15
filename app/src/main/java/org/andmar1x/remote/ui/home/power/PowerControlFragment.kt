package org.andmar1x.remote.ui.home.power

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_devices.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.App
import org.andmar1x.remote.common.mvp.BaseFragment
import org.andmar1x.remote.ui.adapter.PowerControlsAdapter
import org.andmar1x.remote.ui.widget.RecyclerItemClickListener

class PowerControlFragment :
        BaseFragment<PowerControlContract.View, PowerControlPresenter, PowerControlComponent>(),
        PowerControlContract.View,
        RecyclerItemClickListener.OnItemClickListener {

    override fun createComponent(): PowerControlComponent = DaggerPowerControlComponent.builder()
            .appComponent(App.component)
            .build()

    override fun layoutId(): Int = R.layout.fragment_power;

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = PowerControlsAdapter(context)
        listView.addOnItemTouchListener(RecyclerItemClickListener(context, this))
    }

    override fun onItemClick(view: View, position: Int) {
        presenter.selectItem(position)
    }

    companion object {

        fun newInstance(): PowerControlFragment {
            return PowerControlFragment()
        }
    }
}
