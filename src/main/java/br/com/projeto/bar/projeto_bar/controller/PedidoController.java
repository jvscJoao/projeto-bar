package br.com.projeto.bar.projeto_bar.controller;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.bar.projeto_bar.entity.Pedido;
import br.com.projeto.bar.projeto_bar.services.PedidoService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> searchAll() {
        List<Pedido> list = pedidoService.searchAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> searchById(@PathVariable(name = "id") Long id) {
        Pedido obj = pedidoService.searchById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody Pedido pedido) {
        Pedido newObj = pedidoService.insert(pedido);
        URI locator = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newObj.getId())
        .toUri();
        return ResponseEntity.created(locator).body(newObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable(name = "id") Long id, @RequestBody Pedido pedido) {
        Pedido updatedObj = pedidoService.update(id, pedido);
        return ResponseEntity.ok().body(updatedObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
