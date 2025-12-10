package springboot.desafio.nubank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.desafio.nubank.dto.ContatoDTO;
import springboot.desafio.nubank.model.Clientes;
import springboot.desafio.nubank.model.Contatos;
import springboot.desafio.nubank.repository.ClientesRepository;
import springboot.desafio.nubank.repository.ContatosRepository;

import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatosRepository contatosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContatoDTO dto) {
        Optional<Clientes> clientesOpt = clientesRepository.findById(dto.getClienteId());
        if(clientesOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado");
        }

        Contatos contatos = new Contatos();
        contatos.setTelefone(dto.getTelefone());
        contatos.setEmail(dto.getEmail());
        contatos.setClientes(clientesOpt.get());
        contatosRepository.save(contatos);

        return ResponseEntity.status(HttpStatus.CREATED).body(contatos);
    }

}
