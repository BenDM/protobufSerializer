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
		InputStream in = null;
		try {
			in = new FileInputStream(input);
			int len = in.available();
			byte[] tempbytes = new byte[len];
			int byteread = 0;

			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.print("bytes: ");
				System.out.write(tempbytes, 0, byteread);
				System.out.println();
			}

			try {
				c = Company.parseFrom(tempbytes);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return c;
	}

	public static void serializeCompany(File output, Company c)
			throws InvalidProtocolBufferException, FileNotFoundException {
		byte[] value = c.toByteArray();

		FileOutputStream out = null;
		if (!output.exists()) {
			try {
				output.createNewFile();
				out = new FileOutputStream(output);
				out.write(value);
				out.close();

			} catch (IOException e2) {
				e2.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
						System.out.println("write done");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
