package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        livros = new ArrayList<>();
    }

    public Biblioteca(){

    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null || livro.getTitulo() == null || livro.getTitulo().isBlank() || livro.getAutor() == null || livro.getAutor().isBlank() || livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException("Os argumentos passados estão inválidos");
        } else {
            livros.add(livro);
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty() || titulo.isBlank()) {
            throw new ArgumentoInvalidoException("Os argumentos passados estão inválidos");
        } else {
            Boolean livroRemovido = false;
            for (int i = 0; i < livros.size(); i++) {
                Livro livroAtual = livros.get(i);
                if (livroAtual.getTitulo().equalsIgnoreCase(titulo)) {
                    livros.remove(livroAtual);
                    livroRemovido = true;
                }
            }
            if (!livroRemovido) {
                throw new LivroNaoEncontradoException("O livro não foi encontrado");
            }
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty() || titulo.isBlank()) {
            throw new ArgumentoInvalidoException("Os argumentos passados estão inválidos");
        } else {
            for (int i = 0; i < livros.size(); i++) {
                Livro livroAtual = livros.get(i);
                if (livroAtual.getTitulo().equalsIgnoreCase(titulo)) {
                    return livroAtual;

                }
            }
            throw new LivroNaoEncontradoException("O livro não foi encontrado");

        }
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosAteAno = new ArrayList<>();

        for (int i = 0; i < livros.size(); i++) {
            Livro livroAtual = livros.get(i);
            if (livroAtual.getDataPublicacao().getYear() <= ano ) {
                livrosAteAno.add(livroAtual);
            }
        }
        return livrosAteAno;

    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> livrosOrdenados = new ArrayList<>();
        for (Livro livroDaVez : livros) {
            livrosOrdenados.add(livroDaVez);
        }

        livrosOrdenados.sort(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes).reversed());

        List<Livro> livrosTopCinco = new ArrayList<>();
        for (int i = 0; i < livrosOrdenados.size() && i < 5 ; i++) {
            livrosTopCinco.add(livrosOrdenados.get(i));
        }
        return livrosTopCinco;
    }




}
