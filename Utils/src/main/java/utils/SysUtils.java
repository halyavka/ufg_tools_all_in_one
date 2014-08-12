/*********************************************************************
 *                                                                   *
 * Copyright (c) 2002-2007 by Survey Software Services, Inc.         *
 * All rights reserved.                                              *
 *                                                                   *
 * This computer program is protected by copyright law and           *
 * international treaties. Unauthorized reproduction or distribution *
 * of this program, or any portion of it, may result in severe civil *
 * and criminal penalties, and will be prosecuted to the maximum     *
 * extent possible under the law.                                    *
 *                                                                   *
 *********************************************************************/

package utils;


import hep.aida.bin.DynamicBin1D;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p><br>
 *
 * @author: <yuri> <solomin> <(solomin)>.
 * </p>
 * @date: Aug 1, 2009
 */
public class SysUtils {
  private static Logger LOG = Logger.getLogger(SysUtils.class); 
  private static SortedMap<Integer, Long> surveysMap = new TreeMap<Integer, Long>(); // Мапа которая хранит ключ, и Ид. сурвея. Может в будущем в качестве ключа, будет хранить что-то более интеллектуальное

  public static double roundValue (double value, int perception) {
    double factor=(int)Math.pow(10, perception);
    return factor*Math.round(value)/factor;
  }

  public static void sleep(long msec) {
    try {
      Thread.sleep(msec);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleepWithMessage(long msec, String message) {
    try {
      LOG.info(message);
      Thread.sleep(msec);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleep(double value) {
    try {
      Thread.sleep(Math.round(value));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void checkEqualsWithoutFailTests(String additionInfo, String waitingResult, String realResult, boolean showSuccess) {
    try {
      Assert.assertEquals(waitingResult, realResult);
      if (showSuccess) {
        LOG.info("The both values is " + waitingResult);
      }
    }
    catch (Throwable t) {
      LOG.error(additionInfo + "\nwaitingResult = " + waitingResult + "\nRealResult = " + realResult + "\n************************************");
      //t.printStackTrace();
    }
  }

  public static double getAverage(List<Long> list) {
    double sum = 0.0;
    if (list==null || list.isEmpty()) {
      return 0.0;
    }
    for (Long value : list) {
      sum += value;
    }
    return Math.round(sum/list.size());
  }

  public static long getSum(List<Long> list) {
    long sum = 0;
    if (list==null || list.isEmpty()) {
      return 0;
    }
    for (Long value : list) {
      sum += value;
    }
    return sum;
  }

  public static double getMedian(List<Long> list) {
    DynamicBin1D db1D = new DynamicBin1D();
    for (long time : list) {
      db1D.add(time);
    }
    return db1D.median();
  }

  public static String getStringForINSQLCondition(long[] array) {
    String result = "(";
    for (long a : array) {
      result += a + ",";
    }
    return result.substring(0, result.length()-1) + ")";
  }

  public static String getStringByStringList(ArrayList<String> list) {
    String result = "";
    for (String str : list) {
      result += str + "\n";
    }
    return result;
  }

  public static String getInfoStringByTimeAndList(String info, long time, List<Long> storage) {
    return info  + time + " ms. Average = " + SysUtils.getAverage(storage) + " ms. Median = " + SysUtils.getMedian(storage);
  }


  public static SortedMap<Integer, Long> getSurveysMap() {
    return surveysMap;
  }

  public static void addSurveysMap(Integer key, Long value) {
    surveysMap.put(key, value);
  }

  public static boolean checkLoadableEvents(boolean condition, int tryCount, int interval) {
    int number = 0;
    while(condition) {
      if (number++>=tryCount) {
        return false;
      }
      SysUtils.sleep(interval);
    }
    return true;
  }

  public static long getArithmeticProgressionSum(long a1, long an, long delta) {
    return (a1+an)*delta/2;
  }

  public static String getMyIP (final  String HOST) {
    String myIP = "";
    if (HOST.equals("http://ksbeta.pr1.ssstest.com")|| HOST.equals("https://ksbeta.pr1.ssstest.com"))  {
        try {
          InetAddress addr = InetAddress.getByName("ks-next.ssstest.com");       // very strange but it is truth - ks-next
          myIP = addr.getHostAddress();
          myIP=myIP.substring(0,12)+0;        // need , beacause getHostAddress return ip 92.60.188.138
        } catch (Exception e) {}
    } else {
        try {
        InetAddress addr = InetAddress.getLocalHost();
        myIP = addr.getHostAddress();
      } catch (Exception e) {}
    }
    return myIP;
  }
}
