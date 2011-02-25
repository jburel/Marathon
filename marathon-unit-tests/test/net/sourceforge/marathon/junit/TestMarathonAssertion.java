/*******************************************************************************
 *  
 *  Copyright (C) 2010 Jalian Systems Private Ltd.
 *  Copyright (C) 2010 Contributors to Marathon OSS Project
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 * 
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 *  Project website: http://www.marathontesting.com
 *  Help: Marathon help forum @ http://groups.google.com/group/marathon-testing
 * 
 *******************************************************************************/
package net.sourceforge.marathon.junit;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import net.sourceforge.marathon.api.Failure;
import net.sourceforge.marathon.api.SourceLine;

import org.junit.Test;

public class TestMarathonAssertion {
    @Test
    public void testStackTrace() throws IOException {
        SourceLine[] traceback = new SourceLine[1];
        traceback[0] = new SourceLine("testFile.py", "testFunction", 10);
        Failure f = new Failure("stackTrace", traceback);
        MarathonAssertion assertion = new MarathonAssertion(new Failure[] { f }, "stackTrace");
        StringWriter strWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(strWriter);
        assertion.printStackTrace(writer);
        StringBuffer buffer = strWriter.getBuffer();
        BufferedReader reader = new BufferedReader(new StringReader(buffer.toString()));
        assertEquals("Test case name", "net.sourceforge.marathon.junit.MarathonAssertion: stackTrace", reader.readLine());
        assertEquals("Header", "\tFailure: stackTrace", reader.readLine());
        assertEquals("Filename string", "\tat testFunction(testFile.py:10)", reader.readLine());
    }
}