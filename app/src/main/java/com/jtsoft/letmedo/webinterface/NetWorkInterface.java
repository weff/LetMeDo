package com.jtsoft.letmedo.webinterface;


import com.jtsoft.letmedo.bean.AccountMsgBean;
import com.jtsoft.letmedo.bean.JsonBeanSetPassword;
import com.jtsoft.letmedo.bean.ModifyPasswordBean;
import com.jtsoft.letmedo.bean.ModifyPersonalMsgBean;
import com.jtsoft.letmedo.bean.QuitBean;
import com.jtsoft.letmedo.bean.JsonBeanForgetPassword;
import com.jtsoft.letmedo.bean.JsonBeanForgetPasswordCode;
import com.jtsoft.letmedo.bean.JsonBeanLoginState;
import com.jtsoft.letmedo.bean.JsonBeanRegisterCode;
import com.jtsoft.letmedo.bean.JsonBeanUserRegister;

/**
 * Created by Administrator on 2017/6/25.
 */

public class NetWorkInterface {

    //用户注册获取验证码接口
    public interface OnGetUserRegistrCodeListener extends Listener {
        void onGetUserRegisterCode(JsonBeanRegisterCode jsonBeanRegisterCode);

    }
    //用户注册接口
    public interface OnGetUserRegisterListener extends Listener{
        void onGetUserRegister(JsonBeanUserRegister.ResponseBean responseBean);
    }
    //登录接口
//    public interface OnGetNewTokenListener extends Listener{
//        void onGetNewToken(JsonBeanGetToken jsonBeanGetToken);
//    }

    //用户设置密码接口
    public interface OnSetPasswordListener extends Listener{
        void onSetPassword(JsonBeanSetPassword jsonBeanSetPassword);
    }
    //忘记密码--获取找回密码的验证码接口
    public interface OnGetForgetPasswordCodeListener extends Listener{
        void onGetFrogetPasswordCode(JsonBeanForgetPasswordCode jsonBeanForgetPasswordCode);
    }
    //忘记密码--重置密码的接口
    public interface OnGetForgetPasswordListener extends Listener{
        void onGetForgetword(JsonBeanForgetPassword jsonBeanForgetPassword);
    }

    // 判断是否登录
    public interface OnGetLoginStateListener extends Listener {
        void onGetLoginState(JsonBeanLoginState jsonBeanLoginState);
    }
    //退出登录
    public interface OnQuitListener extends Listener{
        void OnQuit(QuitBean quitBean);
    }
    //修改密码
    public interface OnModifyPasswordListener extends Listener{
        void OnModifyPassword(ModifyPasswordBean modifyPasswordBean);
    }

    //获取个人信息
    public interface OnGetAccountMsgListener extends Listener{
        void OnGetAccountMsg(AccountMsgBean accountMsgBean) ;
    }
    //修改个人信息
    public interface OnModifyPersonalMsgListener extends Listener{
        void OnModifyPersonalMsg(ModifyPersonalMsgBean modifyPersonalMsgBean);
    }
}
