
package com.senai.jonatas.comprebem.repository;

import com.senai.jonatas.comprebem.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository
        extends JpaRepository<Produto, UUID> {

    Page<Produto> findByNomeContainingIgnoreCase(
            String nome,
            Pageable pageable
    );
}
