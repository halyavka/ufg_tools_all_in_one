package worldapp.com.steps;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 2/10/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestVariables {

    private String currentWindowHandle;
    private List<Long> questionsId = new ArrayList<>();
    private List<String> questionsText = new ArrayList<>();
    private List<List<Long>> answersId = new ArrayList<>();
    private List<Long> questionsIdForm = new ArrayList<>();
    private List<String> questionsTextForm = new ArrayList<>();
    private List<List<Long>> answersIdForm = new ArrayList<>();
    private String userLogin;
    private String userPassword;
    private long SID;
    private String launchPageUrl;
    private long dataModelID;
    private String surveyName;
    private static long portalId;

    public void setCurrentWindowHandle(WebDriver webDriver){
        this.currentWindowHandle = webDriver.getWindowHandle();
    }

    public String getCurrentWindowHandle(){
        return currentWindowHandle;
    }

    public String getLaunchPageUrl() {
        return launchPageUrl;
    }

    public void setLaunchPageUrl(String launchPageUrl) {
        this.launchPageUrl = launchPageUrl;
    }

    public long getDataModelID() {
        return dataModelID;
    }

    public void setDataModelID(long dataModelID) {
        this.dataModelID = dataModelID;
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public long getPortalId() {
        return portalId;
    }

    public void setPortalId(long portalId) {
        TestVariables.portalId = portalId;
    }

    public void setSID(long SID){
        this.SID = SID;
    }

    public long getSID() {
        return SID;
    }

    public void setSurveyName(String surveyName){
        this.surveyName = surveyName;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public List<Long> getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(List<Long> questionsId) {
        this.questionsId = questionsId;
    }

    public List<String> getQuestionsText() {
        return questionsText;
    }

    public void setQuestionsText(List<String> questionsText) {
        this.questionsText = questionsText;
    }

    public List<List<Long>> getAnswersId() {
        return answersId;
    }

    public void setAnswersId(List<List<Long>> answersId) {
        this.answersId = answersId;
    }

    public List<Long> getQuestionsIdForm() {
        return questionsIdForm;
    }

    public void setQuestionsIdForm(List<Long> questionsIdForm) {
        this.questionsIdForm = questionsIdForm;
    }

    public List<String> getQuestionsTextForm() {
        return questionsTextForm;
    }

    public void setQuestionsTextForm(List<String> questionsTextForm) {
        this.questionsTextForm = questionsTextForm;
    }

    public List<List<Long>> getAnswersIdForm() {
        return answersIdForm;
    }

    public void setAnswersIdForm(List<List<Long>> answersIdForm) {
        this.answersIdForm = answersIdForm;
    }

}
