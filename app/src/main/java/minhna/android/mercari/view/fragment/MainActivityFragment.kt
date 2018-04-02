package minhna.android.mercari.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import minhna.android.mercari.R
import minhna.android.mercari.model.Item
import minhna.android.mercari.view.util.inflate
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import minhna.android.mercari.adapter.ItemAdapter
import minhna.android.mercari.viewmodel.ItemListViewModel

class MainActivityFragment : BaseFragment() {
    @Item.Companion.ItemType var type: Long = -1
    lateinit var viewModel: ItemListViewModel

    companion object {
        fun newInstance(@Item.Companion.ItemType type: Long): MainActivityFragment {
            val fragment = MainActivityFragment()
            fragment.type = type
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)
        viewModel.init(context, type)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_main)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv.layoutManager = GridLayoutManager(context, 2)
        rv.setHasFixedSize(true)

        viewModel.list?.observe(this, Observer {
            list ->
            list?.let {
                rv.adapter = ItemAdapter(list)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.disposeRepo()
    }
}
