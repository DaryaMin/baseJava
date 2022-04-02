package com.uirise.webapp.web;

import com.uirise.webapp.Config;
import com.uirise.webapp.model.Resume;
import com.uirise.webapp.sql.SqlStorage;
import com.uirise.webapp.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" + "<table border=\"1\">\n" +
                        "  <tr>\n" +
                        "    <th>UUID</th>\n" +
                        "    <th>FullName</th>\n" +
                        "  </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "<td>" + resume.getUuid() + "</td>\n" +
                            "<td>" + resume.getFullName() + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" + "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
