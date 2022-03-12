package com.mahdihrz.appenginejavaspring;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class FirstWsAfterHelloTest {
    private static final String FAKE_URL = "fake.fk/first";
    // Set up a helper so that the ApiProxy returns a valid environment for local testing.
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

    @Mock
    private HttpServletRequest mockRequest;
    @Mock private HttpServletResponse mockResponse;
    private StringWriter responseWriter;
    private FirstWsAfterHello servletUnderTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        helper.setUp();

        //  Set up some fake HTTP requests
        when(mockRequest.getRequestURI()).thenReturn(FAKE_URL);

        // Set up a fake HTTP response.
        responseWriter = new StringWriter();
        when(mockResponse.getWriter()).thenReturn(new PrintWriter(responseWriter));

        servletUnderTest = new FirstWsAfterHello();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void doGetWritesResponse() throws Exception {
        servletUnderTest.doGet(mockRequest, mockResponse);

        assertThat(responseWriter.toString())
                .contains("Hello App Engine - param1 Empty");
    }

    @Test
    public void doGetWritesResponseWhenSentParam1() throws Exception {
        when(mockRequest.getParameter("param1")).thenReturn("test1");
        servletUnderTest.doGet(mockRequest, mockResponse);

        assertThat(responseWriter.toString())
                .contains("Hello App Engine - param1: test1");
    }
}