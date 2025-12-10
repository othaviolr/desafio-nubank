package springboot.desafio.nubank.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.desafio.nubank.dto.ClientesDTO;
import springboot.desafio.nubank.dto.ClientesResponseDTO;
import springboot.desafio.nubank.dto.ContatoResponseDTO;
import springboot.desafio.nubank.model.Clientes;
import springboot.desafio.nubank.service.ClientesService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> criar(@RequestBody ClientesDTO dto) {

        Clientes clienteSalvo = clientesService.salvarCliente(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clientesService.listarTodos());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatos(@PathVariable Long id) {

        return ResponseEntity.ok(clientesService.listarContatosPorCliente(id));
    }
}
