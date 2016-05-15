package org.andmar1x.remote.ui.devices

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_devices.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.App
import org.andmar1x.remote.common.extension.consume
import org.andmar1x.remote.common.extension.snackbar
import org.andmar1x.remote.common.mvp.BaseActivity
import org.andmar1x.remote.model.Device
import org.andmar1x.remote.ui.adapter.DevicesAdapter
import org.andmar1x.remote.ui.widget.RecyclerItemClickListener
import org.andmar1x.remote.util.navigation.Navigator

class DevicesActivity :
        BaseActivity<DevicesContract.View, DevicesPresenter, DevicesComponent>(),
        DevicesContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        RecyclerItemClickListener.OnItemClickListener {

    private val devicesAdapter = DevicesAdapter()

    override fun createComponent(): DevicesComponent = DaggerDevicesComponent.builder()
            .appComponent(App.component)
            .build()

    override fun layoutId(): Int = R.layout.activity_devices;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        swipeToRefresh.setColorSchemeResources(R.color.primary)
        swipeToRefresh.setOnRefreshListener(this)

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = devicesAdapter
        listView.addOnItemTouchListener(RecyclerItemClickListener(this, this))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_devices, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_refresh -> consume { startRefreshing() }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        startRefreshing()
    }

    override fun onItemClick(view: View, position: Int) {
        presenter.redirect()
    }

    override fun redirectToSplash(navigator: Navigator) {
        navigator.openSplashScreen(this)
    }

    override fun redirectToHome(navigator: Navigator) {
        navigator.openHomeScreen(this)
    }

    override fun startRefreshing() {
        presenter.refresh()
    }

    override fun finishRefreshing() {
        val devicesCount = devicesAdapter.itemCount
        if (devicesCount == 0) {
            snackbar(coordinatorLayout, "No devices found. Try again or check your settings")
        }
    }

    override fun addDevice(device: Device) {
        devicesAdapter.addItem(device)
    }

    override fun showProgress() {
        swipeToRefresh.isRefreshing = true
        listView.visibility = View.GONE
    }

    override fun hideProgress() {
        swipeToRefresh.isRefreshing = false
        listView.visibility = View.VISIBLE
    }
}
