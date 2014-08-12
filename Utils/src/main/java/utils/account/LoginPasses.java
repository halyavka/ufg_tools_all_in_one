package utils.account;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 5/15/13
 * Time: 10:21 AM
 */
public enum LoginPasses {
  VALID_PASS("worldapp1"),
  NON_EXISTENT_PASS("brahmaputra"),
  PASS_CASE_SENSETIVE("pasSwOrD1"),
 ;

  String loginPass;
  LoginPasses(String loginPass){
    this.loginPass=loginPass;
  }
  public String getLoginPass() {
    return loginPass;
  }}