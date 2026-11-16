package com.Biblioteca.Biblioteca.Controller;

import com.Biblioteca.Biblioteca.Model.Emprestimo;
import com.Biblioteca.Biblioteca.Model.Livro;
import com.Biblioteca.Biblioteca.Model.Usuario;
import org.springframework.ui.Model;
import com.Biblioteca.Biblioteca.Model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class ControllerHome {
    //Aplicçãp Autor
    private final Autor autor = new Autor(1,"Andrei","Brasil");
    private final Autor autor2 = new Autor(2, "George Orwell", "Britânica");
    private final Autor autor3 = new Autor(3, "Clarice Lispector", "Brasileira");
    private List<Autor> autores = Arrays.asList(autor, autor2, autor3);

    //Aplcação Livro
    private final Livro livro = new Livro(1,"O pequeno Principe", "Fulano de tal","Só Deus sabe");
    private final Livro livro2 = new Livro(1,"O Diário de Anne Frank", "Anne Frank","1950 ou menos?");
    private final List<Livro> livros = Arrays.asList(livro, livro2);

    //Aplicação Usuário
    private final Usuario usuario = new Usuario(1,"Andrei","andrei@gmail.com","433939393939");
    private final Usuario usuario2 = new Usuario(2,"Pedri","pedri@gmail.com","4367676767676");
    private List<Usuario> usuarios = Arrays.asList(usuario,usuario2);
    //Aplicação Emprestimo
    private final Emprestimo emprestimo = new Emprestimo(1,livro,usuario,"22/01/2026","22/02/2026");
    private final Emprestimo emprestimo2 = new Emprestimo(2,livro2,usuario2,"06/07/2026","06/08/2026");
    private List<Emprestimo> emprestimos = Arrays.asList(emprestimo,emprestimo2);

    @GetMapping("")
    public String livros(Model model) {
        model.addAttribute("livros",livros);
        return "livros/index";
    }
    @GetMapping("/autores")
    public String autores(Model model) {
        model.addAttribute("autores", autores);
        return "autores/index";
    }

    @GetMapping("/categorias")
    public String categorias() {
        return "categorias/index";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "usuarios/index";
    }

    @GetMapping("/emprestimos")
    public String emprestimos(Model model) {
        model.addAttribute("emprestimos",emprestimos);
        return "emprestimos/index";
    }
}