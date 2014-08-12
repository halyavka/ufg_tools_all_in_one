package pageObjPattern; /**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 2/11/13
 * Time: 2:06 PM
 */


import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestsAnalyser extends TestListenerAdapter {
    protected static Logger LOG = Logger.getLogger(TestsAnalyser.class);
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getMethod().getRetryAnalyzer() != null) {
            Reporter.setCurrentTestResult(result);
            if(result.getMethod().getRetryAnalyzer().retry(result)) {
                Reporter.log("Next attempt #"+(((Analyzer)result.getMethod().getRetryAnalyzer()).getCount())+" to execute "+result.getName()+". \r");
               LOG.info("Setting test run attempt status to SUCCESS_PERCENTAGE_FAILURE");
               result.setStatus(ITestResult.SUCCESS_PERCENTAGE_FAILURE);
            } else {
               LOG.info("Retry limit exceeded for " + result.getName());
            }

            Reporter.setCurrentTestResult(null);
        }

    }

    @Override
    public void onTestSuccess(ITestResult result){
        super.onTestSuccess(result);
        if (result.getMethod().getRetryAnalyzer()!=null&&result.getMethod().getRetryAnalyzer() instanceof Analyzer){
            if (((Analyzer)result.getMethod().getRetryAnalyzer()).getCount()>0){
                LOG.info("Retry attempt # "+((Analyzer)result.getMethod().getRetryAnalyzer()).getCount()+" for " + result.getName()+" is successful");
            }
        }
    }

  /*  @Override
    public void onTestSkipped(ITestResult result) {
        getSkippedTests().add(result);
        Arrays.asList(getAllTestMethods()).add(result.getMethod());
// will be called after test will be skipped
    }*/

   /* @Override
    public void onTestSuccess(ITestResult result) {
        List<ITestResult> results=Collections.synchronizedList(new ArrayList(getFailedTests()));
        Iterator<ITestResult> res=results.iterator();
        while (res.hasNext()){
            ITestResult value=res.next();
            if(value.getName().equals(result.getName())){
                res.remove();
                results.remove(value);
            }
        }
        setFailedTests(results);
        Reporter.setCurrentTestResult(result);
        Reporter.setCurrentTestResult(null);       
        getPassedTests().add(result);
        *//*List<ITestNGMethod> allTestsMethods=Collections.synchronizedList(Arrays.asList(getAllTestMethods()));
        allTestsMethods.add(result.getMethod());
        setAllTestMethods(allTestsMethods);*//*
// will be called after test will pass
    }*/

}
