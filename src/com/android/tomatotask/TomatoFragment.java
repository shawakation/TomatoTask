/**
 * 模块名称：任务统计模块
 * 模块作用：统计今日已经完成的任务总数和历史任务总数
 * 
 */
package com.android.tomatotask;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TomatoFragment extends Fragment {
	private TextView tomatoTxtView,todayTomatoCountTextView,allTomatoCountTextView;
	private ImageView imageView;
	private View mMainView;
	private int todayTomatoCount;
	private int allTomatoCount;
	private SharedPreferences myshPreferences;
	int tick;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.tomato_layout, (ViewGroup)getActivity().findViewById(R.id.viewpager),false);
		tomatoTxtView = (TextView)mMainView.findViewById(R.id.tomatoTxtView);
		todayTomatoCountTextView = (TextView)mMainView.findViewById(R.id.todayTomatoCount);
		allTomatoCountTextView = (TextView)mMainView.findViewById(R.id.allTomatoCount);
		imageView =  (ImageView)mMainView.findViewById(R.id.imageTomato);
		//修改字体
		Typeface fontFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
		tomatoTxtView.setTypeface(fontFace);
		todayTomatoCountTextView.setTypeface(fontFace);
		allTomatoCountTextView.setTypeface(fontFace);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		ViewGroup p = (ViewGroup)mMainView.getParent();
		if (p!=null) p.removeAllViewsInLayout();
		return mMainView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//时间日期的读取，如果时间不是今天的时间，则置今日番茄数为 0
		//配置读取
		SharedPreferences mySharedPreferences = getActivity().getSharedPreferences("TomatoCount",Activity.MODE_PRIVATE);
		String dateStr = mySharedPreferences.getString("date", "2001-01-01");
		todayTomatoCount = mySharedPreferences.getInt("todayTomatoCount", 0);		//获取存储的今日番茄时间
		allTomatoCount = mySharedPreferences.getInt("allTomatoCount", 0);				//获取存储的合计番茄时间
		String dateNowString = (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(new java.util.Date());
		if (!dateStr.equals(dateNowString)) {			//判断存储时间是否和当前时间在同一天
			todayTomatoCount=0;
			SharedPreferences.Editor editor = mySharedPreferences.edit();
			editor.putInt("todayTomatoCount", todayTomatoCount);
			editor.commit();
		}
		todayTomatoCountTextView.setText("今日:"+todayTomatoCount);
		allTomatoCountTextView.setText("总计:"+allTomatoCount);
		//修改主界面番茄图片
		myshPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		tick = Integer.parseInt(myshPreferences.getString("TomatoTime_value","25"));
		/**
		 * 根据设置休息时间的不同修改不同的番茄图片
		 * */
		switch (tick) {
		case 25:
			imageView.setImageResource(R.drawable.tomato_25);
			break;
		case 35:
			imageView.setImageResource(R.drawable.tomato_35);
			break;
		case 45:
			imageView.setImageResource(R.drawable.tomato_45);
			break;
		case 60:
			imageView.setImageResource(R.drawable.tomato_60);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
}
