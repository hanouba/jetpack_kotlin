package com.hansen.code1.model

import com.hansen.code1.api.Api
import com.hansen.code1.controler.LoginControler
import java.util.*

/**
 * 登录
 */
class UserModel {
    private val api by lazy {
        Api()
    }

    private val random = Random()

    companion object {
        const val STATE_LOGIN_LODING = 0
        const val STATE_LOGIN_SUCCESS = 1;
        const val STATE_LOGIN_FAILED = 2
    }


    fun checkUserState(account: String, block: (Int) -> Unit) {
        //0 表示不可用 已经登录过
        // 1 表示可用 未登录过
        block.invoke(random.nextInt(2))
    }

    fun doLogin(
        account: String,
        password: String,
        block: (Int) -> Unit
    ) {
        //进行登录
        block.invoke(STATE_LOGIN_LODING)
        val randomValue:Int = random.nextInt(2)

        if (randomValue == 1) {
            block.invoke(STATE_LOGIN_FAILED)
        }else{
            block.invoke(STATE_LOGIN_SUCCESS)
        }

    }

}