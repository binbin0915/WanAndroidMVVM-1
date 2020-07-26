package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.data.datasource.IntegralDataSource
import me.robbin.wanandroid.data.datasource.IntegralRankDataSource

/**
 *
 * Create by Robbin at 2020/7/25
 */
class IntegralRepository private constructor() {

    companion object {
        val instance: IntegralRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IntegralRepository()
        }
    }

    fun getIntegralDetail() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            IntegralDataSource()
        }.flow

    fun getIntegralRank() =
        Pager(PagingConfig(pageSize = 30, enablePlaceholders = false)) {
            IntegralRankDataSource()
        }.flow

}