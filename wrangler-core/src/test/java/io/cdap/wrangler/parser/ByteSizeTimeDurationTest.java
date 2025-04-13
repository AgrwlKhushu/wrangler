/*
 * Copyright © 2025 The Author
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package io.cdap.wrangler.parser;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the ByteSize and TimeDuration parsers.
 * These tests validate parsing of units like KB, MB, GB, seconds,
 * milliseconds, and minutes.
 */
public class ByteSizeTimeDurationTest {

    /**
     * Test parsing of valid byte sizes with various units (KB, MB, GB).
     */
    @Test
    public void testByteSizeParsing() {
        Assert.assertEquals(10240, new ByteSize("10KB").getBytes());            // 10 KB = 10240 bytes
        Assert.assertEquals(1048576, new ByteSize("1MB").getBytes());           // 1 MB = 1024 * 1024
        Assert.assertEquals(1073741824, new ByteSize("1GB").getBytes());        // 1 GB = 1024 * 1024 * 1024
    }

    /**
     * Test parsing of valid time durations in seconds, milliseconds, and
     * minutes.
     */
    @Test
    public void testTimeDurationParsing() {
        Assert.assertEquals(5000, new TimeDuration("5s").getMilliseconds());         // 5 seconds = 5000 ms
        Assert.assertEquals(1500,
                new TimeDuration("1500ms").getMilliseconds());     // 1500 ms
        // = 1500 ms
        Assert.assertEquals(120000,
                new TimeDuration("2min").getMilliseconds());     // 2 minutes
        // = 120000 ms
    }

    /**
     * Test parsing of an invalid byte size string. Should throw
     * NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void testInvalidByteSize() {
        new ByteSize("XYZ"); // Invalid unit or malformed string
    }

    /**
     * Test parsing of an invalid time duration string. Should throw
     * NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void testInvalidTimeDuration() {
        new TimeDuration("abc"); // Invalid time format
    }
}
