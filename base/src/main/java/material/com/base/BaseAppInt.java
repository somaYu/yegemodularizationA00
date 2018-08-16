package material.com.base;

import android.app.Application;

/**
 * Created by cangwang on 2017/7/25.
 */

public interface BaseAppInt {
    boolean onInitFast(Application application);

    boolean onInitSlow(Application application);
}
