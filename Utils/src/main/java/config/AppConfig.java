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

package config;

import utils.AppUtil;

import java.util.Properties;

/**
 * <p><br>
 *
 * @author: <yuri> <solomin> <(solomin)>.
 * </p>
 * @date: Jun 23, 2009
 */
public abstract class AppConfig {

    private static final String VERSION_NUMBER;
    private static final String SURVEY_360;
    private static final String PAGE_LOAD;
    private static final String DB_DRIVER;
    private static final String DB_LOGIN;
    private static final String DB_PASSWORD;
    private static final String DB_DRIVER2;
    private static final String BROWSER;
    private static final String START_URL;
    private static final String PRODACTION_START_URL;
    private static final String LOGIN_URI;
    private static final String LOGOUT_URI;
    private static final String START_URL_SECURE;
    private static final String SMOKE_TEST;
    private static final String USE_TINYMCE;
    private static final String VOTING_MASTER_URL;
    private static final String URL_CONCEPTION;
    private static final String ADDITION_LOG;
    private static final String CURRENT_VERSION;
    private static final String TEST_PREVIEW;
    private static final String DB_CHECK_DELAY;
    private static final String NEW_DATABASE;
    private static final String REPORT_URL;
    private static final String SURVEY_URL;
    private static final String START_URL_FORM;
    private static final String START_URL_FRANCE;
    private static final String START_URL_BRAND;
    private static final String STORAGE_DIR;
    private static final String EF_START_URL;
    private static final String EF_SURVEY_URL;
    private static final String EF_REPORT_URL;
    private static final String DB_CONNECTION;
    private static final String DB_HOST;
    private static final String DB_NAME;
    private static final String CHECK_DB;
    private static final String SELF_INFO_LOGIN;
    private static final String SELF_INFO_PASSWORD;
    private static final String ADMIN_LOGIN;
    private static final String ADMIN_PASSWORD;



    static {
        Properties prop = AppUtil.loadPropertiesFromClassPath("/config.properties");
        VERSION_NUMBER = prop.getProperty("VERSION_NUMBER");
        SURVEY_360 = prop.getProperty("SURVEY_360");
        PAGE_LOAD = prop.getProperty("PAGE_LOAD");
        DB_DRIVER2 = prop.getProperty("DB_DRIVER2");
        BROWSER = prop.getProperty("BROWSER");
        START_URL = prop.getProperty("START_URL");
        PRODACTION_START_URL = prop.getProperty("PRODACTION_START_URL");
        START_URL_FORM = prop.getProperty("START_URL_FORM");
        START_URL_FRANCE = prop.getProperty("START_URL_FRANCE");
        START_URL_BRAND = prop.getProperty("START_URL_BRAND");
        START_URL_SECURE = prop.getProperty("START_URL_SECURE");
        DB_DRIVER = prop.getProperty("DB_DRIVER");
        SMOKE_TEST = prop.getProperty("SMOKE_TEST");
        USE_TINYMCE = prop.getProperty("USE_TINYMCE");
        VOTING_MASTER_URL = prop.getProperty("VOTING_MASTER_URL");
        URL_CONCEPTION = prop.getProperty("URL_CONCEPTION");
        ADDITION_LOG = prop.getProperty("ADDITION_LOG");
        CURRENT_VERSION = prop.getProperty("CURRENT_VERSION");
        TEST_PREVIEW = prop.getProperty("TEST_PREVIEW");
        DB_CHECK_DELAY = prop.getProperty("DB_CHECK_DELAY");
        NEW_DATABASE = prop.getProperty("NEW_DATABASE");
        REPORT_URL = prop.getProperty("REPORT_URL");
        SURVEY_URL = prop.getProperty("SURVEY_URL");
        STORAGE_DIR = prop.getProperty("STORAGE_DIR");
        EF_START_URL = prop.getProperty("EF_START_URL");
        EF_SURVEY_URL = prop.getProperty("EF_SURVEY_URL");
        EF_REPORT_URL = prop.getProperty("EF_REPORT_URL");
        DB_CONNECTION = prop.getProperty("DB_CONNECTION");
        DB_LOGIN = prop.getProperty("DB_LOGIN");
        DB_PASSWORD = prop.getProperty("DB_PASSWORD");
        DB_HOST = prop.getProperty("DB_HOST");
        DB_NAME = prop.getProperty("DB_NAME");
        CHECK_DB = prop.getProperty("CHECK_DB");
        SELF_INFO_LOGIN = prop.getProperty("SELF_INFO_LOGIN");
        SELF_INFO_PASSWORD = prop.getProperty("SELF_INFO_PASSWORD");
        ADMIN_LOGIN = prop.getProperty("ADMIN_LOGIN");
        ADMIN_PASSWORD = prop.getProperty("ADMIN_PASSWORD");
        LOGIN_URI=prop.getProperty("LOGIN_URI");
        LOGOUT_URI=prop.getProperty("LOGOUT_URI");
    }

