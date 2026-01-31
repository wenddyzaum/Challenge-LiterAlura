package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // 🔹 Usado na opção 4 do menu
    @Query("""
        SELECT a FROM Autor a
        WHERE a.anoNascimento <= :ano
        AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
    """)
    List<Autor> autoresVivosNoAno(@Param("ano") int ano);
}
