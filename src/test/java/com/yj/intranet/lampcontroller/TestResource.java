package com.yj.intranet.lampcontroller;

import com.yj.core.util.AssertUtils;
import com.yj.core.util.IOUtils;

import java.io.InputStream;

/**
 * @author mort
 */
public class TestResource {
    public static byte[] bytes(String testResourcePath) {
        InputStream stream = TestResource.class.getResourceAsStream(testResourcePath);
        AssertUtils.assertNotNull(stream, "resource does not exist, resource=" + testResourcePath);
        return IOUtils.bytes(stream);
    }

    public static String text(String testResourcePath) {
        InputStream stream = TestResource.class.getResourceAsStream(testResourcePath);
        AssertUtils.assertNotNull(stream, "resource does not exist, resource=" + testResourcePath);
        return IOUtils.text(stream);
    }
}
