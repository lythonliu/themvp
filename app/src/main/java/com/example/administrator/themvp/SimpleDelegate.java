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
package com.example.administrator.themvp;

import android.widget.TextView;

import com.example.administrator.themvp.view.AppDelegate;


/**
 * View视图层，完全移除与Presenter业务逻辑的耦合
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public class SimpleDelegate extends AppDelegate {

    @Override
    public int getContentLayoutId() {
        return R.layout.delegate_simple;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        TextView textView = getView(R.id.text);
        textView.setText("在视图代理层创建布局");
    }

    public void setText(String text) {
        //getView(id)等同于bindview(id)
        TextView textView = getView(R.id.text);
        textView.setText(text);
    }
}