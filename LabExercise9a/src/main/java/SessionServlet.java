// 9a. Servlet program for session tracking with 1-minute expiry

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.Date;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get session
        HttpSession session = request.getSession(true);

        // Set session expiry to 60 seconds
        session.setMaxInactiveInterval(60);

        // Visit count logic
        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }

        session.setAttribute("visitCount", visitCount);

        // Output
        out.println("<html><body>");
        out.println("<h2>Session Tracking Information</h2>");

        out.println("<p><b>Session ID:</b> " + session.getId() + "</p>");
        out.println("<p><b>Creation Time:</b> " + new Date(session.getCreationTime()) + "</p>");
        out.println("<p><b>Last Access Time:</b> " + new Date(session.getLastAccessedTime()) + "</p>");
        out.println("<p><b>Visit Count:</b> " + visitCount + "</p>");

        out.println("<p><i>Session expires in 1 minute.</i></p>");

        out.println("<br><a href='SessionServlet'>Refresh</a>");

        out.println("</body></html>");
    }
}