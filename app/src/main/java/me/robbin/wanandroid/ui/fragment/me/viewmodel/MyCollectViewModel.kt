package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.model.UserCollectBean

/**
 *
 * Create by Robbin at 2020/7/30
 */
class MyCollectViewModel : BaseViewModel() {

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
    fun getUserCollectArticles(complete: () -> Unit) {
        launchGo(
            block = { userCollects.value = ApiService.getApi().getUserCollectArticles().data },
            complete = { complete() }
        )
    }

    fun unCollect(aid: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().deCollect(aid) },
            success = { success() }
        )
    }

    fun unCollectUrl(aid: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().deCollectUrl(aid) },
            success = { success() }
        )
    }

}