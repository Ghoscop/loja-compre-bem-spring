
package com.senai.jonatas.comprebem.component;

import com.senai.jonatas.comprebem.entity.Produto;
import com.senai.jonatas.comprebem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class Data implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }

        String[] nomes = {
                "Cadeira", "Mesa", "Sofa", "Armario",
                "Estante", "Escrivaninha"
        };

        for (int i = 1; i <= 60; i++) {
            Produto produto = new Produto();

            String nome = nomes[(i - 1) % nomes.length];

            produto.setNome(nome + " " + i);
            produto.setDescricao(
                    "Descricao do produto " + i
            );
            produto.setPreco(
                    BigDecimal.valueOf(i * 10L)
            );
            produto.setCategoria("Moveis");
            produto.setImagem("");
            produto.setQuantidade(10);

            produtoRepository.save(produto);
        }
    }
}
