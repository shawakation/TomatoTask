package com.android.tomatotask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/*
 * 主界面上圆形滚动条的类
 * 实现了随时间改变滚动条增加的特性
 * */
public class CircleProgressBar extends View {
	private int maxProgress = 10;		//最大进度
	private int progress = 0;			//当前进度
	private int progressStrokeWidth = 10;	//线宽
	RectF oval;							//圆形所在的矩形区域，RectF是已定义好的类，代表一个矩形区域
	Paint paint;						//这个类可以画几何图形，文本和bitmap
	
	/*
	 * 类的构造方法1
	 * Context是一个场景，代表与操作系统的交互的一种过程
	 * 从程序的角度上来理解：Context是个抽象类，而Activity、Service、Application等都是该类的一个实现
	 * */
	
	/*
	 * 调用父类View的构造方法  public View(Context context)
	 * 用context来创建一个View
	 * */
	public CircleProgressBar(Context context) {
		super(context);
		oval = new RectF();
		paint = new Paint();
	}

	/*
	 * 调用父类View的第二种构造方法  public View(Context context, AttributeSet attrs)
	 * */
	public CircleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		oval = new RectF();
		paint = new Paint();
	}

	public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		oval = new RectF();
		paint = new Paint();
	}

	/*
	 * 重写View父类的onDraw方法，传入参数是canvas
	 * view中的Canvas对象会被当做参数传递过来，我们操作这个Canvas，效果会直接反应在View中
	 * */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/*
		 * 画最外层的大圆环，以(屏幕宽度-圆环宽度)为半径
		 * 也就是画屏幕左右边上的内切圆
		 */
		int centre = getWidth()/2;	//获取圆心的x坐标=屏幕宽度/2
		int radius = (int) (centre - progressStrokeWidth/2);	//圆环的半径=(圆心x坐标-线宽度)/2
		paint.setColor(Color.DKGRAY);		//设置圆环的颜色
		/*
		 * Paint.Style.FILL：填充内部
		 * Paint.Style.FILL_AND_STROKE：填充内部和描边
		 * Paint.Style.STROKE：描边
		 */
		paint.setStyle(Paint.Style.STROKE);
		/*
		 * 设置描边的宽度（也就是控制画笔的粗细）
		 * 这里遇到了问题，参考博客：https://blog.csdn.net/wning1/article/details/60147898
		 * */
		paint.setStrokeWidth(progressStrokeWidth);
		paint.setAntiAlias(true);		//抗锯齿
		/*
		 * Android Canvas绘图描述Android Canvas 方法总结
		 * https://blog.csdn.net/qq_35427437/article/details/80045074
		 * */
		canvas.drawCircle(centre, centre, radius, paint);		//画出圆环
		/*
		 * 圆弧 ，圆环的进度，每秒更新一次
		 */
		paint.setStrokeWidth(progressStrokeWidth);		//设置圆环的宽度
		paint.setColor(Color.rgb(0x57, 0x87, 0xb6));		//设置进度条的颜色 
		/*
		 * 用于定义的圆弧的形状和大小的界限
		 * 这里开始有一点问题，radius不能直接用centre/2，否则画不出内切圆
		 * 
		 * */
		RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
		paint.setStyle(Paint.Style.STROKE);
		/*
		 * 根据进度画圆弧 ，绘制白色圆圈，即进度条背景
		 * drawArc为画圆弧的函数，oval表示圆弧的矩形范围，-90表示开始画的角度（从x轴正向转过90度），360 * progress / maxProgress
		 * 表示圆弧画过的弧度，每秒一更新，通过（当前进度/最大进度）换算成百分比得到
		 * false 设置圆弧在绘画的时候，是否经过圆心（这里是不经过）
		 * https://blog.csdn.net/qq_18432309/article/details/51811546
		 * */
		canvas.drawArc(oval, -90, 360 * progress / maxProgress, false, paint);
	}

	public int getMaxProgress() {
		return maxProgress;
	}
	
	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		//invalidate()重绘整个View，在UI线程中执行
		this.invalidate();
	}
	
	public void setProgressNotInUiThread(int progress) {
		this.progress = progress;
		//postInvalidate()重绘整个View，在非UI线程中执行
		this.postInvalidate();
	}
}
