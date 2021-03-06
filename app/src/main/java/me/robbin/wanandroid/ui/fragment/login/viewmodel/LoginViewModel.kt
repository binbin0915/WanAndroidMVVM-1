package me.robbin.wanandroid.ui.fragment.login.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.app.ext.utils.CacheUtils
import me.robbin.wanandroid.app.ext.watcher.SimpleWatcher
import me.robbin.wanandroid.api.ApiService

/**
 * 登录模块 ViewModel
 * Create by Robbin at 2020/7/17
 */
class LoginViewModel : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData("")
    val pwd: MutableLiveData<String> = MutableLiveData("")
    val pwd2: MutableLiveData<String> = MutableLiveData("")

    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            username.value = s.toString()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            pwd.value = s.toString()
        }
    }

    val pwd2Watcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            pwd2.value = s.toString()
        }
    }

    fun login(success: () -> Unit) {
        launchOnlyResult(
            block = {
                ApiService.getApi().login(username.value.toString(), pwd.value.toString())
            },
            success = {
                CacheUtils.setUser(it)
                success()
            },
            error = { "${it.code}: ${it.errMsg}".toToast() }
        )
    }

    fun register(success: () -> Unit) {
        if (checkPwd()) {
            launchOnlyResult(
                block = {
                    ApiService.getApi().register(
                        username.value!!,
                        pwd.value!!,
                        pwd2.value!!
                    )
                },
                success = {
                    CacheUtils.setUser(it)
                    success()
                },
                error = { "${it.code}: ${it.errMsg}".toToast() }
            )
        } else {
            "两次密码不一致".toToast()
        }
    }

    private fun checkPwd(): Boolean {
        return pwd.value.equals(pwd2.value)
                && !pwd.value.isNullOrBlank()
                && !pwd2.value.isNullOrBlank()
                && !username.value.isNullOrBlank()
    }

}