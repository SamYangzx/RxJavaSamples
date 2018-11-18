package pers.example.xiayong.rxjavasamples.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class TestFragment extends Fragment {
    private static final String TAG = "fenghe";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);

        //Find the +1 button
//        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.plus_one_button)
    void demoTest() {
        //订阅
        Log.w(TAG, "domeTest");
        //绑定一个观察者
        observable1.subscribe(observer);
        observable1.subscribe(subscriber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //创建一个观察者
    private Observer<String> observer = new Observer<String>() {

        @Override
        public void onCompleted() {
            Log.w(TAG, "Completed");
        }

        @Override
        public void onError(Throwable e) {
            Log.w(TAG, "Error");
        }

        @Override
        public void onNext(String s) {
            Log.w(TAG, s);
        }
    };

    //创建观察者方法2
    private Subscriber<String> subscriber = new Subscriber<String>() {

        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        public void onSubscribe(Subscription s) {
            Log.w(TAG, "开始采用subscribe连接");
        }

        // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onNext(String value) {
            Log.w(TAG, "对Next事件作出响应" + value);
        }

        // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onError(Throwable e) {
            Log.w(TAG, "对Error事件作出响应");
        }

        // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onCompleted() {
            Log.w(TAG, "对Complete事件作出响应");
        }
    };


    //使用Observable.create()创建被观察者
    private Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Wrold");
            subscriber.onCompleted();
        }
    });


}
