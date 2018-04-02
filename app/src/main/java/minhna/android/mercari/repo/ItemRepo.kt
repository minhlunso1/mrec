package minhna.android.mercari.repo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import minhna.android.mercari.model.Item
import minhna.android.mercari.model.ItemsCache
import org.json.JSONObject
import java.nio.charset.Charset

/**
 * Created by minhnguyen on 3/5/18.
 */
class ItemRepo {
    var compositeDisposable = CompositeDisposable()
    private val itemCache: ItemsCache = ItemsCache()

    fun getItems(context: Context, @Item.Companion.ItemType itemType: Long): LiveData<List<Item>> {
        val data = MutableLiveData<List<Item>>()

        val cached = itemCache.get(itemType)
        if (cached != null) {
            data.value = cached
            return data
        }

        compositeDisposable.add(
                parseFile(context, itemType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            items ->
                            data.value = items
                            itemCache.put(itemType, items)
                })
        )

        return data
    }

    private fun parseFile(context: Context, @Item.Companion.ItemType itemType: Long): Observable<List<Item>> {
        return Observable.create {
            subscriber ->
            val list = mutableListOf<Item>()

            var fileName: String = ""
            when (itemType) {
                Item.ALL -> fileName = "all.json"
                Item.MEN -> fileName = "men.json"
                Item.WOMEN -> fileName = "women.json"
            }
            val content = context.assets.open(fileName).readBytes().toString(Charset.defaultCharset())
            val json = JSONObject(content)
            val items = Gson().fromJson(json.getJSONArray("data").toString(), Array<Item>::class.java)

            list.addAll(items)
            subscriber.onNext(list)
        }
    }
}

