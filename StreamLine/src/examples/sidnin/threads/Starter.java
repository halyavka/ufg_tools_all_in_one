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
public class Starter {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      Action action;
      if (i < 5) {
        action = new FunctionA();
      } else {
        action = new FunctionB();
      }

      ActionThread runnable = new ActionThread(action);
      new Thread(runnable).start();
    }
  }
}
