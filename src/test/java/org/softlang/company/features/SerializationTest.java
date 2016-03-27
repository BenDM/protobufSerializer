package org.softlang.company.features;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.softlang.company.protobuf.CompanyProtos.*;

import com.google.protobuf.InvalidProtocolBufferException;

public class SerializationTest {

	private static String sampleCompany = "inputs" + File.separator + "sampleCompany.txt";
	private Company c;

	@Before
	public void initCompany() throws InvalidProtocolBufferException {
		File sample = new File(sampleCompany);
		c = Serialization.deserializeCompany(sample);
	}

	@Test
	public void testDeserialization() throws InvalidProtocolBufferException {
		File sample = new File(sampleCompany);
		Serialization.deserializeCompany(sample);
	}

	@Test
	public void testSerialization() throws InvalidProtocolBufferException, FileNotFoundException {
		new File("outputs").mkdir();
		File out = new File("outputs" + File.separator + "sampleCompany.tmp");
		Serialization.serializeCompany(out, c);
		c = Serialization.deserializeCompany(out);
	}
}
