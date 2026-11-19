package com.Biblioteca.Biblioteca.Controller;

import com.Biblioteca.Biblioteca.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class ControllerHome {

    private final Autor autor = new Autor(1, "Anne Frank", "Alemanha");
    private final Autor autor2 = new Autor(2, "Antoine de Saint-Exupéry", "França");
    private final Autor autor3 = new Autor(3, "J.K Rowling", "Reino Unido");
    private final List<Autor> autores = new ArrayList<> (Arrays.asList(autor, autor2, autor3));

    private final Categoria categoria = new Categoria(1, "Ficção", "Obras literárias imaginárias e narrativas");
    private final Categoria categoria2 = new Categoria(2, "Fatos Reais", "Biografia, historia, curiosidades");
    private final Categoria categoria3 = new Categoria(3,"Fantasia","Ficção centrado na magia, no sobrenatura e em elementos que escapam à realidade");
    private final List<Categoria> categorias = new ArrayList<>(Arrays.asList(categoria, categoria2,categoria3));

    private final Livro livro = new Livro(1, "O pequeno Principe", "Fulano de tal", "1943", categoria);
    private final Livro livro2 = new Livro(2, "O Diário de Anne Frank", "Anne Frank", "1947", categoria2);
    private final Livro livro3 = new Livro(3,"Harry Potter", "J.K Rowling","1980",categoria3);
    private final List<Livro> livros = new ArrayList<>(Arrays.asList(livro, livro2));

    private final Usuario usuario = new Usuario(1, "Andrei", "andrei@gmail.com", "433939393939");
    private final Usuario usuario2 = new Usuario(2, "Pedri", "pedri@gmail.com", "4367676767676");
    private final List<Usuario> usuarios = new ArrayList<> (Arrays.asList(usuario, usuario2));

    private final Emprestimo emprestimo = new Emprestimo(1, livro, usuario, "22/01/2026", "22/02/2026");
    private final Emprestimo emprestimo2 = new Emprestimo(2, livro2, usuario2, "06/07/2026", "06/08/2026");
    private final List<Emprestimo> emprestimos = new ArrayList<>(Arrays.asList(emprestimo, emprestimo2));

    //Aplicação dos métodos do livro

    //---------GET-LIVRO------------------//
    @GetMapping("")
    public String livros(Model model) {
        model.addAttribute("livros", livros);
        return "livros";
    }

    //---------POST-LIVRO---------------//
    @GetMapping("/cadastrarLivro")
    public String abrirCadastroLivro(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("categorias", categorias);
        return "paginas_cadastro/cadastrarLivro";
    }

    @PostMapping("/cadastrarLivro")
    public String cadastrarLivro(@ModelAttribute Livro livro, @RequestParam("categoriaId") int categoriaId) {
        livro.setId(livros.size() + 1);
        for (Categoria cat : categorias) {
            if (cat.getId() == categoriaId) {
                livro.setCategoria(cat);
                break;
            }
        }
        livros.add(livro);
        return "redirect:/livros";
    }

    //----------PUT-LIVRO--------------//



    //Aplicações dos métodos de autor

    //-----------------GET-AUTOR--------------//
    @GetMapping("/autores")
    public String autores(Model model) {
        model.addAttribute("autores", autores);
        return "autores";
    }

    //---------------POST-AUTOR--------------//

    @GetMapping("/cadastrarAutor")
    public String cadastrarAutor(Model model){
        model.addAttribute("autor", new Autor());
        return "paginas_cadastro/cadastrarAutor";
    }

    @PostMapping("/cadastrarAutor")
    public String cadastrarAutor(@ModelAttribute Autor autor){
        autor.setId(autores.size() +1);
        autores.add(autor);
        return "redirect:/livros/autores";
    }

    //Aplicações dos métodos da categoria

    //---------GET-CATEGORIA------------------///
    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    //---------------POST-CATEGOTIA--------------//

    @GetMapping("/cadastrarCat")
    public String abrirCadastroCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "paginas_cadastro/cadastrarCat";
    }

    @PostMapping("/cadastrarCat")
    public String cadastrarCategoria(@ModelAttribute Categoria categoria) {
        categoria.setId(categorias.size() + 1);
        categorias.add(categoria);
        return "redirect:/livros/categorias";
    }


    //Aplicações dos métodos do usuário

    //-----------------GET-USUARIO--------------//
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    //-----------------POST-USUARIO--------------//

    @GetMapping("/cadastrarUsuario")
    public String abrirCadastroUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "paginas_cadastro/cadastrarUsuario";
    }

    @PostMapping("cadastrarUsuario")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setId(usuarios.size() + 1);
        usuarios.add(usuario);
        return "redirect:/livros/usuarios";
    }


    //Aplicações dos métodos do empréstimo

    //-----------------GET-EMPRESTIMO--------------//
    @GetMapping("/emprestimos")
    public String emprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimo";
    }

    //-----------------POST-EMPRESTIMO--------------//

    @GetMapping("/inserirEmprestimo")
    public String abrirCadastroEmprestimo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livros);
        model.addAttribute("usuarios", usuarios);
        return "paginas_cadastro/inserirEmprestimo";
    }

    @PostMapping("/inserirEmprestimo")
    public String cadastrarEmprestimo(@ModelAttribute Emprestimo emprestimo, @RequestParam("livroId") int livroId, @RequestParam("usuarioId") int usuarioId) {
        emprestimo.setId(emprestimos.size() + 1);
        for (Livro liv : livros) {
            if (liv.getId() == livroId) {
                emprestimo.setLivroEmprestado(liv);
                break;
            }
        }
        for (Usuario usu : usuarios) {
            if (usu.getId() == usuarioId) {
                emprestimo.setUsuario(usu);
                break;
            }
        }
        emprestimos.add(emprestimo);
        return "redirect:/livros/emprestimos";
    }









}