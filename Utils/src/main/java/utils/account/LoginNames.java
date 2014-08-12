package utils.account;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 5/15/13
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public enum LoginNames{
  VALID_LOGIN("solominAT01@test.com"),
  LOGIN_CASE_INSENSETIVE("SOLOminAT01@test.com"),
  INVALID_LOGIN("1A1t1m1a1n1@t1e1s1t1.c1o1m"),

  ;

  String loginName;
  LoginNames(String loginName){
    this.loginName=loginName;
  }
  public String getLoginName() {
    return loginName;
  }}
