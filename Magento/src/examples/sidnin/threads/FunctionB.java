/*********************************************************************
 *                                                                   *
 * Copyright (c) 2002-2008 by Survey Software Services, Inc.         *
 * All rights reserved.                                              *
 *                                                                   *
 * This computer program is protected by copyright law and           *
 * international treaties. Unauthorized reproduction or distribution *
 * of this program, or any portion of it, may result in severe civil *
 * and criminal penalties, and will be prosecuted to the maximum     *
 * extent possible under the law.                                    *
 *                                                                   *
 *********************************************************************/

package examples.sidnin.threads;

/**
 * @author: Maxim Sidnin (sidnin).
 * @date: Oct 29, 2009
 */
public class FunctionB implements Action {

  public void doAction() {
    System.out.println("This is B #1 " + System.currentTimeMillis());
    try {
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("This is B #2 " + System.currentTimeMillis());
  }
}