    public static String getLoginUri(){
        return LOGIN_URI;
    }
    public static String getLogoutUri() {
        return LOGOUT_URI;
    }
    public static String getSurvey360() {
        return SURVEY_360;
    }

    public static String getPageLoad() {
        return PAGE_LOAD;
    }

    public static String getDbDriver2() {
        return DB_DRIVER2;
    }

    public static int getVersionNumber() {
        return Integer.parseInt(VERSION_NUMBER.substring(1, VERSION_NUMBER.length()));
    }

    public static String getDbDriver() {
        return DB_DRIVER;
    }

    public static String getBrowser() {
        return BROWSER;
    }

    public static String getStartUrlSecure() {
        return START_URL_SECURE;
    }

    public static String getStartUrl() {
        return START_URL;
    }

    public static String getProdactionStartUrl() {
        return PRODACTION_START_URL;
    }

    public static String getStartUrlForForm(){
        return START_URL_FORM;
    }

    public static String getStartUrlForFrance(){
        return START_URL_FRANCE;
    }

    public static String getStartUrlForBrand(){
        return START_URL_BRAND;
    }

    public static String getSmokeTest() {
        return SMOKE_TEST;
    }

    public static String getUseTinymce() {
        return USE_TINYMCE;
    }

    public static String getVotingMasterURL() {
        return VOTING_MASTER_URL;
    }

    public static String getURLConception() {
        return URL_CONCEPTION;
    }

    public static String getAdditionLog() {
        return ADDITION_LOG;
    }

    public static String getCurrentVersion() {
        return CURRENT_VERSION;
    }

    public static String getTestPreview() {
        return TEST_PREVIEW;
    }

    public static String getDbCheckDelay() {
        return DB_CHECK_DELAY;
    }

    public static String getNewDatabase() {
        return NEW_DATABASE;
    }

    public static String getReportUrl() {
        return REPORT_URL;
    }

    public static String getSurveyUrl() {
        return SURVEY_URL;
    }

    public static String getVotingMasterUrl() {
        return VOTING_MASTER_URL;
    }

    public static String getUrlConception() {
        return URL_CONCEPTION;
    }

    public static String getStorageDir() {
        return STORAGE_DIR;
    }

    public static String getEfStartUrl() {
        return EF_START_URL;
    }

    public static String getEfSurveyUrl() {
        return EF_SURVEY_URL;
    }

    public static String getEfReportUrl() {
        return EF_REPORT_URL;
    }

    public static String getDbConnection() {
        return DB_CONNECTION;
    }

    public static String getDbLogin() {
        return DB_LOGIN;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getDbHost() {
        return DB_HOST;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static boolean getCheckDb() {
        return CHECK_DB.equals("true");
    }

    public static String getSelfInfoLogin() {
        return SELF_INFO_LOGIN;
    }

    public static String getSelfInfoPassword() {
        return SELF_INFO_PASSWORD;
    }

    public static String getAdminLogin(){
        return ADMIN_LOGIN;
    }

    public static String getAdminPassword(){
        return ADMIN_PASSWORD;
    }
}
