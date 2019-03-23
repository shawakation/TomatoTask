package com.android.tomatotask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.android.tomatotask.Task.ChangeSqlite;
import com.android.tomatotask.Task.Date;
import com.android.tomatotask.Task.DrawLine;
import com.android.tomatotask.Task.Notepad;
import com.android.tomatotask.Task.NotepadAdapter;
import com.android.tomatotask.Task.SqliteHelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TaskFragment extends Fragment {
	private View mMainView;
	public String EXPANDED = "EXPANDED";
	public NotepadAdapter adapter;
	public ArrayList<Map<String, Object>> itemList;
	public ListView listView;
	public int number;
	public Button numberButton;
	public Button topButton;
	RelativeLayout layout;
    TextView textView;
    EditText editText;
    Date getDate;
    String date;

	public TaskFragment() {
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.task_layout_main, (ViewGroup)getActivity().findViewById(R.id.viewpager), false);		//设置界面的布局
		this.numberButton = ((Button) mMainView.findViewById(R.id.number));		//相关元素赋值给变量
		this.topButton = ((Button) mMainView.findViewById(R.id.topButton));
		this.listView = ((ListView) mMainView.findViewById(R.id.listview));
		//修改字体
		Typeface fontFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
		numberButton.setTypeface(fontFace);
		topButton.setTypeface(fontFace);
		this.listView.setDivider(null);
		this.listView.setOnItemClickListener(new ItemClick());
		this.topButton.setOnClickListener(new View.OnClickListener() {
			//点击记录按钮后进行的操作
			@Override
			public void onClick(View v) {
			    textView = ((TextView)layout.findViewById(R.id.writedate));
			    editText = ((DrawLine)layout.findViewById(R.id.edittext));
			    getDate = new Date();
			    date = getDate.getDate();		//保存当前的日期
			    textView.setText(date);
			    layout = (RelativeLayout)(getActivity().getLayoutInflater().inflate(R.layout.writedown, null));	//设置点击按钮后弹出界面的布局
				new AlertDialog.Builder(getActivity()).setView(layout).setPositiveButton("保存", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SQLiteDatabase localSqLiteDatabase = new SqliteHelper(getActivity(), null, null, 0).getWritableDatabase();	//初始化数据库连接
						Notepad localNotepad = new Notepad();		//初始化一个笔记类
						ChangeSqlite localChangeSqlite = new ChangeSqlite();
						String strContent = editText.getText().toString();		//获取笔记的内容
						if (strContent.equals("")) {		//判断笔记是否为空
							Toast.makeText(getActivity(), "内容为空", Toast.LENGTH_SHORT).show();		//弹出窗口
						}
						String strTitle=strContent.length()>11?" "+strContent.substring(0, 11):strContent;	//只取笔记前11个字符
						localNotepad.setContent(strContent);		//为笔记的对象赋值
						localNotepad.setTitle(strTitle);
						localNotepad.setdata(date);
						localChangeSqlite.add(localSqLiteDatabase, localNotepad);		//更新数据库
						showUpdate();		//更新显示界面
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
			}
		});
	}
	
	class ItemClick implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> paramAdapterView,View paramView, int paramInt, long paramLong) {
			Map<String, Object> localMap = itemList.get(paramInt);
			if (((Boolean) localMap.get("EXPANDED")).booleanValue()) {
				localMap.put("EXPANDED", Boolean.valueOf(false));
			} else {
				localMap.put("EXPANDED", Boolean.valueOf(true));
			}
			adapter.notifyDataSetChanged();
		}
	}
	
	public void showUpdate() {
		this.itemList = new ArrayList<Map<String, Object>>();
		SQLiteDatabase localSqLiteDatabase = new SqliteHelper(getActivity(), null, null,0).getReadableDatabase();
		Iterator<Notepad> localIterator = new ChangeSqlite().query(localSqLiteDatabase).iterator();
		while (true) {
			if (!localIterator.hasNext()) {
				Collections.reverse(this.itemList);
				this.adapter = new NotepadAdapter(getActivity(),this, this.itemList);
				this.listView.setAdapter(this.adapter);
				if (this.itemList.size()==0) {
					number=0;
					this.numberButton.setText("");
				}
				return;
			}
			Notepad localNotepad = (Notepad)localIterator.next();
			HashMap<String, Object> localHashMap = new HashMap<String, Object>();
			localHashMap.put("titleItem", localNotepad.getTitle());
			localHashMap.put("dateItem", localNotepad.getdata());
			localHashMap.put("contentItem", localNotepad.getContent());
			localHashMap.put("idItem", localNotepad.getid());
			// 默认笔记是摊开还是折叠，true为摊开
			localHashMap.put("EXPANDED", Boolean.valueOf(true));
			this.itemList.add(localHashMap);
			this.number = this.itemList.size();
			System.out.println("number-number=" + number);
			this.numberButton.setText("(" + this.number + ")");
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		showUpdate();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) p.removeAllViewsInLayout();
		return mMainView;
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
