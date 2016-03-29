package org.softlang.company.features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.softlang.company.protobuf.CompanyProtos.*;

import com.google.protobuf.*;

public class Serialization {
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
}
