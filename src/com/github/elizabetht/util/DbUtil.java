package com.github.elizabetht.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {
	private static Connection dbConnection = null;

	public static Connection getConnection() {
		if (dbConnection != null) {
			return dbConnection;
		} else {
			try {
				InputStream inputStream = DbUtil.class.getClassLoader()
						.getResourceAsStream("db.properties");
				Properties properties = new Properties();
				if (properties != null) {
					properties.load(inputStream);

					/*String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties
							.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");*/

					Class.forName("com.mysql.jdbc.Driver").newInstance();
					//dbConnection = DriverManager.getConnection("jdbc:mysql://172.25.25.16:3306/studentEnrollment", "pk", "pk");
					//dbConnection = DriverManager.getConnection(connectionUrl,userName, password);
					
					System.setProperty("javax.net.ssl.keyStore","/home/ubuntu/keystore");
					System.setProperty("javax.net.ssl.keyStorePassword","password");
					System.setProperty("javax.net.ssl.trustStore","/home/ubuntu/truststore");
					System.setProperty("javax.net.ssl.trustStorePassword","password");
					
					String url = "jdbc:mysql://172.25.25.29:3306/studentEnrollment"+"?"+
									"verifyServerCertificate=true"+
									"&useSSL=true"+
									"&requireSSL=true";
			
					String user = "anilpatil";
					String password = "password";

					dbConnection = DriverManager.getConnection(url, user, password);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dbConnection;
		}
	}
}
