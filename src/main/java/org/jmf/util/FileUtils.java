package org.jmf.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class. For doing stuff with files.
 * 
 * @author jose
 *
 */
public final class FileUtils {

    // Logger
    private static final Logger FILE_LOGGER = LoggerFactory.getLogger("file");

    private FileUtils() {
    }

    /**
     * Read CSV file.
     * 
     * @param file
     *            Path of CSV file
     * @return List of arrays of strings
     * @throws IOException
     *             Exception
     */
    public static List<String[]> customCSVParser(final String file) throws IOException {

        // Open the file
        final FileInputStream fstream = new FileInputStream(file);
        final BufferedReader breader = new BufferedReader(new InputStreamReader(fstream, "UTF-8"));
        final ArrayList<String[]> rows = new ArrayList<>();
       
        Matcher matcher;
        ArrayList<String> row;
        StringBuilder sbuilder;
        String currentValue;
        String strLine;
        
        // Loop lines
        while ((strLine = breader.readLine()) != null) {
            matcher =  Pattern.compile("\\s*(\"[^\"]*\"|[^,]*)\\s*").matcher(strLine);
            row = new ArrayList<>();
            sbuilder = new StringBuilder();
            // loop columns           
            while (matcher.find()) {
                currentValue = matcher.group(1);
                if ("".equals(currentValue)) {
                    row.add(sbuilder.toString());
                    sbuilder.setLength(0);
                } else {
                    sbuilder.append(currentValue);
                }
            }
            // add columns to array            
            String[] srows = row.toArray(new String[row.size()]);
            rows.add(srows);
        }
        // Close the input stream
        breader.close();

        return rows;
    }

    /**
     * Return value from properties files given a key.     
     * @param sConfigFileName Config file name
     * @return Value from property file
     */
    public static Map<String, String> getProperties(final String sConfigFileName) {

        Map<String, String> propMap = new ConcurrentHashMap<>();
	    final Properties properties = new Properties();
        
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(sConfigFileName)) {
            if (input != null) {
                // load a properties file from class path
                properties.load(input);
            } else {
				// read from file system
	            properties.load(new FileInputStream("./src/main/resources/" + sConfigFileName));
            }

	        for (final String name: properties.stringPropertyNames()) {
		        propMap.put(name, properties.getProperty(name));
	        }
        } catch (IOException e) {
            FILE_LOGGER.error("Error getting properties from file:", e);
        }
        return propMap;    
    }

}
