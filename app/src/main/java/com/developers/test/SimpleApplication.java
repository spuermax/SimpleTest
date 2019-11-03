package com.developers.test;

import com.supermax.base.QsApplication;
import com.supermax.base.common.http.HttpBuilder;

/**
 * @Author yinzh
 * @Date 2019/11/2 16:27
 * @Description
 */
public class SimpleApplication extends QsApplication {

    @Override
    public boolean isLogOpen() {
        return false;
    }

    @Override
    public void initHttpAdapter(HttpBuilder builder) throws Exception {

    }
}
