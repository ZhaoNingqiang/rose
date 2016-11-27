package com.flower.rose.model;

import com.flower.rose.base.BaseModel;

import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * @author: ningqiang
 * @time: 16/11/27
 * @description:
 */

public class LoginModel extends BaseModel {
    private void login(BmobUser.BmobThirdUserAuth userAuth){
        BmobUser.loginWithAuthData(userAuth, new LogInListener<JSONObject>() {
            @Override
            public void done(JSONObject jsonObject, BmobException e) {

            }
        });
    }
}
