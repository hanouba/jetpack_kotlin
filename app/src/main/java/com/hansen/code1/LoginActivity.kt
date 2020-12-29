package com.hansen.code1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.hansen.code1.controler.LoginControler
import com.hansen.code1.databinding.ActivityMain2Binding
import com.hansen.code1.databinding.ActivityMainBinding
import com.hansen.code1.model.UserModel


class LoginActivity : AppCompatActivity(),
    LoginControler.OnCheckUserNameStateCallBack, LoginControler.OnDoLoginStateChange {
    lateinit var mBinding: ActivityMain2Binding

    private val loginControler by lazy {
        LoginControler()
    }

    /**
     * 是否可用
     */
    private var isUserNameAvailable = false

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

        mBinding.etUsername.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginControler.checkUserNameState(s.toString(),this@LoginActivity)
            }

        })
    }

    /**
     * 登录逻辑
     */
    private fun toLogin() {
        //登录逻辑处理
        val account: String = mBinding.etUsername.text.toString()
        val password: String = mBinding.etPassword.text.toString()


        if (!isUserNameAvailable) {
            return
        }

        loginControler.doLogin(account,password,this)



    }

    override fun accountFormatWrong() {
        mBinding.tvState.text = "用户名错误.···"
    }

    override fun passwordFormatWrong() {
        mBinding.tvState.text = "密码错误.···"
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

    override fun onNotExist() {
        isUserNameAvailable = true
        mBinding.tvState.text = "用户可用用.···"
    }

    override fun onExist() {
        isUserNameAvailable = false
        mBinding.tvState.text = "用户不可以用.···"
    }


}