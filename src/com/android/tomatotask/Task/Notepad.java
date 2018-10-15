package com.android.tomatotask.Task;

public class Notepad {
	public String content;		//笔记内容
	public String data;		//笔记日期
	public String id;			//笔记ID
	public String title;		//标题

	public String getContent() {
		return this.content;
	}

	public String getTitle() {
		return this.title;
	}

	public String getdata() {
		return this.data;
	}

	public String getid() {
		return this.id;
	}

	public void setContent(String paramString) {
		this.content = paramString;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}

	public void setdata(String paramString) {
		this.data = paramString;
	}

	public void setid(String paramString) {
		this.id = paramString;
	}
}
