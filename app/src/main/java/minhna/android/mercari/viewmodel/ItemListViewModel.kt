package minhna.android.mercari.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import minhna.android.mercari.model.Item
import minhna.android.mercari.repo.ItemRepo

/**
 * Created by minhnguyen on 3/5/18.
 */
class ItemListViewModel : ViewModel() {
    var list: LiveData<List<Item>>? = null
    private val itemRepo: ItemRepo = ItemRepo()

    fun init(context: Context?, @Item.Companion.ItemType itemType: Long) {
        context?.let {
            list = itemRepo.getItems(context, itemType)
        }

    }

    fun disposeRepo() {
        itemRepo.compositeDisposable.dispose()
    }
}

