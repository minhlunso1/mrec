package minhna.android.mercari.view.view

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import minhna.android.mercari.view.util.Utils
import java.lang.reflect.Field
import minhna.android.mercari.R

/**
 * Created by Minh on 3/4/2018.
 */
class MecTabLayout : TabLayout {

    private var dividerFactor = 1

    constructor(context: Context) : super(context) {
        initTabMinWidth()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MecTabLayout)
        dividerFactor = a.getInt(R.styleable.MecTabLayout_divider_factor, 1)
//        a.recycle()
        initTabMinWidth()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MecTabLayout, defStyleAttr, 0)
        dividerFactor = a.getInt(R.styleable.MecTabLayout_divider_factor, 1)
//        a.recycle()
        initTabMinWidth()
    }

    fun setDividerFactor(dividerFactor: Int) {
        this.dividerFactor = dividerFactor
    }

    private fun initTabMinWidth() {
        val wh = Utils.getScreenSize(getContext())
        val tabMinWidth = wh[WIDTH_INDEX] / dividerFactor

        val field: Field
        try {
            field = TabLayout::class.java.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH)
            field.setAccessible(true)
            field.set(this, tabMinWidth)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    companion object {
        private val WIDTH_INDEX = 0
        private val SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth"
    }

}