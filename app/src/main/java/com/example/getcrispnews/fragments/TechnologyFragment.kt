package com.example.getcrispnews.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getcrispnews.NewsServices
import com.example.getcrispnews.R
import com.example.getcrispnews.adapters.NewsAdapter
import com.example.getcrispnews.utils.Articles
import com.example.getcrispnews.utils.ColorPicker
import com.example.getcrispnews.utils.News
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.fragment_general.*
import kotlinx.android.synthetic.main.fragment_technology.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechnologyFragment : Fragment(R.layout.fragment_technology) {

    lateinit var  adapter : NewsAdapter
    var articles = mutableListOf<Articles>()
    val TAG = "GeneralFragment"
    var pageNum = 1
    var totalResults:Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter( view.context ,articles)
        technology_rec_view.adapter = adapter
        //technology_rec_view.layoutManager = LinearLayoutManager(view.context)

        val manager = StackLayoutManager(scrollOrientation = StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)

        manager.setItemChangedListener(object: StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                var colour = ColorPicker.getColors()
                technology_container.setBackgroundColor(Color.parseColor(colour))

                if(totalResults > manager.itemCount && manager.getFirstVisibleItemPosition() > manager.itemCount-5)
                {
                    pageNum++;
                    getNews() //Repeated calls are made till we fetch all total news.
                }
            }

        })

        technology_rec_view.layoutManager = manager
        getNews()
    }

    private fun getNews() {
        val news = NewsServices.newsInstance.getHeadlines("in", "technology",pageNum)
        news.enqueue(object: Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("CEEZYCODE", "Error in Fetcing news")
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                var news: News? = response.body()
                if(news != null)
                {
                    totalResults = news.totalResults.toInt()
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    //Log.d(TAG, news.articles.toString())

                }
            }
        })
    }

}