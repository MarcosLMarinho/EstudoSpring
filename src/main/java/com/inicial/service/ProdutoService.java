package com.inicial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inicial.exceptions.RecursoNaoEncontrado;
import com.inicial.model.Produto;
import com.inicial.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado com o ID: " + id));
    }

    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontrado("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
