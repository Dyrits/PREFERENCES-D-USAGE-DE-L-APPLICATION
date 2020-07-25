package eni.fr.javaee.preferences;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ServletPreferences")
public class ServletPreferences extends HttpServlet {
    Map<String, String> colors = new HashMap<>();
    private static String color = "black";

    @Override
    public void init() throws ServletException {
        super.init();
        colors.put("Rouge", "red");
        colors.put("Bleu", "blue");
        colors.put("Vert", "green");
        colors.put("Noir", "black");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        color = (String) request.getParameter("color");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Counter:
        Cookie[] cookies = request.getCookies();
        Cookie counter = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("counter")) {
                    counter = cookie;
                    counter.setValue(String.valueOf(Integer.parseInt(counter.getValue()) + 1));
                    break;
                }
            }
        }
        if (counter == null) { counter = new Cookie("counter", "1"); }
        counter.setMaxAge(86400);
        response.addCookie(counter);
        request.setAttribute("counter", counter.getValue());
        // Colors:
        if (color == null && session.getAttribute("color") != null) { color = (String) session.getAttribute("color"); }
        request.setAttribute("colors", colors);
        session.setAttribute("color", color);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
