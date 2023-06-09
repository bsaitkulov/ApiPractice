package com.digital.dbUtils;


import lombok.*;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dept_ManagersBean {

    int emp_no;
    int dept_no;
    String from_date;
    String to_date;

    public static List<Dept_ManagersBean> getAll() throws SQLException {
        String query = "SELECT * FROM departments";
        try (ResultSet resultSet = DBConnection.query(query)) {
            return new BeanProcessor().toBeanList(resultSet, Dept_ManagersBean.class);
        }
    }
    public static Dept_ManagersBean getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM departments WHERE " + column + " = ?;";
        ResultSet rs = DBConnection.query(query, value);
        if (!rs.next()) return null;
        return new BeanProcessor().toBean(rs, Dept_ManagersBean.class);
    }
    public Dept_ManagersBean(ResultSet rs) throws SQLException {
        new BeanProcessor().populateBean(rs, this);
    }

}
