package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer numeroDownloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    // 🔹 Construtor padrão (JPA exige)
    public Livro() {}

    // 🔹 Construtor que recebe dados da API
    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.getTitulo();
        this.idioma = dadosLivro.getIdiomas().get(0);
        this.numeroDownloads = dadosLivro.getDownloads();

        DadosAutor dadosAutor = dadosLivro.getAutores().get(0);
        this.autor = new Autor(dadosAutor);
    }

    // 🔹 Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // 🔹 toString para o menu
    @Override
    public String toString() {
        return """
                -------------------------
                Título: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                -------------------------
                """.formatted(
                titulo,
                autor.getNome(),
                idioma.toUpperCase(),
                numeroDownloads
        );
    }
}
