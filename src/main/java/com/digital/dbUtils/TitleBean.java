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
public class TitleBean {

    int emp_no;
    String title;
    String from_date;
    String to_date;

    public static List<TitleBean> getAll() throws SQLException {
        String query = "SELECT * FROM titles";
        try (ResultSet resultSet = DBConnection.query(query)) {
            return new BeanProcessor().toBeanList(resultSet, TitleBean.class);
        }
    }
    public static TitleBean getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM titles WHERE " + column + " = ?;";
        ResultSet rs = DBConnection.query(query, value);
        if (!rs.next()) return null;
        return new BeanProcessor().toBean(rs, TitleBean.class);
    }
    public TitleBean(ResultSet rs) throws SQLException {
        new BeanProcessor().populateBean(rs, this);
    }
}
