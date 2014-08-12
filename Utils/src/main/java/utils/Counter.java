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

import java.util.ArrayList;

/**
 * <p><br>
 *
 * @author: <yuri> <solomin> <(solomin)>.
 * </p>
 * @date: Jan 19, 2010
 */
public abstract class Counter {
  private static int successTestsCount;
  private static int failTestsCount;
  private static ArrayList<String> failTestsNames = new ArrayList<String>();
  private static int particularCounter=0;
  public static int getParticularCounter() {
    return particularCounter;
  }

  public static void setParticularCounter(int particularCounter) {
    Counter.particularCounter += particularCounter;
  }
  public static void resetParticularCounter(){
    particularCounter=0;
  }
  public static int getSuccessTestsCount() {
    return successTestsCount;
  }

  public static void setSuccessTestsCount(int successTestsCount) {
    Counter.successTestsCount += successTestsCount;
  }

  public static int getFailTestsCount() {
    return failTestsCount;
  }

  public static void setFailTestsCount(int failTestsCount) {
    Counter.failTestsCount += failTestsCount;
  }

  public static void clearFailTestsName() {
    failTestsNames.clear();
  }

  public static ArrayList<String> getFailTestsNames() {
    return failTestsNames;
  }

  public static void addFailTestsNames(String testName) {
    failTestsNames.add(testName);
  }

  public static String getLogRatioSuccessToFail() {
    return "Success/Fail tests - [" + successTestsCount + "/" + failTestsCount + "]";
  }

}
