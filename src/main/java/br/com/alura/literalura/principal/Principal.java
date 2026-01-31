package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.service.ConversorDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDados conversor = new ConversorDados();

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    private final String ENDERECO_API = "https://gutendex.com/books/?search=";

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    
                    ======== LiterAlura ========
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    ===========================
                    """);

            opcao = leitura.nextInt();
            leitura.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Encerrando aplicação...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ===== OPÇÃO 1 =====
    private void buscarLivroPorTitulo() {
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine();

        String json = consumoAPI.obterDados(ENDERECO_API + titulo.replace(" ", "%20"));
        DadosResposta dados = conversor.obterDados(json, DadosResposta.class);

        Optional<DadosLivro> livroBuscado = dados.getLivros().stream().findFirst();

        if (livroBuscado.isPresent()) {
            Livro livro = new Livro(livroBuscado.get());

            livroRepository.save(livro);

            System.out.println("\nLivro salvo com sucesso!");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado na API.");
        }
    }

    // ===== OPÇÃO 2 =====
    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    // ===== OPÇÃO 3 =====
    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    // ===== OPÇÃO 4 =====
    private void listarAutoresPorAno() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepository.autoresVivosNoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado nesse ano.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    // ===== OPÇÃO 5 =====
    private void listarLivrosPorIdioma() {
        System.out.println("""
                Digite o idioma:
                PT - Português
                EN - Inglês
                ES - Espanhol
                FR - Francês
                """);

        String idioma = leitura.nextLine().toLowerCase();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}
