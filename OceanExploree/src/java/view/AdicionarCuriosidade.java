package view;

import controller.CuriosidadeDAO;
import model.Curiosidade;
import model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/AdicionarCuriosidade"})
public class AdicionarCuriosidade extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CuriosidadeDAO curiosidadeDAO;

    @Override
    public void init() {
        curiosidadeDAO = new CuriosidadeDAO();
    }

    // Exibe o formulário
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.html");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='pt-BR'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Adicionar Curiosidade - Ocean Explore</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header class='site-header'>");
        out.println("<div class='container'>");
        out.println("<h1 class='logo'>🌊 Ocean Explore</h1>");
        out.println("<nav>");
        out.println("<ul class='main-nav'>");
        out.println("<li><a href='index.html'>Início</a></li>");
        out.println("<li><a href='ListarCuriosidades'>Curiosidades</a></li>");
        out.println("<li><a href='Perfil'>Perfil</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</div>");
        out.println("</header>");

        out.println("<main class='container'>");
        out.println("<h2>Adicionar Nova Curiosidade</h2>");
        out.println("<form action='AdicionarCuriosidade' method='post'>");
        out.println("<label for='titulo'>Título:</label><br>");
        out.println("<input type='text' id='titulo' name='titulo' required><br><br>");
        out.println("<label for='descricao'>Descrição:</label><br>");
        out.println("<textarea id='descricao' name='descricao' required></textarea><br><br>");
        out.println("<label for='imagem'>URL da Imagem (opcional):</label><br>");
        out.println("<input type='text' id='imagem' name='imagem'><br><br>");
        out.println("<button type='submit'>Salvar</button>");
        out.println("</form>");
        out.println("</main>");

        out.println("<footer class='site-footer'>");
        out.println("<div class='container'>");
        out.println("<p>&copy; 2025 Ocean Explore — Todos os direitos reservados.</p>");
        out.println("</div>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }

    // Salva no banco
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.html");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String imagem = request.getParameter("imagem");

        Curiosidade novaCuriosidade = new Curiosidade(titulo, descricao, imagem, usuario.getId());

        try {
            curiosidadeDAO.salvarCuriosidade(novaCuriosidade);
            response.sendRedirect("ListarCuriosidades");
        } catch (SQLException e) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h2>Erro ao salvar curiosidade: " + e.getMessage() + "</h2>");
            out.println("<a href='AdicionarCuriosidade'>Voltar</a>");
        }
    }
}
