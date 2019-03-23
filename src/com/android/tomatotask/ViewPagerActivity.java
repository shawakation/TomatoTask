/**
 * 模块名称：应用程序主界面相关
 * Author：HZ
 * 模块功能：应用程序主界面的创建与管理
 */
package com.android.tomatotask;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

public class ViewPagerActivity extends FragmentActivity {
	private TomatoFragment tomatoFragment;
	private TaskFragment taskFragment;
	private SettingPreferenceFragment settingPreferenceFragment;
	private ViewPager m_vp;
	//页面列表
	private ArrayList<Fragment> fragmentList;
	//标题列表
	ArrayList<String> titleList  = new ArrayList<String>();
	//通过pagerTabStrip可以设置标题的属性
	private PagerTabStrip pagerTabStrip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_layout);
		m_vp = (ViewPager) findViewById(R.id.viewpager);
		pagerTabStrip = (PagerTabStrip)findViewById(R.id.pagertab);
		//设置下划线的颜色
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_light));
		//设置背景的颜色
		pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
		pagerTabStrip.setDrawFullUnderline(false);
		pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
		pagerTabStrip.setTextAlignment(4);
		pagerTabStrip.setTextSpacing(40);
		tomatoFragment = new TomatoFragment();
		taskFragment = new TaskFragment();
		settingPreferenceFragment = new SettingPreferenceFragment();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(tomatoFragment);
		fragmentList.add(taskFragment);
		fragmentList.add(settingPreferenceFragment);
	    titleList.add("任务");
	    titleList.add("笔记");
	    titleList.add("设置");
	    m_vp.setOffscreenPageLimit(1);		//该设置默认值为1   即默认加载下一个页面， 如果为2则加载下2个页面，为0没效果
	    m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
	}
	
	public class MyViewPagerAdapter extends FragmentPagerAdapter{
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return titleList.get(position);
		}
		
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
