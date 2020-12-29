package com.hansen.code1.controler

import android.text.TextUtils
import com.hansen.code1.model.UserModel
import com.hansen.code1.model.UserModel.Companion.STATE_LOGIN_FAILED
import com.hansen.code1.model.UserModel.Companion.STATE_LOGIN_LODING
import com.hansen.code1.model.UserModel.Companion.STATE_LOGIN_SUCCESS
import java.util.*

class LoginControler {
    private val userModel by lazy {
        UserModel()
    }


    fun checkUserNameState(account: String, callBack: OnCheckUserNameStateCallBack) {
        userModel.checkUserState(account) {
            println(it)
            when (it) {
                0 -> {
                    callBack.onNotExist()
                }
                1 -> {
                    callBack.onExist()
                }
            }
        }
    }

    interface OnCheckUserNameStateCallBack {
        fun onNotExist()
        fun onExist()
    }

    fun doLogin(
        account: String,
        password: String,
        callBack: OnDoLoginStateChange
    ) {
        //进行登录
        //检查账号是否正确
        if (TextUtils.isEmpty(account)) {
            callBack.accountFormatWrong()
            return
        }
        //检查密码是否正确
        if (TextUtils.isEmpty(password)) {
            callBack.passwordFormatWrong()
            return
        }

        userModel.doLogin(account, password) {
            when (it) {
                STATE_LOGIN_LODING -> {
                    callBack.onLoading()
                }
                STATE_LOGIN_SUCCESS -> {
                    callBack.onLoginSuccess()
                }
                STATE_LOGIN_FAILED -> {
                    callBack.onLoginFail()
                }
            }
        }

    }

    interface OnDoLoginStateChange {

        fun accountFormatWrong()
        fun passwordFormatWrong()

        fun onLoading()

        fun onLoginSuccess();

        fun onLoginFail()
    }
}