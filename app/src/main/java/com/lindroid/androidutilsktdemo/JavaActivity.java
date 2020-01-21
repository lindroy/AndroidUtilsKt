package com.lindroid.androidutilsktdemo;

import android.graphics.Color;
import android.widget.TextView;
import com.lindroid.androidutilsktdemo.base.BaseActivity;

/**
 * @author Lin
 * @date 2019/2/28
 * @function 仅用于测试
 * @Description
 */
public class JavaActivity extends BaseActivity {
    private TextView textView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_java;
    }

    @Override
    public void initView() {
        super.initView();
        textView = findViewById(R.id.textView);
        textView.setTextColor(Color.BLACK);
       /* AndUtil.init(this.getApplication())
                .setLogGlobalConfig(new Function1<LogConfig, Unit>() {
                    @Override
                    public Unit invoke(LogConfig logConfig) {
                        logConfig.setLogEnable()
                        return null;
                    }
                });*/
    }


}
