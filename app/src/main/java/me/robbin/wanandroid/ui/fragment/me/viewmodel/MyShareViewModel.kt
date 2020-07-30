package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel

/**
 *
 * Create by Robbin at 2020/7/30
 */
class MyShareViewModel : BaseArticlesViewModel() {

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> {
        return articleRepository.getMyShare()
    }

}