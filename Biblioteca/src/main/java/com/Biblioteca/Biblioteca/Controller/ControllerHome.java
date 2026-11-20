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
    @GetMapping("/editarLivro/{id}")
    public String abrirEdicaoLivro(@PathVariable("id") int id, Model model) {
        for (Livro liv : livros) {
            if (liv.getId() == id) {
                model.addAttribute("livro", liv);
                break;
            }
        }
        model.addAttribute("categorias",categorias);
        return "paginas_edicao/editarLivro"; // Aponta direto para templates/editarUsuario.html
    }

    @PutMapping("/editarLivro/{id}" )
    public String atualizarLivro(@PathVariable("id") int id, @ModelAttribute Livro livroAtualizado,  @RequestParam("categoriaId") int categoriaId) {
        for (Livro liv : livros) {
            if (liv.getId() == id) {
                liv.setTitulo(livroAtualizado.getTitulo());
                liv.setAutor(livroAtualizado.getAutor());
                liv.setData_de_lancamento(livroAtualizado.getData_de_lancamento());
                liv.setCategoria(livroAtualizado.getCategoria());
                break;
            }
        }
        return "redirect:/livros";
    }

    //----------DELETE-LIVRO--------------//

    @DeleteMapping("/excluirLivro/{id}")
    @ResponseBody
    public String excluirLivro(@PathVariable("id") int id) {
        livros.removeIf(liv -> liv.getId() == id);
        return "ok";
    }



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

    //---------------PUT-AUTOR-----------------//
    @GetMapping("/editarAutor/{id}")
    public String abrirEdicaoAutor(@PathVariable("id") int id, Model model) {
        for (Autor aut : autores) {
            if (aut.getId() == id) {
                model.addAttribute("autor", aut);
                break;
            }
        }
        return "paginas_edicao/editarAutor";
    }

    @PutMapping("/editarAutor/{id}")
    public String atualizarAutor(@PathVariable("id") int id,
                                 @ModelAttribute Autor autorAtualizado) {
        for (Autor aut : autores) {
            if (aut.getId() == id) {
                aut.setNome(autorAtualizado.getNome());
                aut.setNacionalidade(autorAtualizado.getNacionalidade());
                break;
            }
        }
        return "redirect:/livros/autores";
    }

    //---------------DELETE-AUTOR--------------//
    @DeleteMapping("/excluirAutor/{id}")
    @ResponseBody
    public String excluirAutor(@PathVariable("id") int id) {
        autores.removeIf(autor -> autor.getId() == id);
        return "ok";
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

    //------------PUT-CATEGORIA--------//
    @GetMapping("/editarCategoria/{id}")
    public String abrirEdicaoCategoria(@PathVariable("id") int id, Model model) {
        for (Categoria cat : categorias) {
            if (cat.getId() == id) {
                model.addAttribute("categoria", cat);
                break;
            }
        }
        return "paginas_edicao/editarCategoria";
    }

    @PutMapping("/editarCategoria/{id}")
    public String atualizarCategoria(@PathVariable("id") int id, @ModelAttribute Categoria categoriaAtualizada) {
        for (Categoria cat : categorias) {
            if (cat.getId() == id) {
                cat.setNome(categoriaAtualizada.getNome());
                cat.setDescricao(categoriaAtualizada.getDescricao());
                break;
            }
        }
        return "redirect:/livros/categorias";
    }

    //---------------DELETE-CATEGORIA--------------//
    @DeleteMapping("/excluirCategoria/{id}")
    @ResponseBody
    public String excluirCategoria(@PathVariable("id") int id) {
        categorias.removeIf(categoria -> categoria.getId() == id);
        return "ok";
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

    //-----------------PUT-USUARIO--------------//
    @GetMapping("/editarUsuario/{id}")
    public String abrirEdicaoUsuario(@PathVariable("id") int id, Model model) {
        for (Usuario user : usuarios) {
            if (user.getId() == id) {
                model.addAttribute("usuario", user);
                break;
            }
        }
        return "paginas_edicao/editarUsuario"; // Aponta direto para templates/editarUsuario.html
    }

    @PutMapping("/editarUsuario/{id}")
    public String atualizarUsuario(@PathVariable("id") int id, @ModelAttribute Usuario usuarioAtualizado) {
        for (Usuario user : usuarios) {
            if (user.getId() == id) {
                user.setNome(usuarioAtualizado.getNome());
                user.setEmail(usuarioAtualizado.getEmail());
                user.setTelefone(usuarioAtualizado.getTelefone());
                break;
            }
        }
        return "redirect:/livros/usuarios";
    }


    //---------------DELETE-USUARIO--------------//
    @DeleteMapping("/excluirUsuario/{id}")
    @ResponseBody
    public String excluirUsuario(@PathVariable("id") int id) {
        usuarios.removeIf(usuario -> usuario.getId() == id);
        return "ok";
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
    public String cadastrarEmprestimo(@ModelAttribute Emprestimo emprestimo, @RequestParam("livroId") int livroId,
                                      @RequestParam("usuarioId") int usuarioId) {
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
    //---------------PUT-EMPRESTIMO-----------------//
    @GetMapping("/editarEmprestimo/{id}")
    public String abrirEdicaoEmprestimo(@PathVariable("id") int id, Model model) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == id) {
                model.addAttribute("emprestimo", emp);
                break;
            }
        }
        model.addAttribute("livros", livros);
        model.addAttribute("usuarios", usuarios);
        return "paginas_edicao/editarEmprestimo";
    }

    @PutMapping("/editarEmprestimo/{id}")
    public String atualizarEmprestimo(@PathVariable("id") int id, @ModelAttribute Emprestimo emprestimoAtualizado,
                                      @RequestParam("livroId") int livroId, @RequestParam("usuarioId") int usuarioId) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == id) {
                emp.setData_de_emprestimo(emprestimoAtualizado.getData_de_emprestimo());
                emp.setData_de_devolucao(emprestimoAtualizado.getData_de_devolucao());

                for (Livro liv : livros) {
                    if (liv.getId() == livroId) {
                        emp.setLivroEmprestado(liv);
                        break;
                    }
                }
                for (Usuario user : usuarios) {
                    if (user.getId() == usuarioId) {
                        emp.setUsuario(user);
                        break;
                    }
                }
                break;
            }
        }
        return "redirect:/livros/emprestimos";
    }

    //---------------DELETE-EMPRESTIMO--------------//
    @DeleteMapping("/excluirEmprestimo/{id}")
    @ResponseBody
    public String excluirEmprestimo(@PathVariable("id") int id) {
        emprestimos.removeIf(emprestimo -> emprestimo.getId() == id);
        return "ok";
    }








}