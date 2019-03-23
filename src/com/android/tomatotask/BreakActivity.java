package com.android.tomatotask;

import java.text.SimpleDateFormat;
import java.util.Date;
<<<<<<< HEAD
import android.annotation.SuppressLint;
=======
import java.util.Locale;

>>>>>>> second commit
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BreakActivity extends Activity implements OnClickListener {
	private TextView breakTextView;
	private ProgressBar breakProgressBar;
	private Button returnbButton;
	private int rest=0;
	private TimeCount time;
	private int timeSpan;
<<<<<<< HEAD
	private boolean showShake = true;		//震动
	private String showRing;					//铃声
	private Vibrator vibrator;
	MediaPlayer player;

	public BreakActivity() {
		// TODO 自动生成的构造函数存根
	}
=======
	private boolean showShake = true;			//震动
	private String showRing;					//铃声
	private Vibrator vibrator;
	MediaPlayer player;
>>>>>>> second commit
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.break_layout);
		Log.v("MAIN", "BreakActivity-onCreate");
		breakProgressBar = (ProgressBar)findViewById(R.id.breakBar);
		breakTextView = (TextView)findViewById(R.id.breakTxtView);
		returnbButton = (Button)findViewById(R.id.ReturnButton);
		//修改字体
		Typeface fontFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		breakTextView.setTypeface(fontFace);
		returnbButton.setTypeface(fontFace);
		Intent intent = getIntent();
		rest = intent.getIntExtra("rest", 5);
		showShake=intent.getBooleanExtra("showShake", true);
		showRing=intent.getStringExtra("showRing");
<<<<<<< HEAD
		Log.v("MAIN", showRing+"-showRing");
		timeSpan = rest * 60 * 1000;
		breakProgressBar.setMax(rest*60);
		returnbButton.setOnClickListener(this);
		time = new TimeCount(timeSpan, 1000);		//构造CountDownTimer对象
=======
		timeSpan = rest * 60 * 1000;
		breakProgressBar.setMax(rest*60);
		returnbButton.setOnClickListener(this);
		time = new TimeCount(timeSpan, 1000);
>>>>>>> second commit
		time.start();
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
		Log.v("MAIN", "Doing BreakActivity-onPause");
=======
		if (player != null&&player.isPlaying()) player.stop();
>>>>>>> second commit
	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);		//参数依次为总时长,和计时的时间间隔
		}
		/**
		 * 计时过程显示
		 */
<<<<<<< HEAD
		@SuppressLint("SimpleDateFormat")
		@Override
		public void onTick(long millisUntilFinished) {
			int nowprogress;
			String string = new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished));
=======
		@Override
		public void onTick(long millisUntilFinished) {
			int nowprogress;
			String string = new SimpleDateFormat("mm:ss",Locale.getDefault()).format(new Date(millisUntilFinished));
>>>>>>> second commit
			breakTextView.setText(string);
			nowprogress = (int)(rest*60-millisUntilFinished/1000);
			breakProgressBar.setProgress(nowprogress);
		}
		/**
		 * 计时完毕时触发
		 */
		@Override
		public void onFinish() {
			if (showShake) {
				//开启震动
				vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				long [] pattern = {200,500,200,500,1200,500,200,500};		// 停止 开启 停止 开启
				vibrator.vibrate(pattern,-1);           //重复两次上面的pattern 如果只想震动一次，index设为-1   
			}
			if (showRing != "") {
<<<<<<< HEAD
				Log.v("MAIN", "Doing show ring");
=======
>>>>>>> second commit
				//开启铃声
				player = new MediaPlayer();
				Uri uri = Uri.parse(showRing);
				try {
					player.setDataSource(BreakActivity.this, uri);
				} catch (Exception e) {
					e.printStackTrace();
				}
				final AudioManager audioManager = (AudioManager) BreakActivity.this.getSystemService(Context.AUDIO_SERVICE);
				if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
					player.setAudioStreamType(AudioManager.STREAM_RING);
<<<<<<< HEAD
					Log.v("MAIN", AudioManager.STREAM_RING +"");
=======
>>>>>>> second commit
					player.setLooping(false);
					try {
						player.prepare();
					} catch (Exception e) {
						e.printStackTrace();
					}
					player.start();
				}
			}
			returnbButton.setVisibility(View.VISIBLE);
		}		
	}

	@Override
	public void onClick(View v) {
		finish();		//关闭当前activity 进入上一个activity该activity必须是主activity
	}
<<<<<<< HEAD
=======
	
>>>>>>> second commit
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BreakActivity.this);
			alertBuilder.setTitle("番茄？");
			alertBuilder.setMessage("放弃休息继续下一个番茄？");
			alertBuilder.setPositiveButton("番茄",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,int which) {
					time.cancel();
					BreakActivity.this.finish();
				}
			});
			alertBuilder.setNegativeButton("休息",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,int which) {
					dialog.cancel();
				}
			}).create();
			alertBuilder.show();
		}
	    return super.onKeyDown(keyCode, event);
	}
<<<<<<< HEAD
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v("MAIN", "BreakActivity-onRestart");
=======
	
	@Override
	protected void onRestart() {
		super.onRestart();
>>>>>>> second commit
	}

	@Override
	protected void onResume() {
		super.onResume();
<<<<<<< HEAD
		Log.v("MAIN", "BreakActivity-onResume");
=======
>>>>>>> second commit
	}

	@Override
	protected void onStop() {
		super.onStop();
<<<<<<< HEAD
		Log.v("MAIN", "BreakActivity-onStop");
=======
>>>>>>> second commit
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
<<<<<<< HEAD
		Log.v("MAIN", "BreakActivity-onDestroy");
	}
}
=======
	}
}
>>>>>>> second commit
