package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import model.Usuario;

@WebServlet("/VerificarLogin")
public class VerificarLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogado") : null;

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (usuario != null) {
            out.print("{\"logado\": true}");
        } else {
            out.print("{\"logado\": false}");
        }
    }
}
