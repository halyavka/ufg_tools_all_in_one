package worldapp.com.myConfig;

import org.jbehave.core.failures.UUIDExceptionWrapper;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 29.11.13
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class NotPerformedStepFound extends UUIDExceptionWrapper {

    public NotPerformedStepFound(String step) {
        super(step);
    }

}