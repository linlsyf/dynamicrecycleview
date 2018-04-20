package com.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTool {    
    /**  
     * �������ڸ�ʽ.Ĭ����'yyyy-MM-dd'.  
     * @param date  
     * @return  
     */  
    public static String toString(Date date){   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        return sdf.format(date);   
    }          
       
 //ָ�������ַ���ת��Ϊ����   
        public static Date getDate(String dateStr, String formateStr) {   
        SimpleDateFormat formatter2 = new SimpleDateFormat(formateStr);   
        Date date = new Date();   
        try {   
            date = formatter2.parse(dateStr);   
        } catch (ParseException e) {
        	date = now();
            e.printStackTrace();   
        }   
        return date;   
    }    
  
    /**  
     * ����ָ�����ڸ�ʽ���ַ���  
     * @param formate  
     * @return  
     */  
    public static String toString(Date date,String formate){   
        SimpleDateFormat sdf = new SimpleDateFormat(formate);   
        return sdf.format(date);   
    }   
       
    /**  
     * �õ�ָ�����,�·�,�ڼ����date����.  
     * @param year  
     * @param month(1��12֮������)  
     * @param day  
     * @return  
     */  
    public static Date getDate(int year,int month,int day){   
        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);   
        return calendar.getTime();   
    }   
       
    /**  
     * �õ���������Ӧ����.  
     * @return  
     */  
    public static int getYear(Date date){   
        GregorianCalendar ca = new GregorianCalendar();   
        ca.setTime(date);   
        return ca.get(Calendar.YEAR);   
    }   
       
   
    public static String getMonth(Date date){   
		DateFormat returndf = new SimpleDateFormat("yyyy-MM");
		
        return returndf.format(date);   
    }   
       
    /**  
     * �õ�һ��ĵڼ���.  
     * @return  
     */  
    public static int getDayOfYear(Date date){   
        GregorianCalendar ca = new GregorianCalendar();   
        ca.setTime(date);   
        return ca.get(Calendar.DAY_OF_YEAR);   
    }      
       
    /**  
     * �õ�һ������ĵڼ���.  
     * @return  
     */  
    public static int getDayOfMonth(Date date){   
        GregorianCalendar ca = new GregorianCalendar();   
        ca.setTime(date);   
        return ca.get(Calendar.DAY_OF_MONTH);   
    }      
       
    /**  
     * �õ��ܼ�.  
     * @return  
     */  
    public static int getDayOfWeek(Date date){   
        GregorianCalendar ca = new GregorianCalendar();   
        ca.setTime(date);   
        return ca.get(Calendar.DAY_OF_WEEK);   
    }   
       
    /**  
     * �õ�ĳ����ĳ���ڵ�ָ�����ڼ�.  
     * @param year  
     * @param week  
     * @param day Ϊ1��7֮������:1-sun-->7-sta  
     * @return  
     */  
    public static Date getDayInThisWeek(int year,int week,int day){   
        Calendar cal=Calendar.getInstance();   
        cal.set(Calendar.YEAR, year);   
        cal.set(Calendar.WEEK_OF_YEAR, week);   
        cal.set(Calendar.DAY_OF_WEEK, day);   
        return cal.getTime();   
    }   
       
    /**  
     * �õ���������һ��ĵڼ�������.  
     * @param date  
     * @return  
     */  
   public static int getWeekOfYear(Date date){   
        GregorianCalendar ca = new GregorianCalendar();   
        ca.setTime(date);   
        return ca.get(Calendar.WEEK_OF_YEAR);   
    }   
       
    /**  
     * �õ��������ڵ�ʱ����  
     * @param date1  
     * @param date2  
     * @return  
     */  
    public static long getTimeInterval(Date date1,Date date2){   
        return date1.getTime() - date2.getTime();   
    }   
       
    /**  
     * �õ��������ڵļ������.  
     * @param date1  
     * @param date2  
     * @return  
     */  
    public static int getDayInterval(Date date1,Date date2){   
        return (int)((date1.getTime() - date2.getTime())/ONEDAYMILLISECONDS);   
    }   
       
    /**  
     * �õ������Ժ��ĳһ��.  
     * @param days ����Ϊ����  
     * @return  
     */  
    public static Date afterAnyDay(Date date,int days){   
        Date newDate = new Date();   
        long tp = date.getTime();   
        tp = tp + days*ONEDAYMILLISECONDS;   
       newDate.setTime(tp);   
        return newDate;   
    }   
    
    /**  
     * �õ���ʼ����֮���ĳ��(ĳ��)(ĳ��)��������  
     * @param date ��ʼ����  
     * @param years ��ݼ��  
     * @param months �·ݼ��  
     * @param days �������  
     * @return  
     */  
    public static Date afterAnyDay(Date date,int years,int months,int days){   
        Calendar cal=Calendar.getInstance();   
        cal.setTime(date);   
        cal.add(Calendar.YEAR, years);   
        cal.add(Calendar.MONTH, months);   
        cal.add(Calendar.DATE, months);   
        return cal.getTime();   
    }   
      
   /**  
     * ����ĳһ���ĳ�µ�����.  
     * @param year  
     * @param month  
     * @return  
     */  
    public static int maxDayInMonth(int year,int month){   
        Calendar time = Calendar.getInstance();   
        //������clearһ��,��ΪĬ�ϻ�õ���ǰϵͳʱ��   
        time.clear();   
        time.set(Calendar.YEAR, year);   
        time.set(Calendar.MONTH, month - 1);   
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);   
    }   
       
    /**  
     * ��������.  
     * @return  
     */  
    public static Date now(){   
        return new Date();   
    }   
       
    /**  
     * �����ڸ�ʽת��Ϊ����.  
     * @param date  
     * @return  
     */  
    public static Calendar dateToCalendar(Date date){   
        Calendar cal=Calendar.getInstance();   
        cal.setTime(date);   
        return cal;   
    }   
       
    /**  
     * ������ת��Ϊ����.  
     * @param cal  
     * @return  
     */  
    public static Date calendarToDate(Calendar cal){   
        return cal.getTime();   
    }   
    
    /**
     * ��������ʱ��֮���������
     * @param start  ��ʼʱ��
     * @param end    ����ʱ��
     * @return
     */
    public static int secondsBetween(Date now,Date then){
        //ȷ��start��end֮ǰ
        if(then.after(now)){
            Date cal=then;
            then=now;
            now=cal;
        }
        //�ֱ�õ�����ʱ��ĺ�����
        long sl=then.getTime();
        long el=now.getTime();
    
        long ei=el-sl;    
        //���ݺ���������������
        return (int)(ei/1000);
    }    
    
    public static final long ONEDAYSECONDS = 86400;   
    public static final long ONEDAYMILLISECONDS = 86400000;   
       
    /**  
     * @param args  
     */  
    public static void main(String[] args) {    
        Date now = DateTool.now();   
        System.out.println("����������:"+DateTool.toString(now));   
        System.out.println("������:"+DateTool.toString(now,"yyyy-MM-dd HH:mm:ss"));   
        System.out.println("����������:"+DateTool.toString(now,"E")+",�Ǳ��ܵĵ�"+DateTool.getDayOfWeek(now)+"��!");   
        System.out.println("40��֮����:"+DateTool.toString(DateTool.afterAnyDay(now, 40)));   
        Date endDate = DateTool.getDate(2011, 7, 15);   
        System.out.println("���뻹��:"+DateTool.getDayInterval(now, endDate)+"�쵽20100715");   
    }   
    
    public static String toFullString(Date date){   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        return sdf.format(date);   
    } 
}