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
public class DepartmentsBean {

    int dept_no;
    String dept_name;

    public static List<DepartmentsBean> getAll() throws SQLException {
        String query = "SELECT * FROM departments";
        try (ResultSet resultSet = DBConnection.query(query)) {
            return new BeanProcessor().toBeanList(resultSet, DepartmentsBean.class);
        }
    }
    public static DepartmentsBean getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM departments WHERE " + column + " = ?;";
        ResultSet rs = DBConnection.query(query, value);
        if (!rs.next()) return null;
        return new BeanProcessor().toBean(rs, DepartmentsBean.class);
    }
    public DepartmentsBean(ResultSet rs) throws SQLException {
        new BeanProcessor().populateBean(rs, this);
    }

}
