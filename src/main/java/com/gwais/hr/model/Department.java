package com.gwais.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dept")
public class Department {

	@Id
	@SequenceGenerator(name = "deptSeqGenerator", sequenceName = "dept_seq", allocationSize = 1)
	@GeneratedValue(generator = "deptSeqGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "deptno", updatable = false, nullable = false)
	private Long deptno;
	
	@Column(name = "dname")
	private String dname;

	@Column(name = "loc")
	private String loc;
	
	public Long getDeptno() {
		return deptno;
	}
	
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	
	public String getDname() {
		return dname;
	}
	
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
}
