/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.themvp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.administrator.themvp.BuildConfig;
import com.example.administrator.themvp.view.IDelegate;


/**
 * Presenter base class for Activity
 * Presenter层的实现基类
 *
 * @param <T_IDelegate> View delegate class type
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public abstract class PresenterActivity<T_IDelegate extends IDelegate> extends com.lythonliu.LinkAppCompatActivity {

    @Override
    public String getAppName(){
        return BuildConfig.APP_NAME;
    }

    protected T_IDelegate t_iDelegate;

    public PresenterActivity() {
        try {
            t_iDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("onCreate IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("onCreate IDelegate error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t_iDelegate.onCreate(getLayoutInflater(), null, savedInstanceState);
        setContentView(t_iDelegate.getContentView());
        initToolbar();
        t_iDelegate.initWidget();
        bindEvenListener();
    }

    protected void bindEvenListener() {
    }

    protected void initToolbar() {
        Toolbar toolbar = t_iDelegate.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (t_iDelegate == null) {
            try {
                t_iDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("onCreate IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("onCreate IDelegate error");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (t_iDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(t_iDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t_iDelegate = null;
    }

    protected abstract Class<T_IDelegate> getDelegateClass();
}
