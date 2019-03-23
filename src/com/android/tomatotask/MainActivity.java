<<<<<<< HEAD
=======
/*
 * MainActivity.java是主要的android文件
 * 1.在AndroidManifest.xml中声明，不然会报错
 * 2.调用父类方法
 * 3.加载主要布局文件
 * */

/*
 * 此项目的亮点：1.自定义了字体文件
 * 			  2.实现了圆形滚动条
 * */
>>>>>>> second commit
package com.android.tomatotask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
<<<<<<< HEAD
import android.content.SharedPreferences;
=======

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
>>>>>>> second commit
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
<<<<<<< HEAD
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
=======
>>>>>>> second commit
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
=======
/*
 * MainActivity类为主要的类，继承Activity父类(extends)
 * 在这里 OnClickListener 是一个接口，implements表示MainActivity实现了OnClickListener接口的全部方法
 * OnClickListener接口只有一个抽象方法   void onClick(View v);
 * */
>>>>>>> second commit
public class MainActivity extends Activity implements OnClickListener {
	private CircleProgressBar progressBar;
	private TextView textView;
	private TimeCount time;
	protected Animation animation;
<<<<<<< HEAD
	@SuppressWarnings("unused")
	private CharSequence tomatocharSequence;
	@SuppressWarnings("unused")
	private CharSequence breakcharSequence;
	private CharSequence[] arrCharSequences;
	private int timeSpan;
	private final String TAG = "MAIN";
	private int flag = 1;		//标志位：1:番茄未开始，2:番茄开始后的，3:番茄时间到同时休息时间未开始，4:休息时间开始后的，1:休息时间到番茄未开始
	private boolean flagHide = true;		//true为显示数字时间，false为不显示数字时间
	private int tick = 0;
	private int rest = 0;
	private long exitTime = 0;
	private int[] ID;
	private boolean showShake = true;	//震动
	private String showRing;	//铃声
	MediaPlayer player;
	private Vibrator vibrator;
	SharedPreferences myshPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * 必须先在assets下创建fonts文件夹并放入字体文件，同时提供相对路径创建Typeface对象
		 * 当使用外部字体却又发现字体无变化时（以Droid Sans代替），通常是因为该字体android没有支持
		 */
		Log.v("MAIN", "MainActivity-onCreate");
		Typeface fontFace = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
		textView = (TextView) this.findViewById(R.id.txtView);
		textView.setTypeface(fontFace);
		progressBar = (CircleProgressBar) findViewById(R.id.circleProgressbar);
		tomatocharSequence = (CharSequence) (getResources().getString(R.string.tomatocharSequence));
		breakcharSequence = (CharSequence) (getResources().getString(R.string.breakcharSequence));
		arrCharSequences = getResources().getStringArray(R.array.TomatoStart_String);
		
		//配置读取
		setReader();
		//设置progressBar最大进度
		progressBar.setMaxProgress(tick * 60);
=======
	private CharSequence[] arrCharSequences;
	private int timeSpan;
	private final String TAG = "MAIN";
	private int flag = 1;		//标志位：1:任务未开始  2:任务进行中  3:任务完成但未开始休息  4:休息开始
	private int tick = 0;		//番茄时间，单位是分钟
	private int rest = 0;		//休息时间，单位是分钟
	private long exitTime = 0;
	private int[] ID;			//存放动画
	private boolean showShake = true;	//震动
	private String showRing;	//铃声
	private MediaPlayer player;
	private Vibrator vibrator;
	private SharedPreferences myshPreferences;

