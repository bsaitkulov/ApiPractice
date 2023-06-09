package testDB;

import com.digital.dbUtils.DBConnection;
import com.digital.dbUtils.TitleBean;
import com.digital.dbUtils.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestTitle {

    public static void main(String[] args) throws SQLException {
        DBConnection.open("employees");
        ResultSet rs = DBConnection.query("SELECT * FROM titles");
        List<String> listOfTitles = new ArrayList<>();
        while (rs.next()) {
            TitleBean user = new TitleBean(rs);
            listOfTitles.add(user.getTitle());
            System.out.println(user.getTitle());
        }
        System.out.println(listOfTitles);
    }
}
