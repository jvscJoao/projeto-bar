package br.com.projeto.bar.projeto_bar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.bar.projeto_bar.entity.Produto;
import br.com.projeto.bar.projeto_bar.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> searchAll() {
        List<Produto> list = produtoService.searchAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> searchById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(produtoService.searchById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto obj) {
        Produto newObj = produtoService.insert(obj);
        URI locator = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newObj.getId())
        .toUri();
        return ResponseEntity.created(locator).body(newObj);
    }

    @PutMapping
    public ResponseEntity<Produto> update(@RequestBody Produto obj) {
        Produto objUpdated = produtoService.update(obj);
        return ResponseEntity.ok().body(objUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
