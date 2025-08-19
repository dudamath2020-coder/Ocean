package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import model.Usuario;

@WebServlet(name = "Perfil", urlPatterns = {"/Perfil"})
public class Perfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtém a sessão existente, sem criar nova
        HttpSession session = request.getSession(false);

        // Verifica se existe usuário logado
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogado") : null;

        // Se não estiver logado, manda para o login
        if (usuario == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Configura o tipo de resposta
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Monta o HTML do perfil
        out.println("<!DOCTYPE html>");
        out.println("<html lang='pt-BR'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Perfil do Usuário - Ocean Explore</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        // Cabeçalho
        out.println("<header class='site-header'>");
        out.println("  <div class='container'>");
        out.println("    <h1 class='logo'>🌊 Ocean Explore</h1>");
        out.println("    <nav>");
        out.println("      <ul class='main-nav'>");
        out.println("        <li><a href='index.html'>Início</a></li>");
        out.println("        <li><a href='videos.html'>Vídeos</a></li>");
        out.println("        <li><a href='fauna.html'>Fauna</a></li>");
        out.println("        <li><a href='flora.html'>Flora</a></li>");
        out.println("        <li><a href='preservacao.html'>Preservação</a></li>");
        out.println("        <li><a href='ListarCuriosidades'>Curiosidades</a></li>");
        out.println("        <li><a href='Perfil'>Perfil do Usuário</a></li>");
        out.println("      </ul>");
        out.println("    </nav>");
        out.println("  </div>");
        out.println("</header>");

        // Conteúdo
        out.println("<main class='container'>");
        out.println("  <h2>Perfil do Usuário</h2>");
        out.println("  <div class='perfil-card'>");
        out.println("    <img src='https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png' alt='Foto de Perfil'>");
        out.println("    <div>");
        out.println("      <h3>" + usuario.getNome() + "</h3>");
        out.println("      <p>Email: " + usuario.getEmail() + "</p>");
        out.println("      <button onclick=\"alert('Função de editar perfil em desenvolvimento!')\">Editar Perfil</button>");
        out.println("      <form action='Logout' method='post' style='display:inline;'>");
        out.println("        <button type='submit'>Sair</button>");
        out.println("      </form>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</main>");

        // Rodapé
        out.println("<footer class='site-footer'>");
        out.println("  <div class='container'>");
        out.println("    <p>&copy; 2025 Ocean Explore — Todos os direitos reservados.</p>");
        out.println("  </div>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
