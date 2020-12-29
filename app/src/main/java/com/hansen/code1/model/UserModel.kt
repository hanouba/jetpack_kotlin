package com.hansen.code1.model

import com.hansen.code1.api.Api
import java.util.*

/**
 * 登录
 */
class UserModel {
    private val api by lazy {
        Api()
    }

    private val random = Random()

    fun doLogin(
        callBack: OnDoLoginStateChange ,
        account : String,
        password:String) {
        //进行登录
        callBack.onLoading()
        //开始去调用登录的API
        //有结果 此操作为耗时操作
        // 0 1
        val randomIndex : Int = random.nextInt(2)
        if (randomIndex == 0) {
            callBack.onLoginSuccess()
        }else{
            callBack.onLoginFail()
        }

    }


    fun checkUserState(account: String,block:(Int)-> Unit) {
        //0 表示不可用 已经登录过
        // 1 表示可用 未登录过
        block.invoke(random.nextInt(2))
    }

     interface OnDoLoginStateChange {
        fun onLoading()

        fun onLoginSuccess();

        fun onLoginFail()
    }
}