	/*
	 * @Override表示重写父类方法onCreate
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		//首先调用所继承父类的方法onCreate
		setContentView(R.layout.activity_main);	//设置初始屏幕布局
		/*
		 * 必须先在assets下创建fonts文件夹并放入字体文件，同时提供相对路径创建Typeface对象
		 * Typeface中的方法createFromAsset用于在assets中找到字体类型并创建相应的对象
		 * 当使用外部字体却又发现字体无变化时，通常是因为该字体android没有支持
		 */
		Typeface fontFace = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
		/*
		 * txtView 是 layout/activity_main.xml 主界面上的“开始”文字的名称
		 * 其在 R.java 中有唯一的id
		 * this.findViewById 通过id找到并赋值给textView对象
		 * textView.setTypeface 设置文本的字体，该字体来自assets
		 * */
		textView = (TextView) this.findViewById(R.id.txtView);
		textView.setTypeface(fontFace);
		
		progressBar = (CircleProgressBar) findViewById(R.id.circleProgressbar);
		//TomatoStart_String是一个字符串数组，里面保存的是点“开始”后出现的内容
		arrCharSequences = getResources().getStringArray(R.array.TomatoStart_String);
		
		//配置读取
		ReadPreference();
		
		//设置progressBar最大进度
		progressBar.setMaxProgress(tick * 60);		//单位是秒
		/*
		 * 在主类中实现OnClickListener接口并重写onClick方法
		 * 在重载的方法中实现点击设置
		 * 注意在主类中实现了OnClickListener接口
		 * */
>>>>>>> second commit
		progressBar.setOnClickListener(this);

		//动画资源文件
		ID = new int[] { R.anim.my_alpha_action, R.anim.my_scale_action, R.anim.my_rotate_action, R.anim.alpha_scale,R.anim.alpha_rotate, R.anim.scale_rotate, R.anim.alpha_scale_rotate, R.anim.myown_design };
		stateFlag();
	}

	@Override
	protected void onPause() {
		super.onPause();
<<<<<<< HEAD
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
			}
		}
		Log.v("MAIN", "MainActivity-onPause");
	}

	/**
	 * 读取设置中的配置
	 */
	private void setReader() {
		//配置读取
		myshPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Log.v("MAIN","SharedPreferences-"+myshPreferences.getString("TomatoTime_value", "25")+"-");
		tick = Integer.parseInt(myshPreferences.getString("TomatoTime_value","25"));
		rest = Integer.parseInt(myshPreferences.getString("BreakTime_value","25"));
		showShake = myshPreferences.getBoolean("startShake", true);
		showRing = myshPreferences.getString("pref_ringtone", "");
		Log.v("MAIN","showRing-"+ myshPreferences.getString("pref_ringtone", "")+"-");
		//开发者模式
=======
		if (player != null&&player.isPlaying()) player.stop();
	}

	/**
	 * 读取数据中的配置，数据保存在 /data/data
	 */
	private void ReadPreference() {
		/*
		 * 使用SharedPreferences读取配置
		 * https://www.cnblogs.com/penger/p/4143331.html
		 * 保存在  /data/data/<package name>/shared_prefs
		 * 第一个参数是xml文件的名字，第二个参数是文件创建模式
		 * */
		myshPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		/**
		 * getString 读取字符串，若没有则新建一个
		 * */
		tick = Integer.parseInt(myshPreferences.getString("TomatoTime_value","25"));
		rest = Integer.parseInt(myshPreferences.getString("BreakTime_value","25"));
		showShake = myshPreferences.getBoolean("startShake", false);
		showRing = myshPreferences.getString("pref_ringtone", "");
		//开发者模式，返回值若为真则执行
>>>>>>> second commit
		if (myshPreferences.getBoolean("developer_Mode", false)) {
			tick = 1;
			rest = 1;
		}
	}

