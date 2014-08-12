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
import junit.framework.AssertionFailedError;
import org.apache.log4j.Logger;
import org.junit.Assert;


/**
 * <p><br>
 *
 * @author: <yuri> <solomin> <(solomin)>.
 * </p>
 * @date: Aug 3, 2010
 */
public class MyAssert extends Assert {
  // ToDo предназначен для того, что бы прямо в методы сравнения и прочее была возможность вставить
  protected static Logger LOG = Logger.getLogger(MyAssert.class);
  public static void myAssertEquals(String string, String string1, String info) {
    try {
      assertEquals(string, string1);
      if (info.length()>0) {
        LOG.debug("Success: " + info);
      }
    }
    catch(AssertionFailedError afe) {
      afe.printStackTrace();
      if (info.length()>0) {
        LOG.error("Unsuccess: " + info);
      }
    }
  }

  public static void assertEquals(String string, String string1, String info) {
    boolean flag = true;
    try {
      Assert.assertEquals(string1, string);
    }
    catch (AssertionFailedError afe) {
      flag = false;
      afe.printStackTrace();
      Assert.fail();
    }
    finally {
      if (!info.isEmpty()) {
        // Добавляем механизм логирования, а пока просто информативная строка
          if(flag)LOG.debug("SUCCESS   " + "info: " + info);
          else LOG.error("BUG   " + "info: " + info);
      }
    }
  }

  public static void assertEquals(Object object1, Object object2, String info) {
    boolean flag = true;
    try {
      Assert.assertEquals(object1, object2);
    }
    catch (AssertionFailedError afe) {
      flag = false;
      afe.printStackTrace();
      Assert.fail();
    }
    finally {
      if (!info.isEmpty()) {
        // Добавляем механизм логирования, а пока просто информативная строка
          if(flag)LOG.debug("SUCCESS   " + "info: " + info);
          else LOG.error("BUG   " + "info: " + info);
      }
    }

  }

  public static void assertEquals(long long1, long long2, String info) {
    boolean flag = true;
    try {
      Assert.assertEquals(long1, long2);
    }
    catch (AssertionFailedError afe) {
      flag = false;
      afe.printStackTrace();
      Assert.fail();
    }
    finally {
      if (!info.isEmpty()) {
        // Добавляем механизм логирования, а пока просто информативная строка
          if(flag)LOG.debug("SUCCESS   " + "info: " + info);
          else LOG.error("BUG   " + "info: " + info);
      }
    }
  }

  public static void myAssertTrue(String string, boolean b, String info) {
    try {
      assertTrue(string, b);
      if (info.length()>0) {
        LOG.debug("Success: " + info);
      }
    }
    catch(AssertionFailedError afe) {
      afe.printStackTrace();
      if (info.length()>0) {
        LOG.error("Unsuccess: " + info);
      }
    }
  }

  public static void assertTrue(String string, boolean b) {
    boolean flag = true;
    try {
      Assert.assertTrue(b);
    }
    catch (AssertionFailedError afe) {
      flag = false;
      afe.printStackTrace();
      Assert.fail();
    }
    finally {
      if (!string.isEmpty()) {
        // Прикрутить лог фор джи, вместо вывода на консоль
          if(flag)LOG.debug("SUCCESS   ".concat(string));
          else LOG.error("BUG   ".concat(string));
      }
    }
  }

  public static void myAssertFalse(String string, boolean b, String info) {
    try {
      assertFalse(string, b);
      if (info.length()>0) {
        LOG.debug("Success: " + info);
      }
    }
    catch(AssertionFailedError afe) {      
      afe.printStackTrace();
      if (info.length()>0) {
        LOG.error("\nUnsuccess: " + info + "\n");
      }
    }
  }

  public static void assertFalse(String string, boolean b) {
    boolean flag = true;
    try {
      Assert.assertFalse(b);
    }
    catch (AssertionFailedError afe) {
      flag = false;
      afe.printStackTrace();
      Assert.fail();
    }
    finally {
      if (!string.isEmpty()) {
        // Прикрутить лог фор джи, вместо вывода на консоль
          if(flag)LOG.debug("SUCCESS   ".concat(string));
          else LOG.error("BUG   ".concat(string));
      }
    }
  }
}
