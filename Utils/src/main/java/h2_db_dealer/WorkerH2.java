package h2_db_dealer;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 3/6/14
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkerH2 {

    public List<String> getSurveyNameBySid(String SID) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:autotests", "sa", "");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT NAME FROM SURVEY WHERE SID = '" + SID + "'");
            List<String> list = new ArrayList<String>();
            while (result.next()) {
                String name = result.getString("NAME");
                list.add(name);
            }
            st.close();
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getSIDByReadyAndName(int ready, String name) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:autotests", "sa", "");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT SID FROM SURVEY WHERE READY = " + ready);
            List<String> list = new ArrayList<String>();
            while (result.next()) {
                String name2 = result.getString("SID");
                list.add(name2);
            }
            st.close();
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFromDBBySIDReady(String SID, int ready) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:autotests", "sa", "");
            Statement st = conn.createStatement();
            st.execute("DELETE FROM SURVEY WHERE READY = " + ready + " AND SID = '" + SID + "'");
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFromDBBySID(String SID) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:autotests", "sa", "");
            Statement st = conn.createStatement();
            st.execute("DELETE FROM SURVEY WHERE SID = '" + SID + "'");
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Test
    public void test1(){
        writeTo("CREATE TABLE IF NOT EXISTS SURVEY_INFO(SID VARCHAR(255), NAME VARCHAR(255));");
        writeTo("INSERT INTO SURVEY VALUES('New_survey13', '11111', 1)");
        writeTo("UPDATE SURVEY SET NAME = 'New_survey124444' WHERE SID = '11111'");
        writeTo("UPDATE SURVEY SET READY = 0 WHERE SID = '11111'");
        deleteFromDBBySIDReady("11111", 0);
        deleteFromDBBySID("11111");
        List name = new ArrayList();
        name = getSurveyNameBySid("11111");
        String sid1 = getSIDByReadyAndName(0, "").get(0);
    }



    public void writeTo(String executeQuery) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:autotests", "sa", "");
            Statement st = conn.createStatement();
            st.execute(executeQuery);
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
