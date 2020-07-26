package me.robbin.wanandroid.ui.fragment.me

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentProjectBinding
import me.robbin.wanandroid.viewmodel.common.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/25
 */
class ProjectFragment : BaseFragment<ArticleListViewModel, FragmentProjectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_project, BR.viewModel, mViewModel)
    }

}