package cn.com.zzy.libfragment.ac;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cn.com.zzy.libfragment.R;
import cn.com.zzy.multi.MyLog;

/*
兼容v4包-1.6最低兼容到android 1.6版本
    1、自己的Activity需要继承v4包的FragmentActivity
    2、FragmentManager fm = getSupportFragmentManager();
android3.0版本开始自带Fragment
    1、自己的Activity需要Activity即可
    2、FragmentManager fm = getFragmentManager();
传参 -- Bundle
    Activity中：
        MyFragment myFragment = LifeCycleFragment.newInstance("参数1","参数2");
    Fragment中：
        public static LifeCycleFragment newInstance(String param1, String param2) {
            LifeCycleFragment fragment = new LifeCycleFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }
        然后onCreate方法中，通过getArgments()方法，获取到bundle对象，然后通过getString的key值拿到我们传递过来的值。
为何不用构造方法传递参数 -- 如：new LifeCycleFragment("参数1","参数2")
    当Activity重构时，Activity中的Fragment也会自动重新创建，但是会自动调用无参数的构造方法，所以只有用Bundle传递参数才能保存下来。
    导致Activity重构的情况，有：横竖屏切换等

 */
public class FragmentActivity extends android.support.v4.app.FragmentActivity implements LifeCycleFragment.OnFragmentInteractionListener {

    private FragmentManager mFM;
    private FragmentTransaction mFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mFM = getSupportFragmentManager();
        mFT = mFM.beginTransaction();

        LifeCycleFragment mLifeCycleFragment = LifeCycleFragment.newInstance("参数1","参数2");
        mFT.add(R.id.lc, mLifeCycleFragment);
        //mFT.add(R.id.lc, mLifeCycleFragment, "LifeCycleTag");//带tag
        //mFT.remove(mLifeCycleFragment);//移除
        //mFT.replace()//替换
        mFT.addToBackStack("LifeCycle");//添加到回退栈
        mFT.commit();
    }

    public void back(){
        if (null != mFM && mFM.getBackStackEntryCount() > 1) {
            //return to previous fragment
            mFM.popBackStack();
        }
    }

    //Fragment中会调用这个方法
    @Override
    public void onFragmentInteraction(Uri uri) {
        MyLog.Log("~~~", "Fragment 调用 Activity中的方法");
    }
}
