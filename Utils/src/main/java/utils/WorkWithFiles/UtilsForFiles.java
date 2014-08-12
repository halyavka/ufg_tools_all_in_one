package utils.WorkWithFiles;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.AppUtil;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UtilsForFiles {
    private static Logger LOG = Logger.getLogger(UtilsForFiles.class);
    /**
     * Unpack an archive from a URL
     *
     * @param url
     * @param targetDir
     * @return the file to the url
     * @throws java.io.IOException
     */
    public static Map downloadAndUnpackArchive(URL url, File targetDir) throws IOException {
        LOG.info("Download location is: "+targetDir);
        LOG.info("Download file is: "+url);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        File zip = null;  //"arc"+System.currentTimeMillis()
        try {
            URLConnection urlConnection=url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("content-type", "binary/data");
            url.openConnection().getInputStream();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream(),4096);//url.openStream()
            // make sure we get the actual file
            zip = File.createTempFile("arc" + System.currentTimeMillis(), ".zip", targetDir);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));
            copyInputStream(in, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   return unpackArchive(zip, targetDir);

    }   public static Map downloadAndUnpackArchive(InputStream inputStream, File targetDir) throws IOException {
        LOG.info("Download location is: "+targetDir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        File zip = null;  //"arc"+System.currentTimeMillis()
        try {
            if(inputStream.available()==0){throw new IOException("Download content is null");
            }
            InputStream in = new BufferedInputStream(inputStream);
            // make sure we get the actual file
            zip = File.createTempFile("arc" + System.currentTimeMillis(), ".zip", targetDir);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));

            copyInputStream(in, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
            return unpackArchive(zip, targetDir);


    }
    /**
     * Unpack a zip file
     *
     * @param theFile
     * @param targetDir
     * @return the file
     * @throws java.io.IOException
     */
    public static Map unpackArchive(File theFile, File targetDir) throws IOException {
        if (!theFile.exists()) {
            throw new IOException(theFile.getAbsolutePath() + " does not exist");
        }
        if (!buildDirectory(targetDir)) {
            throw new IOException("Could not create directory: " + targetDir);
        }
        ZipFile zipFile = new ZipFile(theFile);
        Map<String,File> map=new HashMap<String,File>();
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            File file = new File(targetDir, File.separator + entry.getName());
            map.put(entry.getName(),file);
            if (!buildDirectory(file.getParentFile())) {
                throw new IOException("Could not create directory: " + file.getParentFile());
            }
            if (!entry.isDirectory()) {
                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
            } else {
                if (!buildDirectory(file)) {
                    throw new IOException("Could not create directory: " + file);
                }
            }
        }
        zipFile.close();
        return map;
    }

    public static void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len >= 0) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.close();
    }

    public static boolean buildDirectory(File file) {
        return file.exists() || file.mkdirs();
    }
    //filenames are .pdf, .csv, xls, etc
    private static File saveStreamIntoFile(InputStream inputStream, String fileName) throws Exception {
        String path = AppUtil.getRelativePathToImageWithClass(UtilsForFiles.class.getResource("/"), "resources/export/tmp/" + System.currentTimeMillis());
        path += fileName;
        File file = new File(path);
        LOG.info("PATH = " + path);
        if (!file.createNewFile()) throw new Exception();
        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        do {
            length = inputStream.read(buffer);
            if (length > 0) out.write(buffer, 0, length);
        } while (length != -1);
        out.close();
        return file;
    }  public void download_zip_file(URL url,String save_to) {
        try {
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "binary/data");
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(save_to + "tmp.zip");

            byte[] b = new byte[1024];
            int count;

            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static WebClient loginThroughWebClient(String HOST,String login,String pass) throws Exception{

        WebClient client = new WebClient();
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setUseInsecureSSL(true);
        HtmlPage page = client.getPage(HOST + "/Member/UserAccount/UserLogin.do");//ConfigBridge.getApplicationUrl() + "/Member/UserAccount/UserLogin.do"
        LOG.info("Login to: " + HOST + "/Member/UserAccount/UserLogin.do");
        ((HtmlElement)page.getElementById("login")).type(login);
        ((HtmlElement) page.getElementById("password")).type(pass);//
        page = ((HtmlElement)page.getElementById("loginButton")).click();
        if (page.getElementById("login") != null) {
            ((HtmlElement)page.getElementById("login")).type(login);
            ((HtmlElement)page.getElementById("password")).type(pass);
            ((HtmlElement)page.getElementById("loginButton")).click();
        }
        if (page.getElementById("designObjectSelect")!=null) LOG.info("Success login!!!");
        else {
            LOG.info("############# Unsuccess login");
        }
        return client;
    }


}