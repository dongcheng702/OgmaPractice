package com;

import java.lang.reflect.Field;
import java.util.Locale;

public class Tools {

	public static void main(String[] args) {
		CmsWorkHourBean article = new CmsWorkHourBean();
        Class<? extends CmsWorkHourBean> aClass = article.getClass();
        
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            System.out.println("dto.set"+name.substring(0, 1).toUpperCase(Locale.ROOT)+name.substring(1)+"(null);");
        }
	}

}
