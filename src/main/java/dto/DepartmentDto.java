package dto;

import java.util.List;

public class DepartmentDto {

	private Long deptno;
	
	private String dname;
	
	private String loc;
	
	private List<EmployeeDto> employeesList;

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

	public List<EmployeeDto> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<EmployeeDto> employeesList) {
		this.employeesList = employeesList;
	}
	
	
}
