package com.zwh.demo.ui.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.zwh.common.widget.ClearnEditText;
import com.zwh.common.widget.NormalTitleBar;
import com.zwh.common.widget.PasswordEditText;
import com.zwh.common.widget.ProgressDialogView;
import com.zwh.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Zhaohao
 * @Description: 注册主界面, 集成了MOB的短信验证
 * @Date 2016/12/01 11:35
 */
public class RegistActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.phoneNoText)
    ClearnEditText phoneNoText;
    @BindView(R.id.verificationCodeText)
    EditText verificationCodeText;
    @BindView(R.id.getVerficaCodeButton)
    Button getVerficaCodeButton;
    @BindView(R.id.passwordText)
    PasswordEditText passwordText;
    @BindView(R.id.registButton)
    Button registButton;
    @BindView(R.id.titleBar)
    NormalTitleBar titleBar;

    private Context context;
    private boolean isReady = false;
    private Handler handler = null;
    private String phoneStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        this.context = this;
        ButterKnife.bind(this);

        titleBar.setTitleText("注册");
        titleBar.setOnBackListener(this);

        initSMSSDK();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
//                    case 1:
//                        Map<String,String> map = (Map<String, String>) msg.obj;
//                        String country = map.get("country");
//                        String phone = map.get("phone");
//                        showMsg(country+"  "+phone);
//                        break;
                    case 2:
                        showMsg("恭喜你！通过验证");
                        finish();
                        break;
                    case 3:
                        showMsg("验证失败!");
                        break;
                    case 4:
                        ProgressDialogView.dismiss();
                        showMsg("获取验证码成功!");
                        break;
                }
            }
        };
    }

    @OnClick({R.id.getVerficaCodeButton, R.id.registButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionBack:
                finish();
                break;
            case R.id.getVerficaCodeButton:
                phoneStr = phoneNoText.getText().toString();
                if (RegexUtils.isMobileExact(phoneStr)) {
                    getVerficationCode();
                } else {
                    showMsg("手机号码格式不对");
                    return;
                }
                break;
            case R.id.registButton: //注册按钮
                submitVerfiyCode();
                break;
        }
    }


    /**
     * 获取验证码
     */
    private void getVerficationCode() {
        ProgressDialogView.show(context, "提示", "正在请求验证码...");

//        SMSSDK.getVerificationCode("+86", phoneStr);
    }

    /**
     * 提交验证码
     */
    private void submitVerfiyCode() {
        String security = verificationCodeText.getText().toString();
        String password = passwordText.getText().toString();
        if (StringUtils.isEmpty(password)) {
            showMsg("请输入密码!");
            return;
        }
        if (!StringUtils.isEmpty(security)) {
            //提交短信验证码
//            SMSSDK.submitVerificationCode("+86", phoneStr, security);//国家号，手机号码，验证码
        } else {
            showMsg("验证码不能为空!");
        }

    }

    private void initSMSSDK() {
//        SMSSDK.initSDK(this, "1982a3b8c8f50", "4b06a4f906e9d6e7b702721da1e4983f");
//        EventHandler eh = new EventHandler() {
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    //回调完成
//                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        Log.e("TAG", "提交验证码成功" + data.toString());
//                        HashMap<String, Object> mData = (HashMap<String, Object>) data;
//                        String country = (String) mData.get("country");//返回的国家编号
//                        String phone = (String) mData.get("phone");//返回用户注册的手机号
//
//                        Log.e("TAG", country + "====" + phone);
//
//                        if (phone.equals(phoneStr)) {
//                            handler.sendEmptyMessage(2);
//                        } else {
//                            handler.sendEmptyMessage(3);
//                        }
//
//                        //提交验证码成功
//                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                        handler.sendEmptyMessage(4);
//                        //获取验证码成功
//                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
//                        //返回支持发送验证码的国家列表
//                    }
//                } else {
//                    ((Throwable) data).printStackTrace();
//                }
//            }
//        };
//        SMSSDK.registerEventHandler(eh); //注册短信回调
//        isReady = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (isReady) {
//            SMSSDK.unregisterAllEventHandler();
//        }
    }

    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
