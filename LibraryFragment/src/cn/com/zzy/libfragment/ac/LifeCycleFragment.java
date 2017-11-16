package cn.com.zzy.libfragment.ac;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.zzy.libfragment.R;
import cn.com.zzy.multi.MyLog;

public class LifeCycleFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";//参数1 标识
    private static final String ARG_PARAM2 = "param2";//参数2 标识
    private String mParam1;//参数1
    private String mParam2;//参数2

    private OnFragmentInteractionListener mListener;//回调接口

    public LifeCycleFragment() {}

    //初始化Fragment 并传参
    public static LifeCycleFragment newInstance(String param1, String param2) {
        LifeCycleFragment fragment = new LifeCycleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //Fragment和Activity建立关联的时候调用（获得activity的传递的值）
/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            onButtonPressed(null);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //获取Activity中的传参
            mParam1 = getArguments().getString(ARG_PARAM1);
            MyLog.Log("~~~", "mParam1" + mParam1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            MyLog.Log("~~~", "mParam2" + mParam2);
        }
    }
    //为Fragment创建视图（加载布局）时调用
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_life_cycle, container, false);
    }
    //当Activity中的onCreate方法执行完后调用（表示activity执行oncreate方法完成了的时候会调用此方法）
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /********************************** Fragment is active **********************************/

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //Fragment中的布局被移除时调用（表示fragment销毁相关联的UI布局）
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Fragment和Activity解除关联的时候调用（脱离activity）
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // 回调Activity中的onFragmentInteraction方法，
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
