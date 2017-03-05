package org.jmf.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void testCustomCSVParser() throws IOException {
        String path = "src/test/resources/mock.csv";
        List<String[]> csv = FileUtils.customCSVParser(path);
        assertEquals("css:declaration-format", csv.get(1)[0]);
    }

 

}
