package model;

public class Curiosidade {
    private int id;
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private int idUsuario;
    private String nomeUsuario; // Adicionei para facilitar a exibição

    public Curiosidade(String titulo, String descricao, String imagemUrl, int idUsuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.idUsuario = idUsuario;
    }
    
    // Construtor adicional para a listagem
    public Curiosidade(int id, String titulo, String descricao, String imagemUrl, int idUsuario, String nomeUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}