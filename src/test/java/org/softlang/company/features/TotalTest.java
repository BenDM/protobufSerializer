package org.softlang.company.features;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.softlang.company.protobuf.CompanyProtos.*;

import com.google.protobuf.InvalidProtocolBufferException;

public class TotalTest {
	private static String sampleCompany = "inputs" + File.separator + "sampleCompany.txt";
	private Company c;

	@Before
	public void initCompany() throws InvalidProtocolBufferException {
		File sample = new File(sampleCompany);
		c = Serialization.deserializeCompany(sample);
	}

	@Test
	public void testTotal() throws InvalidProtocolBufferException, FileNotFoundException {
		double total = Total.total(c);
		assertEquals(399747, total, 0);
	}
}
