package com.example.springbatchdb2db.config;

import com.example.springbatchdb2db.model.Student;
import com.example.springbatchdb2db.model.StudentDTO;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper2 implements RowMapper<StudentDTO>
{

    @Override
    public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

    	StudentDTO student = new StudentDTO();
        student.setSid(rs.getString("sid"));
        student.setSage(rs.getString("sage"));
        student.setMarks(rs.getString("marks"));
        student.setSname(rs.getString("sname"));
        return student;
    }
}
