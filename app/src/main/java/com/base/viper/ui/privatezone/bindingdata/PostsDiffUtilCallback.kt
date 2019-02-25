package com.base.viper.ui.privatezone.bindingdata

import android.support.v7.util.DiffUtil
import com.base.viper.data.network.model.Post

class PostsDiffUtilCallback(private val oldList: List<Post>, private val newList: List<Post>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        true // for the sake of simplicity we return true here but it can be changed to reflect a fine-grained control over which part of our views are updated
}