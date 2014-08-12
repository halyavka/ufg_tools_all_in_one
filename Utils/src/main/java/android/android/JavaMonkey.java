package android.android;

import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.PhysicalButton;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 17.04.14
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class JavaMonkey {
    private static final String ADB = System.getenv("ANDROID_HOME") + "\\platform-tools\\adb";
    private static final long TIMEOUT = 5000;
    private TreeMap<String, String> options = new TreeMap<String, String>();
    private MyChimpChat mChimpchat;
    private IChimpDevice mDevice;

    public JavaMonkey() {
    }

    public void init() {
        options.put("backend", "adb");
        options.put("adbLocation", ADB);
        mChimpchat = MyChimpChat.getInstance(options);
        mDevice = mChimpchat.waitForConnection(TIMEOUT, ".*");
        if ( mDevice == null ) {
            throw new RuntimeException("Couldn't connect.");
        }
        mDevice.wake();
    }

    public void shutdown() {
        mDevice = null;
       // mDevice.getManager().close();
        mChimpchat.shutdown();
    }

    public void click_HOME_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.HOME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_BACK_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_MENU_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.MENU);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_DPAD_CENTER_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.DPAD_CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_DPAD_DOWN_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.DPAD_DOWN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goTo(int x, int y){
        try {
            mDevice.getManager().touchMove(x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_DPAD_UP_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.DPAD_UP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_DPAD_LEFT_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.DPAD_LEFT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_DPAD_RIGHT_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.DPAD_RIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_SAVE_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.valueOf("SAVE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_ENTER_Button(){
        try {
            mDevice.getManager().press(PhysicalButton.ENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickOnPoint(int x, int y){
        try {
            mDevice.getManager().touch(x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSdkVersion(){
        return mDevice.getProperty("build.version.sdk");
    }

    public String getDisplayHeight(){
        return mDevice.getProperty("display.height");
    }

    public String getDisplayWidth(){
        return mDevice.getProperty("display.width");
    }

    public void listProperties() {
        /*am.current.action:
        am.current.categories:
        am.current.comp.class:
        am.current.comp.package:
        am.current.data:
        am.current.package:
        build.board: smdk4x12
        build.brand: 1380110310000
        build.cpu_abi: armeabi-v7a
        build.device: p4notewifi
        build.display: JZO54K.N8013UEUCMI3
        build.fingerprint: samsung/p4notewifiue/p4notewifi:4.1.2/JZO54K/N8013UEUCMI3:user/release-keys
        build.host: SEP-63
        build.id: JZO54K
        build.manufacturer: samsung
        build.model: GT-N8013
        build.product: p4notewifiue
        build.tags: release-keys
        build.type: user
        build.user: se.infra
        build.version.codename: REL
        build.version.incremental: N8013UEUCMI3
        build.version.release: 4.1.2
        build.version.sdk: 16
        clock.millis: 1397731954545
        clock.realtime: 4397002466
        clock.uptime: 587663739
        display.density: 1.0
        display.height: 752
        display.width: 1280
        monkey.version: 2*/

        if ( mDevice == null ) {
            throw new IllegalStateException("init() must be called first.");
        }
        String sdvVersion = mDevice.getProperty("build.version.sdk");
        for (String prop: mDevice.getPropertyList()) {
            System.out.println("  #########  " + prop + ": " + mDevice.getProperty(prop));
        }
    }

    public void run() {
        init();
        listProperties();
        shutdown();
    }

}