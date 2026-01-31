package br.com.alura.literalura.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResposta {

    @JsonAlias("results")
    private List <DadosLivro> livros;

    public List< DadosLivro> getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        return "DadosResposta{" +
                "livros=" + livros +
                '}';
    }
}




