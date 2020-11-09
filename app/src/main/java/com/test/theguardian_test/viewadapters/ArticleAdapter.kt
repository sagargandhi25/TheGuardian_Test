package com.test.theguardian_test.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.theguardian_test.R
import com.test.theguardian_test.databinding.RowArticleBinding
import com.test.theguardian_test.domain.Article

class ArticleAdapter: RecyclerView.Adapter<ArticleViewHolder>() {

    /**
     *The Articles that our Adapter will show
     */
    var result: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val withDataBinding: RowArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArticleViewHolder.LAYOUT,
            parent,
            false
        )
        return ArticleViewHolder(withDataBinding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.result = result[position]
        }
    }

    override fun getItemCount() = result.size

}

/**
 * ViewHolder for article items. All work is done by data binding.
 */
class ArticleViewHolder(val viewDataBinding: RowArticleBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_article
    }
}