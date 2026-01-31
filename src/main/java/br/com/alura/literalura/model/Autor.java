package br.com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.getNome();
        this.anoNascimento = dadosAutor.getAnoNascimento();
        this.anoFalecimento = dadosAutor.getAnoFalecimento();
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        return """
               Autor: %s
               Ano de nascimento: %d
               Ano de falecimento: %d
               Livros: %s
               """.formatted(
                nome,
                anoNascimento,
                anoFalecimento,
                livros != null ? livros.stream().map(Livro::getTitulo).toList() : "Nenhum"
        );
    }
}
