package android.android;

import com.android.chimpchat.core.IChimpBackend;
import com.android.chimpchat.core.IChimpDevice;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 21.04.14
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class MyChimpChat {
    private final IChimpBackend mBackend;
    private static String sAdbLocation;
    private static boolean sNoInitAdb;

    private MyChimpChat(IChimpBackend backend) {
        this.mBackend = backend;
    }

    public static MyChimpChat getInstance(Map<String, String> options) {
        sAdbLocation = options.get("adbLocation");
        sNoInitAdb = Boolean.valueOf(options.get("noInitAdb"));

        IChimpBackend backend = createBackendByName(options.get("backend"));
        if (backend == null) {
            return null;
        }
        MyChimpChat chimpchat = new MyChimpChat(backend);
        return chimpchat;
    }

    /** Generates a new instance of ChimpChat using default settings
     * @return a new instance of ChimpChat or null if errors occur during creation
     */
    public static MyChimpChat getInstance() {
        Map<String, String> options = new TreeMap<String, String>();
        options.put("backend", "adb");
        return MyChimpChat.getInstance(options);
    }


    /**
     * Creates a specific backend by name.
     *
     * @param backendName the name of the backend to create
     * @return the new backend, or null if none were found.
     */

    private static IChimpBackend createBackendByName(String backendName) {
        if ("adb".equals(backendName)) {
            return new MyAdbBackend(sAdbLocation);
        } else {
            return null;
        }
    }

    /**
     * Retrieves an instance of the device from the backend
     * @param timeoutMs length of time to wait before timing out
     * @param deviceId the id of the device you want to connect to
     * @return an instance of the device
     */
    public IChimpDevice waitForConnection(long timeoutMs, String deviceId){
        return mBackend.waitForConnection(timeoutMs, deviceId);
    }

    /**
     * Retrieves an instance of the device from the backend.
     * Defaults to the longest possible wait time and any available device.
     * @return an instance of the device
     */
    public IChimpDevice waitForConnection(){
        return mBackend.waitForConnection(Integer.MAX_VALUE, ".*");
    }

    /**
     * Shutdown and clean up chimpchat.
     */
    public void shutdown(){
        mBackend.shutdown();
    }
}