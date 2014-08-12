package android.android;

import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.chimpchat.core.IChimpBackend;
import com.android.chimpchat.core.IChimpDevice;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.beust.jcommander.internal.Lists;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 21.04.14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class MyAdbBackend implements IChimpBackend {
    private static Logger LOG = Logger.getLogger(MyAdbBackend.class.getCanonicalName());
    private static final int CONNECTION_ITERATION_TIMEOUT_MS = 200;
    private final List<IChimpDevice> devices = Lists.newArrayList();
    private final AndroidDebugBridge bridge;

   /* public MyAdbBackend() {
        // [try to] ensure ADB is running
        String adbLocation = findAdb();
        AndroidDebugBridge.init(false *//* debugger support *//*);
        bridge = AndroidDebugBridge.createBridge(adbLocation, true *//* forceNewBridge *//*);
    }*/

    public MyAdbBackend(String location) {
        if(AndroidDebugBridge.getBridge() == null){
            // This only use then you work with JavaMonkey, without Selendroid !!!
            System.err.println("Bridge is null!!!");
            AndroidDebugBridge.init(false);
            bridge = AndroidDebugBridge.createBridge(location, true /* force new bridge */);
        }
        else{
            // This only use then you work with JavaMonkey and with Selendroid !!!
            bridge = AndroidDebugBridge.getBridge();
        }
    }

 /*   private String findAdb() {
        File location = new File(MyAdbBackend.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String mrParentLocation = new File(location.getParent()).getParent();
        // in the new SDK, adb is in the platform-tools, but when run from the command line
        // in the Android source tree, then adb is next to monkeyrunner.
        if (mrParentLocation != null && mrParentLocation.length() != 0) {
            // check if there's a platform-tools folder
            File platformTools = new File(new File(mrParentLocation).getParent(), SdkConstants.FD_PLATFORM_TOOLS);
            if (platformTools.isDirectory()) {
                return platformTools.getAbsolutePath() + File.separator + SdkConstants.FN_ADB;
            }
            return mrParentLocation + File.separator + SdkConstants.FD_OUTPUT + File.separator + SdkConstants.FN_ADB;
        }
        return SdkConstants.FN_ADB;
    }*/

    /**
     * Checks the attached devices looking for one whose device id matches the specified regex.
     *
     * @param deviceIdRegex the regular expression to match against
     * @return the Device (if found), or null (if not found).
     */
    private IDevice findAttachedDevice(String deviceIdRegex) {
        Pattern pattern = Pattern.compile(deviceIdRegex);
        for (IDevice device : bridge.getDevices()) {
            String serialNumber = device.getSerialNumber();
            if (pattern.matcher(serialNumber).matches()) {
                return device;
            }
        }
        return null;
    }

    @Override
    public IChimpDevice waitForConnection() {
        return waitForConnection(Integer.MAX_VALUE, ".*");
    }

    @Override
    public IChimpDevice waitForConnection(long timeoutMs, String deviceIdRegex) {
        do {
            IDevice device = findAttachedDevice(deviceIdRegex);
            // Only return the device when it is online
            if (device != null && device.getState() == IDevice.DeviceState.ONLINE) {
                IChimpDevice chimpDevice = new AdbChimpDevice(device);
                devices.add(chimpDevice);
                return chimpDevice;
            }
            try {
                Thread.sleep(CONNECTION_ITERATION_TIMEOUT_MS);
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, "Error sleeping", e);
            }
            timeoutMs -= CONNECTION_ITERATION_TIMEOUT_MS;
        } while (timeoutMs > 0);
        return null;
    }

    @Override
    public void shutdown() {
        for (IChimpDevice device : devices) {
            device.dispose();
        }
        AndroidDebugBridge.terminate();
    }
}
