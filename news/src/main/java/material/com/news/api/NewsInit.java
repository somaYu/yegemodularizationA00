package material.com.news.api;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

import material.com.base.BaseAppInt;

/**
 * Created by cangwang on 2017/7/25.
 */

public class NewsInit implements BaseAppInt, IProvider{

    @Override
    public void init(Context context) {

    }

    public boolean onInitSlow(Application application) {
        /**你的操作**/
        return false;
    }

    public boolean onInitFast(Application application) {
        /**你的操作**/
        return false;
    }
}
