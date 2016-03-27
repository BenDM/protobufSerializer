package org.softlang.company.features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.softlang.company.protobuf.CompanyProtos.*;

import com.googlecode.protobuf.format.JsonFormat;

public class Main {
	public static void main(String[] args) throws IOException {
		String sampleCompany = "inputs" + File.separator + "sampleCompany.json";
		BufferedWriter writer = new BufferedWriter(new FileWriter(sampleCompany));
		Company c = Company.newBuilder().setName("Acme Corporation")

				.addDepartment(Department.newBuilder().setName("Research")
						.setManager(
								Employee.newBuilder().setName("Craig").setAddress("Redmond").setSalary(123456).build())
						.addEmployee(
								Employee.newBuilder().setName("Erik").setAddress("Utrecht").setSalary(12345).build())
						.addEmployee(
								Employee.newBuilder().setName("Ralf").setAddress("Koblenz").setSalary(1234).build())
						.build())

				.addDepartment(Department.newBuilder().setName("Development")
						.setManager(
								Employee.newBuilder().setName("Ray").setAddress("Redmond").setSalary(234567).build())
						.addDepartment(Department.newBuilder().setName("Dev1")
								.setManager(Employee.newBuilder().setName("Klaus").setAddress("Boston").setSalary(23456)
										.build())
								.addDepartment(Department.newBuilder().setName("Dev1.1")
										.setManager(Employee.newBuilder().setName("Karl").setAddress("Riga")
												.setSalary(2345).build())
										.addEmployee(Employee.newBuilder().setName("Joe").setAddress("Wifi City")
												.setSalary(2344).build())
										.build())
								.build())
						.build())
				.build();

		writer.write(JsonFormat.printToString(c));
		writer.close();
	}
}
