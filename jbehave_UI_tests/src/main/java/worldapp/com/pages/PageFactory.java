package worldapp.com.pages;

import worldapp.com.common.SimpleProvider;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 2/19/14
 * Time: 4:28 PM
 */
public class PageFactory {//extends AccountBasicTestsExecutor {

    protected final SimpleProvider webDriverProvider;

    public PageFactory(SimpleProvider webDriverProvider){
        this.webDriverProvider = webDriverProvider;
    }

    public SimpleProvider getWebDriverProvider() {
        return webDriverProvider;
    }

   /* public LoginPage newLoginPage(){
        return new LoginPage(webDriverProvider.get());
    }

    public OfflineLoginPage newLoginOfflinePage(){
        return new OfflineLoginPage(webDriverProvider.get());
    }

    public OfflineFormsPage newOfflineFormPage(){
        return new OfflineFormsPage(webDriverProvider.get());
    }

    public SurveysPage newSurveysPage(){
        return new SurveysPage(webDriverProvider.get());
    }

    public PortalPage newPortalPage(){
        return new PortalPage(webDriverProvider.get());
    }

    public VotingPage newVotingPage(){
        return new VotingPage(webDriverProvider.get());
    }

    public ActionsPage newActionsPage(){
        return new ActionsPage(webDriverProvider.get());
    }

    public QuestionListPage newQuestionListPage(){
        return new QuestionListPage(webDriverProvider.get());
    }

    public PluginsPage newPluginPage(){
        return new PluginsPage(webDriverProvider.get());
    }*/
}
