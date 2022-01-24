package com.example.springbatchdb2db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO2 {

    @Id
    private String sid;
    private String sname;
    private String sage;
    private String marks;
    
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSage() {
		return sage;
	}
	public void setSage(String sage) {
		this.sage = sage;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public StudentDTO2(String sid, String sname, String sage, String marks) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sage = sage;
		this.marks = marks;
	}
	public StudentDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
