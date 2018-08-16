package material.com.news.presenter;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import material.com.base.BaseFragment;
import material.com.base.BasePresenter;
import material.com.base.utils.MetaUtil;
import material.com.base.utils.SaveUtil;
import material.com.flow.FlowFragment;
import material.com.news.BuildConfig;
import material.com.news.adapter.ViewPagerAdapter;
import material.com.news.ui.IAllNewView;

/**
 * Created by cangwang on 2017/8/4.
 */

public class AllNewsPresenter extends BasePresenter<IAllNewView>{
    private BaseFragment bf;

    private List<String> pageTitles = new Vector<>();
    private List<Fragment> pageFagments = new ArrayList<Fragment>();

    private SaveUtil dataSave;
    int channel = 0;

    public AllNewsPresenter(BaseFragment bf){
        this.bf = bf;
        channel = MetaUtil.getChannelNum(bf.getActivity().getApplicationContext());
    }

    public void getData(){
        dataSave = new SaveUtil(bf.getContext(), "gank", BuildConfig.BUILD_TYPE.equals("debug") ? SaveUtil.DEBUG : SaveUtil.PUBLISH);
        pageTitles = dataSave.getDataList("setting_data");
    }

    public void setAdapter(){
        for(String item :pageTitles){
//            Fragment tab = new NewFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("sort",item);
//            tab.setArguments(bundle);
            Fragment tab = (Fragment) ARouter.getInstance().build("/gank_news/new").withString("sort",item).navigation();
            pageFagments.add(tab);
        }
        pageTitles.add("Flow");
        pageFagments.add(new FlowFragment());

        getView().initPageAapter(new ViewPagerAdapter(bf.getChildFragmentManager(),pageFagments,pageTitles));
    }

    public void navigationSubmit(){
//                EventBus.getDefault().post(new SubmitStartEvent());
//                startActivity(new Intent("com.cangwang.submit"));
        ARouter.getInstance().build("/gank_submit/1").navigation();
    }

    /**
     * 跳转到设置页面
     */
    public void navigationSettings(){
//        String path = "/gank_setting";
//        if (channel == 10086) {
//            path +="/1";
//        }else if (channel == 10087){
//            path +="_server/1";
//        }
        ARouter.getInstance().build("/gank_setting/1").navigation();
    }

    @Override
    public void release() {
        bf=null;
        super.release();
    }
}
