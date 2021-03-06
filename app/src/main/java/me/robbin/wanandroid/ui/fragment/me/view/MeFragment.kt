package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_me.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentMeBinding
import me.robbin.wanandroid.app.ext.checkLogin
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType
import me.robbin.wanandroid.ui.fragment.me.viewmodel.MeViewModel

/**
 * 我的 Fragment
 * Create by Robbin at 2020/7/10
 */
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_me, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView(savedInstanceState: Bundle?) {
        refreshMe.setOnRefreshListener {
            mViewModel.refreshInfo()
        }
    }

    override fun createObserver() {
        appViewModel.isLogin.observe(viewLifecycleOwner, Observer {
            refreshMe.isEnabled = it
            if (it) {
                mViewModel.getUserInfo()
            } else {
                mViewModel.clearUserInfo()
            }
        })
        mViewModel.refresh.observe(viewLifecycleOwner, Observer {
            refreshMe.isRefreshing = it
        })
    }

    inner class ClickProxy {

        fun goProfile() = checkLogin {
            val bundle = Bundle()
            bundle.putInt("userId", appViewModel.userInfo.value!!.id)
            nav().navigate(R.id.action_global_to_profile, bundle)
        }

        fun goIntegral() = checkLogin { nav().navigate(R.id.action_main_to_integral) }

        fun goMyCollect() = checkLogin { nav().navigate(R.id.action_main_to_my_collect) }

        fun goMyArticle() = checkLogin {
            val args = Bundle()
            args.putSerializable("type", ArticleType.MY_SHARE)
            nav().navigate(R.id.action_main_to_my_share, args)
        }

        fun goTodo() = checkLogin {
            nav().navigate(R.id.action_main_to_todo)
        }

        fun goWanAndroidSite() {
            val bundle = Bundle()
            bundle.putString("title", "玩Android")
            bundle.putString("url", "https://www.wanandroid.com/index")
            nav().navigate(R.id.action_global_to_webFragment, bundle)
        }

        fun goProject() = nav().navigate(R.id.action_main_to_project)

        fun goAboutMe() = nav().navigate(R.id.action_main_to_about)

        fun goSetting() = nav().navigate(R.id.action_main_to_setting)
    }

}