package org.softlang.company.features;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.softlang.company.protobuf.CompanyProtos.*;

import com.google.protobuf.InvalidProtocolBufferException;

import org.junit.Before;
import org.junit.Test;

public class CutTest {
	private static String sampleCompany = "inputs" + File.separator + "sampleCompany.txt";
	private Company c;

	@Before
	public void initCompany() throws InvalidProtocolBufferException {
		File sample = new File(sampleCompany);
		c = Serialization.deserializeCompany(sample);
	}

	@Test
	public void testCut() throws InvalidProtocolBufferException, FileNotFoundException {
		Company C = Cut.cut(c);
		new File("outputs").mkdir();
		File tmp = new File("outputs" + File.separator + "cutCompany.tmp");
		Serialization.serializeCompany(tmp, C);
		c = Serialization.deserializeCompany(tmp);
		Double total = Total.total(c);
		assertEquals(399747 / 2.0d, total, 0);
		tmp.delete();
	}
}
