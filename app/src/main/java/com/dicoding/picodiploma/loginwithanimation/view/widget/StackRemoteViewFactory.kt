package com.dicoding.picodiploma.loginwithanimation.view.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit.ApiService
import kotlinx.coroutines.runBlocking

class StackRemoteViewFactory(private val mContext: Context, private val apiService: ApiService) :
    RemoteViewsService.RemoteViewsFactory {
    private var mWidgetItems = listOf<ListStoryItem>()
    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        runBlocking {
            mWidgetItems = apiService.getStories().listStory
        }
    }

    override fun onDestroy() {
        mWidgetItems = emptyList()
    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val story = mWidgetItems[position]
        val extras = bundleOf(
            StoryBannerWidget.EXTRA_ITEM to story.name
        )
        val fillIntent = Intent()
        fillIntent.putExtras(extras)

        return RemoteViews(mContext.packageName, R.layout.widget_stack_item_layout).apply {
            setTextViewText(R.id.widget_item_name, story.name)
            setOnClickFillInIntent(R.id.widget_item_image, fillIntent)

            val bitmap = runBlocking {
                try {
                    Glide.with(mContext.applicationContext)
                        .asBitmap()
                        .load(story.photoUrl)
                        .submit()
                        .get()
                } catch (e: Exception) {
                    null
                }
            }


            bitmap?.let { setImageViewBitmap(R.id.widget_item_image, it) }
        }
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}