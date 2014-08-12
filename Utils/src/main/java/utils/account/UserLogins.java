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

package utils.account;


public enum UserLogins {
    LOGIC_01("logic@test.com", ""),
    IVAN("ivan", "");

    private String userLogin;
    private String userPassword;


    UserLogins(String userLogin) {
        this.userLogin = userLogin;
    }

    UserLogins(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
