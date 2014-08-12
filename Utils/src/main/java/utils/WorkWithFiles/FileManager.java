/*********************************************************************
 *                                                                   *
 * Copyright (c) 2002-2007 by Survey Software Services, Inc.         *
 * All rights reserved.                                              *
 *                                                                   *
 * This computer program is protected by copyright law and           *
 * international treaties. Unauthorized reproduction or distribution *
 * of this program, or any portion of it, may result in severe civil *
 * and criminal penalties, and will be prosecuted to the maximum     *
 * extent possible under the law.                                    *
 *                                                                   *
 *********************************************************************/

package utils.WorkWithFiles;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import utils.AppUtil;
import utils.SysUtils;

import javax.mail.Folder;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public abstract class FileManager {
    protected static Logger LOG = Logger.getLogger(FileManager.class);

    public static void saveStringToFile(String dirName, String fileName, String source) {
        File f = new File(dirName);
        f.mkdirs();
        FileOutputStream fout = null;
        try {
            try {
                fout = new FileOutputStream(f.getPath() + "\\" + fileName, true);
                fout.write(source.getBytes());
            } finally {
                if (fout != null) {
                    fout.close();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void saveStringToFile(String fileName, String source) {
        File f = new File(fileName);
        f.mkdirs();
        FileOutputStream fout = null;
        try {
            try {
                fout = new FileOutputStream(fileName, true);
                fout.write(source.getBytes());
            } finally {
                if (fout != null) {
                    fout.close();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

 /*   public static void cleanFolderByLocation(String location){
        Folder folder = new Folder(location);
        for(File fileInFolder : folder.listFiles()){
            fileInFolder.delete();
        }
    }*/

    public static List<String> loadFileAsListString(String fileName) {
        List<String> result = new ArrayList<String>();
        String realFileName = AppUtil.getRelativePathToImageWithClass(FileManager.class.getResource("/"), fileName);
        File file = new File(realFileName);
        try {
            String content = FileUtils.readFileToString(file);
            for (String str : content.split("\r\n")) {
                result.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<String> parseRemoteFileToList(URL fileLocation){
        List<String> arrayList = new ArrayList<String>();
        try {
           if(fileLocation!=null){
            LOG.info("Parsing file '" + fileLocation.getHost()+fileLocation.getPath() + "' with retrieved data...");
            URLConnection urlConnection=fileLocation.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            InputStream stream=urlConnection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
            String str;
            while ((str = reader.readLine()) != null) {
                arrayList.add(str);
                LOG.info(str);
            }
            reader.close();  }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return arrayList;
    }
    public static List parseFileToList(File fileLocation) {
        List<String> arrayList = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileLocation));//"\\\\worldapp-autote\\www\\data.txt"
            String str;
            LOG.info("Parsing file '" + fileLocation.getName() + "' with retrieved data...");
            if (in == null) {
                Assert.fail("There is no data in file!!!");
            }
            while ((str = in.readLine()) != null) {
                // LOG.info(str);
                arrayList.add(str);
            }
            in.close();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void overWriteFileUseBufferedWriter(String path, String string) {
        //File file = new File(path);
        try {
            //FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            bufferedWriter.write(string);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void overWriteFileUseFileWriter(String path, String strs) {
        try {
            FileWriter fstream1 = new FileWriter(path);// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(strs); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }
    public static void writeDataToFile(String[] dataCharacters,String pathToFile) {
        try {
            File file = new File(pathToFile);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < dataCharacters.length; i++) {
                String dataCharacter = dataCharacters[i];
                bw.write(dataCharacter);
                bw.newLine();
            }
            bw.close();
            LOG.info("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPDFDataAndDeleteFile(String aDataFilePath) throws Exception {
        String path = aDataFilePath;

        File f = new File(path);
        String everything;
        PDDocument pddDocument=null;
        try {
            LOG.info("Start parsing PDF file ...");
            pddDocument = PDDocument.load(new File(aDataFilePath));
            PDFTextStripper textStripper = new PDFTextStripper();
            LOG.info("Get pdf text!");
            everything = textStripper.getText(pddDocument);
            LOG.info("Getting pdf text is successful!");
            boolean success = f.delete();
            if (!success) {
                LOG.info("Deletion failed.");
            } else {
                LOG.info(path + " deleted successfully.");
            }
            return everything;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            if(pddDocument!=null){ LOG.info("Close pdf text!");pddDocument.close();}
        }
    }

    public static String getPDFData(String aDataFilePath) throws Exception {
        //String path = aDataFilePath;

        //File f = new File(path);
        String everything = null;
        try {
            PDDocument pddDocument = PDDocument.load(new File(aDataFilePath));
            PDFTextStripper textStripper = new PDFTextStripper();
            everything = textStripper.getText(pddDocument);
            pddDocument.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return everything;
    }

    public static void checkTextFromUploadedFile(String fileName) throws Exception {
        String textPath = AppUtil.getRelativePathToImageWithClass(FileManager.class.getResource("/"), "resources/export/tmp/" + fileName);
       // String textPathStandard = AppUtil.getRelativePathToImageWithClass(FileManager.class.getResource("/"), "resources/export/survey/Survey.pdf");
        File file = new File(textPath);
        if (!file.exists()) {
            int i = 0;
            do {
                SysUtils.sleep(2000);
                if (i++ > 10) throw new FileNotFoundException();
                LOG.info("PDF file doesn't exist. Wait...");
            }
            while (!file.exists());
        }
        LOG.info("PDF file exists.");
        //String textDataStandard = getPDFData(textPathStandard);
        String textData = getPDFDataAndDeleteFile(textPath);
        //Assert.assertTrue(textData.equals(textDataStandard));
        Assert.assertTrue(textData.contains("title\r\n" +
                "description\r\n" +
                "Single line\r\n"+
                "Answer0\r\n"));
    }

    public static void waitForFileInSecByFilePath(String filePath, int seconds){
        File file = new File(filePath);
        if (!file.exists()) {
            int i = 0;
            do {
                SysUtils.sleep(1000);
                if (i++ > seconds) try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            while (!file.exists());
        }
    }

    public static void waitForFileInSecByFile(File file, int seconds){
        if (!file.exists()) {
            int i = 0;
            do {
                SysUtils.sleep(1000);
                if (i++ > seconds) try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
            while (!file.exists());
        }
    }

}
