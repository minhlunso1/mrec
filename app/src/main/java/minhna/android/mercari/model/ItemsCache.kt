package minhna.android.mercari.model

/**
 * Created by minhnguyen on 3/5/18.
 */
class ItemsCache {
    //cache with map would be effective when apply single instance of view model for multiple view instance
    private var map: HashMap<Long, List<Item>> = HashMap()

    fun put(@Item.Companion.ItemType itemType: Long, list: List<Item>) {
        map?: HashMap()
        map.put(itemType, list)
    }

    fun get(@Item.Companion.ItemType itemType: Long) = map.get(itemType)
}