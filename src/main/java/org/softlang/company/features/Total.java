package org.softlang.company.features;

import org.softlang.company.protobuf.CompanyProtos.*;

public class Total {
	public static double total(Company c) {
		double total = 0;
		for (Department d : c.getDepartmentList())
			total += total(d);
		return total;
	}

	public static double total(Department d) {
		double total = total(d.getManager());
		for (Department s : d.getDepartmentList())
			total += total(s);
		for (Employee e : d.getEmployeeList())
			total += total(e);
		return total;
	}

	public static double total(Employee e) {
		return e.getSalary();
	}
}