package minhna.android.mercari.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import minhna.android.mercari.R
import minhna.android.mercari.model.Item
import minhna.android.mercari.view.util.inflate
import minhna.android.mercari.view.util.load
import minhna.android.mercari.view.util.setFormattedPrice

/**
 * Created by minhnguyen on 3/6/18.
 */
class ItemAdapter(var list: List<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder
            = ItemViewHolder(parent.inflate(R.layout.item))

    override fun getItemCount(): Int = list.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) = with(itemView) {
            imgItem.load(item.photo)

            if (item.status.equals(Item.SOLD_OUT))
                imgSoldOut.visibility = View.VISIBLE
            else
                imgSoldOut.visibility = View.INVISIBLE

            tvName.text = item.name
            tvLike.text = item.num_likes.toString()
            tvComment.text = item.num_comments.toString()
            tvPrice.setFormattedPrice(item.price)
        }
    }
}