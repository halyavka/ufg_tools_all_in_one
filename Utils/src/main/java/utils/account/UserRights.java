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

// (?<=\W)(?=\d+)\d+(?=L)

package utils.account;


/**
 * <p><br>
 *
 * @author: <Dmitry> <Slobodyanik> (<sdim>).
 * </p>
 * @date: Oct 25, 2007
 */
//Сейчас работает только ALL_RIGHTS, остальные не проверял
public enum UserRights {
  ALL_RIGHTS (new long[] {-3519562490314795L, 8639498626188443643L, 484L, 0L}),
  TRIAL_RIGHTS (new long[] {-7840082136970297392L, 14614656L, 0L, 0L}),
  TRIAL_EFORM_RIGHTS (new long[] {-7840082136971870256L, 14647424L, 0L, 0L}),
  PREMIUM_RIGHTS (new long[] {72174160079978242L, 9109504L, 0L, 0L}),
  PROFESSIONAL_RIGHTS (new long[] {-7840082205689774121L, 14614656L, 0L, 0L}),
  PROFESSIONAL_UNLIMITED_RIGHTS (new long[] {-7840082205689774121L, 14614656L, 0L, 0L});

  private final long[] rights;

  UserRights(long[] rights){
    this.rights = rights;
  }

  public long[] getRights(){
    return rights;   
  }
}
