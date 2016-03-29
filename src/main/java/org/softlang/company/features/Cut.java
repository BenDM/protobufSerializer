package org.softlang.company.features;

import org.softlang.company.protobuf.CompanyProtos.*;

public class Cut {
	static Company.Builder C = Company.newBuilder();

	public static Company cut(Company c) {
		C = c.toBuilder();
		for (Department.Builder d : C.getDepartmentBuilderList())
			cut(d);
		return C.build();
	}

	public static void cut(Department.Builder d) {
		cut(d.getManagerBuilder());
		for (Department.Builder s : d.getDepartmentBuilderList())
			cut(s);
		for (Employee.Builder e : d.getEmployeeBuilderList())
			cut(e);
	}

	public static void cut(Employee.Builder e) {
		e.setSalary(e.getSalary() / 2);
	}
}