<<<<<<< HEAD
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);		//参数依次为总时长,和计时的时间间隔
		}
		/**
		 * 计时完毕时触发
=======
	/**
	 * 每间隔1秒，CountDownTimer便会调用onTick回调方法执行相应操作
		当倒计时结束后，CountDownTimer会调用onFinish回调方法执行相应的操作
		https://blog.csdn.net/superxlcr/article/details/78996857
	 * */
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);		//参数依次为总时长,和计时的时间间隔，单位是毫秒，调用父类方法构造
		}
		/**
		 * 计时完毕时触发
		 * 任务：改变文字，显示动画效果；震动；播放铃声；修改数据（总番茄数+1、今日番茄数+1）
>>>>>>> second commit
		 */
		@Override
		public void onFinish() {
			switch (flag) {
<<<<<<< HEAD
				case 2:{		//番茄开始后的，如果此时番茄时间到了，修改flag，提示时间到
					flag = 3;
					if (flagHide) {
						textView.setVisibility(View.VISIBLE);
						flagHide = false;
						//增加动画效果
						int randow = new Random().nextInt(8);
						animation = AnimationUtils.loadAnimation(MainActivity.this,ID[randow]);
						textView.startAnimation(animation);
					}
					if (showShake) {
						//开启震动
						vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
						long[] pattern = { 200, 500, 200, 500, 1200, 500, 200, 500 };		// 停止 开启 停止 开启
						vibrator.vibrate(pattern, -1);		//重复两次上面的pattern，如果只想震动一次，index设为-1
					}
					if (showRing != "") {
						Log.v(TAG, "Showring");
						//开启铃声
						player = new MediaPlayer();
=======
				case 2:{		//2表示是任务时间到了，转入3（休息）
					flag = 3;
					//增加动画效果
					textView.setText("点此休息！");
					int randow = new Random().nextInt(8);
					animation = AnimationUtils.loadAnimation(MainActivity.this,ID[randow]);
					textView.startAnimation(animation);
					if (showShake) {
						/*
						 * https://blog.csdn.net/xiaojun111111/article/details/51220142
						 * */
						vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
						long[] pattern = { 200, 500, 200, 500, 1200, 500, 200, 500 };
						vibrator.vibrate(pattern, -1);		//重复两次上面的pattern，如果只想震动一次，index设为-1
					}
					//若铃声不空，则播放
					if (showRing != "") {
						Log.v(TAG, "-ShowRing-");
						//开启铃声
						player = new MediaPlayer();
						/*
						 * URI https://blog.csdn.net/sunqiujing/article/details/75011871
						 * 作用是解析铃声的路径
						 * player.setDataSource 设置铃声路径
						 * */
>>>>>>> second commit
						Uri uri = Uri.parse(showRing);
						try {
							player.setDataSource(MainActivity.this, uri);
						} catch (Exception e) {
							e.printStackTrace();
						}
<<<<<<< HEAD
						final AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
						if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
							player.setAudioStreamType(AudioManager.STREAM_RING);
							Log.v(TAG, AudioManager.STREAM_RING +"");
							player.setLooping(false);
							try {
								player.prepare();
							} catch (Exception e) {
								e.printStackTrace();
							}
							player.start();
						}
					}
				}
				// 实例化SharedPreferences对象（第一步）
				SharedPreferences mySharedPreferences = getSharedPreferences("TomatoCount", Activity.MODE_PRIVATE);
				int todayTomatoCount;
				int allTomatoCount;
				todayTomatoCount = mySharedPreferences.getInt("todayTomatoCount", 0);
				allTomatoCount = mySharedPreferences.getInt("allTomatoCount", 0);
				String dateNowString = (new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())).format(new java.util.Date());	//获取当前时间
				// 实例化SharedPreferences.Editor对象（第二步）
				SharedPreferences.Editor editor = mySharedPreferences.edit();
				// 用putString的方法保存数据
				todayTomatoCount += 1;
				allTomatoCount += 1;
				editor.putInt("todayTomatoCount", todayTomatoCount);
				editor.putInt("allTomatoCount", allTomatoCount);
				editor.putString("date", dateNowString);
				// 提交当前数据
				editor.commit();
				Log.v("MAIN", "-------todayTomatoCount---------"+ todayTomatoCount + "-------");
				Log.v("MAIN", "-------allTomatoCount---------" + allTomatoCount+ "-------");
				Log.v("MAIN", "-------dateNowString---------" + dateNowString+ "-------");
				Toast.makeText(MainActivity.this,getResources().getString(R.string.EndTask),Toast.LENGTH_SHORT).show();
				textView.setText("点此休息！");
				break;
=======
						//实例化AudioManager，用getSystemService(Context.AUDIO_SERVICE)
						AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
						/*
						 * getStreamVolume 只能得到流（stream)的声音
						 * AudioManager.STREAM_RING 表示电话铃声的流
						 * 若铃声的音量不为0
						 * */
						if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
							/**
							 * 设置 MediaPlayer 音频流的类型为铃声
							 * 设置 MediaPlayer 不循环播放
							 * */
							player.setAudioStreamType(AudioManager.STREAM_RING);
							player.setLooping(false);
							try {
								player.prepare();
								player.start();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					/*
					 * 创建一个名为TomatoCount的配置文件
					 * 在 shared_pref 下
					 * */
					SharedPreferences mySharedPreferences = getSharedPreferences("TomatoCount", Activity.MODE_PRIVATE);
					int todayTomatoCount = mySharedPreferences.getInt("todayTomatoCount", 0);
					int allTomatoCount = mySharedPreferences.getInt("allTomatoCount", 0);
					/**
					 * 用SimpleDateFormat中的格式来格式化当前的时间
					 * 保存为字符串
					 * */
					String dateNowString = (new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())).format(new Date());
					//实例化SharedPreferences.Editor对象
					SharedPreferences.Editor editor = mySharedPreferences.edit();
					//用putString的方法保存数据
					todayTomatoCount++;
					allTomatoCount++;
					editor.putInt("todayTomatoCount", todayTomatoCount);
					editor.putInt("allTomatoCount", allTomatoCount);
					editor.putString("curDate", dateNowString);
					//提交当前数据
					editor.commit();
					Toast.makeText(MainActivity.this,getResources().getString(R.string.EndTask),Toast.LENGTH_LONG).show();
					break;
				}
>>>>>>> second commit
			default:
				break;
			}
		}
