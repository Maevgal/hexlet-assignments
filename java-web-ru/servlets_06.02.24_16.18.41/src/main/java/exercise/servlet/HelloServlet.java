package io.hexlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getParameterValues("name") == null) {
            res.getWriter().write("Hello, Guest!");
        } else {
            String name = String.join("", req.getParameterValues("name"));

            res.getWriter().println("Hello, " + name + "!");
            //req.getRequestDispatcher("/hello/hello?name="+name).forward(req, res);

        }
    }
    // END
}
