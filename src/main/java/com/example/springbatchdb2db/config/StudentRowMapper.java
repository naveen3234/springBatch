package com.example.springbatchdb2db.config;

import com.example.springbatchdb2db.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student>
{

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student student = new Student();
        student.setSid(rs.getString("sid"));
        student.setSage(rs.getString("sage"));
        student.setMarks(rs.getString("marks"));
        student.setSname(rs.getString("sname"));
        return student;
    }
}
