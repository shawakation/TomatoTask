package com.android.tomatotask.Task;

import java.util.Calendar;

public class Date {
	public String getDate() {
		Calendar localCalendar = Calendar.getInstance();
		int i = localCalendar.get(1);
		int j = 1 + localCalendar.get(2);
		int k = localCalendar.get(5);
		int l = localCalendar.get(11);
		int i1 = localCalendar.get(12);
		int i2 = localCalendar.get(10);
		if (l >= 13) {
			if (i2 == 0) i2 = 12;
			if (i1 < 10) return i + "-" + j + "-" + k + "           " + "下午" + " " + i2 + ":" + "0" + i1;
			return i + "-" + j + "-" + k + "           " + "下午" + " " + i2 + ":" + i1;
		}
		if (i2 == 0) i2 = 12;
		if (i1 < 10) return i + "-" + j + "-" + k + "           " + "下午" + " " + i2 + ":" + "0" + i1;
		return i + "-" + j + "-" + k + "           " + "上午" + " " + i2 + ":" + i1;
	}
}