<<<<<<< HEAD
		/**
		 * 计时过程显示
		 */
		@Override
		public void onTick(long millisUntilFinished) {
			String string = new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished));
			textView.setText(string);
=======
		/*
		 * 每计时一秒执行
		 */
		@Override
		public void onTick(long millisUntilFinished) {
			String string = new SimpleDateFormat("mm:ss",Locale.getDefault()).format(new Date(millisUntilFinished));
			textView.setText(string);
			//设置进度并重画
>>>>>>> second commit
			progressBar.setProgressNotInUiThread((int) (millisUntilFinished / 1000));
		}
	}

<<<<<<< HEAD
	@SuppressLint("SimpleDateFormat")
=======
>>>>>>> second commit
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(MainActivity.this, SettingFragment.class);
			item.setIntent(intent);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (flag == 2) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
				alertBuilder
						.setTitle("放弃？")
						.setMessage("是否放弃这个番茄并退出吗？")
						.setPositiveButton("确定",new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,int which) {
										time.cancel();
										MainActivity.this.finish();
									}
								})
						.setNegativeButton("取消",new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,int which) {
										dialog.cancel();
									}
								}).create();
				alertBuilder.show();
			}
		}
		else {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退到主界面",Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					time.cancel();
					finish();
					System.exit(0);
				}
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
<<<<<<< HEAD
		Log.v("MAIN","-------MainActivity---------onRestart----Do Something----");
=======
>>>>>>> second commit
		SharedPreferences mySharedPreferences = getSharedPreferences("test",Activity.MODE_PRIVATE);
		tick = mySharedPreferences.getInt("tick", 25);
		rest = mySharedPreferences.getInt("rest", 5);
		mySharedPreferences.getInt("longrest", 15);
<<<<<<< HEAD
		setReader();
=======
		ReadPreference();
