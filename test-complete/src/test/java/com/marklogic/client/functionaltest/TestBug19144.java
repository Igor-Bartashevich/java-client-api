/*
 * Copyright 2014-2016 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marklogic.client.functionaltest;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.ValuesDefinition;

import org.xml.sax.SAXException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.StringHandle;

import org.custommonkey.xmlunit.exceptions.XpathException;
import org.junit.*;
public class TestBug19144 extends BasicJavaClientREST {

	private static String dbName = "TestBug19144DB";
	private static String [] fNames = {"TestBug19144DB-1"};
	private static String restServerName = "REST-Java-Client-API-Server";
@BeforeClass
	public static void setUp() throws Exception 
	{
	  System.out.println("In setup");
	  setupJavaRESTServer(dbName, fNames[0], restServerName,8011);
	  setupAppServicesConstraint(dbName);
	}

@Test
	public void testBug19144WithJson() throws IOException, ParserConfigurationException, SAXException, XpathException, TransformerException
	{	
		System.out.println("Running testBug19144WithJson");
		
		String[] filenames = {"aggr1.xml", "aggr2.xml", "aggr3.xml", "aggr4.xml", "aggr5.xml"};
		String queryOptionName = "aggregatesOpt.xml";

		DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8011, "rest-admin", "x", Authentication.DIGEST);
		
		// write docs
		for(String filename : filenames)
		{
			writeDocumentUsingInputStreamHandle(client, filename, "/bug19144/", "XML");
		}
		
		setQueryOption(client, queryOptionName);
				
		QueryManager queryMgr = client.newQueryManager();
		
		// create query def
		ValuesDefinition queryDef = queryMgr.newValuesDefinition("popularity", "aggregatesOpt.xml");
		queryDef.setAggregate("correlation", "covariance");
		queryDef.setName("pop-rate-tups");
		
		// create handle
		StringHandle resultHandle = new StringHandle().withFormat(Format.JSON);
		queryMgr.tuples(queryDef, resultHandle);
		
		String result = resultHandle.get();
		
		System.out.println(result);
		
		assertEquals("{", result.substring(0, 1));
        		
		// release client
		client.release();		
	}

@Test
	public void testBug19144WithXml() throws IOException, ParserConfigurationException, SAXException, XpathException, TransformerException
	{	
		System.out.println("Running testBug19144WithXml");
		
		String[] filenames = {"aggr1.xml", "aggr2.xml", "aggr3.xml", "aggr4.xml", "aggr5.xml"};
		String queryOptionName = "aggregatesOpt.xml";

		DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8011, "rest-admin", "x", Authentication.DIGEST);
		
		// write docs
		for(String filename : filenames)
		{
			writeDocumentUsingInputStreamHandle(client, filename, "/bug19144/", "XML");
		}
		
		setQueryOption(client, queryOptionName);
				
		QueryManager queryMgr = client.newQueryManager();
		
		// create query def
		ValuesDefinition queryDef = queryMgr.newValuesDefinition("popularity", "aggregatesOpt.xml");
		queryDef.setAggregate("correlation", "covariance");
		queryDef.setName("pop-rate-tups");
		
		// create handle
		StringHandle resultHandle = new StringHandle().withFormat(Format.XML);
		queryMgr.tuples(queryDef, resultHandle);
		
		String result = resultHandle.get();
		
		System.out.println(result);
		
		assertEquals("<", result.substring(0, 1));
        		
		// release client
		client.release();		
	}
	@AfterClass
	public static void tearDown() throws Exception
	{
		System.out.println("In tear down");
		tearDownJavaRESTServer(dbName, fNames, restServerName);
		
	}
}
