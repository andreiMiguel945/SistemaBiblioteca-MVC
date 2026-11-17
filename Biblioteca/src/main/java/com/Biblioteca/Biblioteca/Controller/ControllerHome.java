package com.Biblioteca.Biblioteca.Controller;

import com.Biblioteca.Biblioteca.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class ControllerHome {

    private final Autor autor = new Autor(1, "Anne Frank", "Alemanha");
    private final Autor autor2 = new Autor(2, "Antoine de Saint-Exupéry", "França");
    private final Autor autor3 = new Autor(3, "J.K Rowling", "Reino Unido");
    private final List<Autor> autores = Arrays.asList(autor, autor2, autor3);

    private final Categoria categoria = new Categoria(1, "Ficção", "Obras literárias imaginárias e narrativas");
    private final Categoria categoria2 = new Categoria(2, "Fatos Reais", "Biografia, historia, curiosidades");
    private final Categoria categoria3 = new Categoria(3,"Fantasia","Ficção centrado na magia, no sobrenatura e em elementos que escapam à realidade");
    private final List<Categoria> categorias = Arrays.asList(categoria, categoria2,categoria3);

    private final Livro livro = new Livro(1, "O pequeno Principe", "Fulano de tal", "1943", categoria);
    private final Livro livro2 = new Livro(2, "O Diário de Anne Frank", "Anne Frank", "1947", categoria2);
    private final Livro livro3 = new Livro(3,"Harry Potter", "J.K Rowling","1980",categoria3);
    private final List<Livro> livros = Arrays.asList(livro, livro2, livro3);

    private final Usuario usuario = new Usuario(1, "Andrei", "andrei@gmail.com", "433939393939");
    private final Usuario usuario2 = new Usuario(2, "Pedri", "pedri@gmail.com", "4367676767676");
    private final List<Usuario> usuarios = Arrays.asList(usuario, usuario2);

    private final Emprestimo emprestimo = new Emprestimo(1, livro, usuario, "22/01/2026", "22/02/2026");
    private final Emprestimo emprestimo2 = new Emprestimo(2, livro2, usuario2, "06/07/2026", "06/08/2026");
    private final List<Emprestimo> emprestimos = Arrays.asList(emprestimo, emprestimo2);

    //Aplicação do Get
    @GetMapping("")
    public String livros(Model model) {
        model.addAttribute("livros", livros);
        return "livros";
    }
    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categorias);
        return "categoria";
    }
    @GetMapping("/autores")
    public String autores(Model model) {
        model.addAttribute("autores", autores);
        return "autores";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping("/emprestimos")
    public String emprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimo";
    }

    //Aplicação do Post

    @PostMapping
    public String cadastrar(@ModelAttribute Livro livro){
        livros.add(livro);
        return "redirect:/livros";
    }

}