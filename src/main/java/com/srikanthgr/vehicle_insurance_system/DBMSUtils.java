package com.srikanthgr.vehicle_insurance_system;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


public class DBMSUtils {
    
    public static int GetPreviousID(JdbcTemplate jdbcTemplate, String sqlQuery){

        int previousID = 0;

        previousID = jdbcTemplate.query(sqlQuery, new ResultSetExtractor<Integer>() {
             
            public Integer extractData(ResultSet rs) throws SQLException {

                Integer ID = 0;

                while(rs.next()) {

                    ID = rs.getInt(1);
                }

                return ID;
            }
        });

        return previousID;
    }
}
