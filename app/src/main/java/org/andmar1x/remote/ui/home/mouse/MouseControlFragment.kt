package org.andmar1x.remote.ui.home.mouse

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.fragment_mouse.*
import org.andmar1x.remote.R
import org.andmar1x.remote.common.App
import org.andmar1x.remote.common.extension.consume
import org.andmar1x.remote.common.extension.toggleSoftInput
import org.andmar1x.remote.common.mvp.BaseFragment

class MouseControlFragment :
        BaseFragment<MouseControlContract.View, MouseControlPresenter, MouseControlComponent>(),
        MouseControlContract.View,
        View.OnClickListener {

    private var keyboardShown = false

    override fun createComponent(): MouseControlComponent = DaggerMouseControlComponent.builder()
            .appComponent(App.component)
            .build()

    override fun layoutId(): Int = R.layout.fragment_mouse;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leftButton.setOnClickListener(this)
        rightButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_mouse, menu)
        val menuItem = menu.findItem(R.id.action_keyboard)
        menuItem?.setIcon(if (keyboardShown) R.drawable.ic_keyboard_hide_white else R.drawable.ic_keyboard_white)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_keyboard -> consume { presenter.toggleKeyboard(keyboardShown) }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        else if (v.equals(leftButton)) presenter.leftButtonClick()
        else if (v.equals(rightButton)) presenter.rightButtonClick()
    }

    override fun toggleKeyboard(show: Boolean) {
        toggleSoftInput()
        keyboardShown = !show
        ActivityCompat.invalidateOptionsMenu(activity)
    }

    companion object {

        fun newInstance(): MouseControlFragment {
            return MouseControlFragment()
        }
    }
}
