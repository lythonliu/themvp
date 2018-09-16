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
package com.example.administrator.themvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * View delegate base class
 * 视图层代理的基类
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> viewSparseArray = new SparseArray<View>();

    protected View contentView;

    public abstract int getContentLayoutId();

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int contentLayoutId = getContentLayoutId();
        contentView = inflater.inflate(contentLayoutId, container, false);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    @Override
    public void initWidget() {
    }

    public <T_View extends View> T_View findView(int id) {
        T_View view = (T_View) viewSparseArray.get(id);
        if (view == null) {
            view = (T_View) contentView.findViewById(id);
            viewSparseArray.put(id, view);
        }
        return view;
    }

    public <T_View extends View> T_View getView(int id) {
        return (T_View) findView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    public void toast(CharSequence msg) {
        Toast.makeText(contentView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public <T_Activity extends Activity> T_Activity getActivity() {
        return (T_Activity) contentView.getContext();
    }
}
