package com.example.springbatchdb2db.config;

import com.example.springbatchdb2db.model.Student;
import com.example.springbatchdb2db.model.StudentDTO;
import org.springframework.batch.item.ItemProcessor;

import java.util.Locale;

public class NaveenItemPocessor  implements ItemProcessor<Student,StudentDTO>
{

    @Override
    public StudentDTO process(Student item) throws Exception {

        StudentDTO dto = new StudentDTO();
        dto.setSid(item.getSid());
        dto.setSname(item.getSname().toUpperCase(Locale.ROOT));
        dto.setMarks(item.getMarks());
        dto.setSage(item.getSage());
        return dto;
    }
}
