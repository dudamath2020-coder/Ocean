/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view;

import controller.CuriosidadeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Curiosidade;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "ListarCuriosidades", urlPatterns = {"/ListarCuriosidades"})
public class ListarCuriosidades extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CuriosidadeDAO curiosidadeDAO;

    public void init() {
        curiosidadeDAO = new CuriosidadeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        List<Curiosidade> curiosidades = curiosidadeDAO.listarTodas();

        // Cabeçalho HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-BR\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Curiosidades sobre Recifes</title>");
        out.println("    <link rel=\"stylesheet\" href=\"css/style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<header class='site-header'>");
        out.println("<div class='container'>");
        out.println("    <h1 class='logo'>🌊 Ocean Explore</h1>");
        out.println("    <nav>");
        out.println("        <ul class='main-nav'>");
        out.println("            <li><a href='index.html'>Início</a></li>");
        out.println("            <li><a href='videos.html'>Vídeos</a></li>");
        out.println("            <li><a href='fauna.html'>Fauna</a></li>");
        out.println("            <li><a href='flora.html'>Flora</a></li>");
        out.println("            <li><a href='preservacao.html'>Preservação</a></li>");
        out.println("            <li><a href='ListarCuriosidades'>Curiosidades</a></li>");
        out.println("            <li><a href='perfil.html'>Perfil do Usuário</a></li>");
        out.println("        </ul>");
        out.println("    </nav>");
        out.println("</div>");
        out.println("</header>");

        // Lista de curiosidades
        out.println("<main>");
        out.println("<h2>Curiosidades sobre Recifes</h2>");
        out.println("<ul>");

        if (curiosidades != null && !curiosidades.isEmpty()) {
            for (Curiosidade c : curiosidades) {
                out.println("<li>");
                out.println("    <h3>" + c.getTitulo() + "</h3>");
                out.println("    <p>" + c.getDescricao() + "</p>");
                if (c.getImagemUrl() != null && !c.getImagemUrl().isEmpty()) {
                    out.println("    <img src=\"" + c.getImagemUrl() + "\" alt=\"" + c.getTitulo() + "\">");
                }
                out.println("    <p>Adicionado por: <strong>" + c.getNomeUsuario() + "</strong></p>");
                out.println("</li>");
            }
        } else {
            out.println("<p>Nenhuma curiosidade encontrada.</p>");
        }

        out.println("</ul>");

        // Botão para adicionar nova curiosidade
        out.println("<div style='margin-top: 20px;'>");
        out.println("    <form action='AdicionarCuriosidade' method='get'>");
        out.println("        <button type='submit'>Adicionar Nova Curiosidade</button>");
        out.println("    </form>");
        out.println("</div>");

        out.println("</main>");

        // Rodapé
        out.println("<footer>");
        out.println("    <p>&copy; 2025 Ocean Explore</p>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }
}
