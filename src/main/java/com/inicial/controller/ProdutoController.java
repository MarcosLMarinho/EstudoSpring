package com.inicial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.inicial.exceptions.RecursoNaoEncontrado;
import com.inicial.model.Produto;
import com.inicial.service.ProdutoService;

import java.util.List;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public List<Produto> listaProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        //try {
            Produto produto = produtoService.buscarProdutoPorId(id);
            return ResponseEntity.ok(produto);
        //} catch (RecursoNaoEncontrado e) {
            //return ResponseEntity.notFound().build();
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        //}
    }

    @PostMapping()
    public Produto criarProduto (@RequestBody Produto produto) {
        Produto entity = produtoService.salvarProduto(produto);
        return entity;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

}
