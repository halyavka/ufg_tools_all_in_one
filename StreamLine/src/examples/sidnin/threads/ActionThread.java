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
public class ActionThread implements Runnable {

  private Action action;

  public ActionThread(Action action) {
    this.action = action;
  }

  public void run() {
    action.doAction();
  }
}
