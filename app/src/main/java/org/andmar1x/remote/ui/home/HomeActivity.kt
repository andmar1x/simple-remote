package org.andmar1x.remote.ui.home

import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnMenuTabClickListener
import kotlinx.android.synthetic.main.activity_home.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.App
import org.andmar1x.remote.common.extension.consume
import org.andmar1x.remote.common.mvp.BaseActivity
import org.andmar1x.remote.ui.home.mouse.MouseControlFragment
import org.andmar1x.remote.ui.home.power.PowerControlFragment

class HomeActivity :
        BaseActivity<HomeContract.View, HomePresenter, HomeComponent>(),
        HomeContract.View,
        OnMenuTabClickListener {

    private var bottomBar: BottomBar? = null

    override fun createComponent(): HomeComponent = DaggerHomeComponent.builder()
            .appComponent(App.component)
            .build()

    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        bottomBar = BottomBar.attach(this, savedInstanceState)
        bottomBar?.setMaxFixedTabs(3)
        bottomBar?.setItemsFromMenu(R.menu.menu_bottombar, this)
        bottomBar?.mapColorForTab(0, ContextCompat.getColor(this, R.color.primary))
        bottomBar?.mapColorForTab(1, ContextCompat.getColor(this, R.color.primary))
        bottomBar?.mapColorForTab(2, ContextCompat.getColor(this, R.color.primary))
        bottomBar?.mapColorForTab(3, ContextCompat.getColor(this, R.color.primary))
        bottomBar?.setActiveTabColor(ContextCompat.getColor(this, R.color.icons_active_dark));
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        bottomBar?.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> consume { }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onMenuTabSelected(menuItemId: Int) {
        if (menuItemId == R.id.menu_item_mouse) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_container, MouseControlFragment.newInstance())
                    .commit()
        } else if (menuItemId == R.id.menu_item_power) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_container, PowerControlFragment.newInstance())
                    .commit()
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_container, Fragment())
                    .commit()
        }
    }

    override fun onMenuTabReSelected(menuItemId: Int) {
    }
}
