package controller;

import model.Curiosidade;
import model.Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuriosidadeDAO {

    // Salvar curiosidade
    public void salvarCuriosidade(Curiosidade curiosidade) throws SQLException {
        String sql = "INSERT INTO curiosidades (titulo, descricao, imagem_url, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Banco.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curiosidade.getTitulo());
            ps.setString(2, curiosidade.getDescricao());

            if (curiosidade.getImagemUrl() == null || curiosidade.getImagemUrl().isEmpty()) {
                ps.setNull(3, java.sql.Types.VARCHAR);
            } else {
                ps.setString(3, curiosidade.getImagemUrl());
            }

            ps.setInt(4, curiosidade.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar curiosidade: " + e.getMessage());
            throw e; // Lança a exceção para o servlet tratar
        }
    }

    // Listar todas as curiosidades
    public List<Curiosidade> listarTodas() {
        List<Curiosidade> curiosidades = new ArrayList<>();
        String sql = "SELECT c.*, u.nome AS nome_usuario FROM curiosidades c " +
                     "JOIN usuarios u ON c.id_usuario = u.id ORDER BY c.id DESC";
        try (Connection conn = Banco.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curiosidade curiosidade = new Curiosidade(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("imagem_url"),
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario")
                );
                curiosidades.add(curiosidade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar curiosidades: " + e.getMessage());
        }
        return curiosidades;
    }
}
