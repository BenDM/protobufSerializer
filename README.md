# protobufSerializer

##Headline

Protocol Buffer in Language:Java 

##Characteristics

Protocol Buffer is a serialization format with an interface description language developed by Google. . Is an open source, free software which is used for the program communication. There available protocol compilers are for C++, Java and Python.  Google developed this software to be smaller and faster than XLM because the data is transmitted in the binary format. Protocol Buffers was creating between 2001 to 2008 and was finally presented in 2008.


##Illustration

The data model is implemented as txt  files (sampleCompany.txt) that conform to a schema (CompanyProtos.proto). For example departments:
```
message Department {
	required string name = 1;
	required Employee manager = 2;
	repeated Department department = 3;
	repeated Employee employee = 4;
}

```
Actual [[Language:Java]] classes will be generated using [[Protocol Buffer Compiler]].

[[Feature:Open serialization]] is implemented:

```
	public static Company deserializeCompany(File input) throws InvalidProtocolBufferException {
		Company c = null;
		if (!input.exists())
			return null;
		InputStream inputFile = null;
		try {
			inputFile = new FileInputStream(input);
			int leng = inputFile.available();
			byte[] bytes = new byte[leng];
			inputFile.read(bytes);
			try {
				c = Company.parseFrom(bytes);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputFile != null) {
				try {
					inputFile.close();
				} catch (IOException e) {
				}
			}
		}
		return c;
	}
```
```
		public static void serializeCompany(File output, Company c)
			throws InvalidProtocolBufferException, FileNotFoundException {
		byte[] bytes = c.toByteArray();
		FileOutputStream outputFile = null;
		if (!output.exists()) {
			try {
				output.createNewFile();
				outputFile = new FileOutputStream(output);
				outputFile.write(bytes);
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputFile != null) {
					try {
						outputFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

```
[[Feature:Total]] and [[Feature:Cut]] are implemented as static methods:

```
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

```
```
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
	
```
object initializer is implemented in main method:
```
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
				
```

Test cases are implemented for all Namespace:Features.

##Relationships

For define message formats in a .proto file see [[https://developers.google.com/protocol-buffers/docs/proto#enum]]
For use the protocol buffer compiler see [[https://developers.google.com/protocol-buffers/docs/reference/java-generated#invocation]]
For use the Java protocol buffer API see [[https://developers.google.com/protocol-buffers/docs/reference/java/]]

##Architecture

The contribution follows a standardized structure:
* inputs contains input files for tests and a schema file.
* tools contains Protocol Buffer Compiler
* src/main/java contains the following packages:
** org.softlang.company.features for implementations of [[Functional requirements]].
** org.softlang.company.protobuf is generated java class.
* src/test/java contains the following packages:
** org.softlang.company.tests for [[Technology:JUnit]] test cases for [[Namespace:Feature]]s.

##Usage

This contribution uses Maven for building. Eclipse is supported.
