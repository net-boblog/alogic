package com.alogic.timer.matcher.util.parser;

import java.util.Hashtable;

import com.alogic.timer.matcher.util.DateItemParser.Default;

/**
 * Cron中的DayOfWeek
 * 
 * @author duanyy
 * @since 1.6.3.37
 */
public class DayOfWeek extends Default{
	protected static int [] range = new int[]{7,1,7};
	protected static Hashtable<String,Integer> mappings;
	
	public int [] getRange(){
		return range;
	}
	public int parseSingleItem(String _item) {
		Integer found = mappings.get(_item.toUpperCase());
		if (found != null){
			return found.intValue();
		}
		return super.parseSingleItem(_item);
	}
	static{
		mappings = new Hashtable<String,Integer>();
		mappings.put("MON", 1);
		mappings.put("TUE", 2);
		mappings.put("WED", 3);
		mappings.put("THU", 4);
		mappings.put("FRI", 5);
		mappings.put("SAT", 6);
		mappings.put("SUN", 0);
	}
}
