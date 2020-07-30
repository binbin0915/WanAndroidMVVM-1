package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.UserCollectBean
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/30
 */
class CollectViewModel : BaseVM() {

    private val articleRepository by lazy { ArticleRepository.instance }

    val userCollects: MutableLiveData<MutableList<UserCollectBean>> = MutableLiveData()

    /**
     * 获取站外收藏文章列表
     * Create by Robbin at 2020/7/30
     */
    fun getWanCollect() = articleRepository.getCollectArticles()

    /**
     * 获取站外收藏文章列表
     * Create by Robbin at 2020/7/30
     */
    fun getUserCollectArticles() {
        launchGo(
            block = { userCollects.value = ApiService.getApi().getUserCollectArticles().data }
        )
    }

}