>>>>>>> second commit
		progressBar.setMaxProgress(tick * 60);
		//震动和声音设置
		showShake = mySharedPreferences.getBoolean("showshake", true);
		if (flag == 4) {
			flag = 1;
<<<<<<< HEAD
			if (flagHide) {
				textView.setVisibility(View.VISIBLE);
				flagHide = false;
				//增加动画效果
				int randow = new Random().nextInt(8);
				animation = AnimationUtils.loadAnimation(MainActivity.this,ID[randow]);
				textView.startAnimation(animation);
			}
=======
			textView.setVisibility(View.VISIBLE);
			//增加动画效果
			int randow = new Random().nextInt(8);
			animation = AnimationUtils.loadAnimation(MainActivity.this,ID[randow]);
			textView.startAnimation(animation);
>>>>>>> second commit
			stateFlag();
		}
	}

<<<<<<< HEAD
=======
	/*
	 * 点击之后的事件（点击之后的动画效果）
	 * 首先通过id判断是否点击了圆，点击了则展示动画效果
	 * */
>>>>>>> second commit
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.circleProgressbar:
<<<<<<< HEAD
			stateFlag();
			if (flagHide) {
				textView.setVisibility(View.VISIBLE);
				flagHide = false;
				//增加动画效果
				int randow = new Random().nextInt(8);
				animation = AnimationUtils.loadAnimation(this, ID[randow]);
				textView.startAnimation(animation);
			} else {
				textView.setVisibility(View.INVISIBLE);
				flagHide = true;
			}
=======
			/**
			 * 2、4则无效，1、3则进入相应的状态
			 * */
			stateFlag();
			//增加动画效果
			int randow = new Random().nextInt(8);
			animation = AnimationUtils.loadAnimation(this, ID[randow]);
			textView.startAnimation(animation);
>>>>>>> second commit
			break;
		default:
			break;
		}
	}

	/**
	 * 番茄状态标志，如果是1则开始番茄，同时置flag为2；如果是3则番茄时间到，开始休息，flag置为4
<<<<<<< HEAD
=======
	 * 其实是2、4时点击无效
>>>>>>> second commit
	 */
	private void stateFlag() {
		switch (flag) {
		case 1:
			timeSpan = tick * 60 * 1000;
			time = new TimeCount(timeSpan, 1000);		//构造CountDownTimer对象
<<<<<<< HEAD
			//随机获取arrays.xml中的数据
			int randow = new Random().nextInt(arrCharSequences.length);
			Toast.makeText(MainActivity.this, arrCharSequences[randow],Toast.LENGTH_SHORT).show();
			flag = 2;	//番茄开始后
			time.start();
			break;
		case 3:		//进入休息Activity
			Log.i(TAG, "--intent-->>" + rest);
=======
			//随机获取arrays.xml中的数据，并显示
			int randow = new Random().nextInt(arrCharSequences.length);
			Toast.makeText(MainActivity.this, arrCharSequences[randow],Toast.LENGTH_SHORT).show();
			flag = 2;	//任务开始
			time.start();
			break;
		case 3:
>>>>>>> second commit
			Intent intent = new Intent(getApplicationContext(),BreakActivity.class);
			intent.putExtra("rest", rest);
			intent.putExtra("showShake", showShake);
			intent.putExtra("showRing", showRing);
<<<<<<< HEAD
			flag = 4;
=======
			flag = 4;		//休息开始
>>>>>>> second commit
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
<<<<<<< HEAD
		Log.v("MAIN", "-------MainActivity---------onResume-------");
=======
>>>>>>> second commit
	}

	@Override
	protected void onStop() {
		super.onStop();
<<<<<<< HEAD
		Log.v("MAIN", "-------MainActivity---------onStop-------");
=======
>>>>>>> second commit
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
<<<<<<< HEAD
		Log.v("MAIN", "-------MainActivity---------onDestroy-------");
=======
>>>>>>> second commit
	}
}
