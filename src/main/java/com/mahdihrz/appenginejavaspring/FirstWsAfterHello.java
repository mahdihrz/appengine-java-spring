package com.mahdihrz.appenginejavaspring;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FirstWsAfterHello", value = "/first")
public class FirstWsAfterHello extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/plain");

        if (request.getParameter("param1") != null){
            response.getWriter().println("Hello App Engine - param1: " + request.getParameter("param1"));
        } else {
            response.getWriter().println("Hello App Engine - param1 Empty");
        }

    }
}
