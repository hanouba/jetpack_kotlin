package com.hansen.code1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.hansen.code1.databinding.ActivityMain2Binding
import com.hansen.code1.databinding.ActivityMainBinding
import com.hansen.code1.model.UserModel


class MainActivity : AppCompatActivity(), UserModel.OnDoLoginStateChange {
    lateinit var mBinding: ActivityMain2Binding

    private val userModel by lazy {
        UserModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initlistener()


    }

    private fun initlistener() {
        mBinding.btLogin.setOnClickListener {
            toLogin()
        }
    }

    /**
     * 登录逻辑
     */
    private fun toLogin() {
        //登录逻辑处理
        val account: String = mBinding.etUsername.text.toString()
        val password: String = mBinding.etPassword.text.toString()
        //检查账号是否正确
        if (TextUtils.isEmpty(account)) {
            return
        }
        //检查密码是否正确
        if (TextUtils.isEmpty(password)) {
            return
        }

        userModel.checkUserState(account) {
            when (it) {
                0 -> {

                    //不可用
                }
                1 -> {
                    //可用
                    //给密码加密
                    //登录 异步操作
                    userModel.doLogin(this, account, password)
                    //禁止按钮可以点击  防止重复提交
                    mBinding.btLogin.isEnabled = false
                }
            }
        }

    }

    override fun onLoading() {
        mBinding.tvState.text = "登录中.···"
    }

    override fun onLoginSuccess() {
        mBinding.tvState.text = "登录成功.···"
    }

    override fun onLoginFail() {
        mBinding.tvState.text = "登录失败.···"
    }


}