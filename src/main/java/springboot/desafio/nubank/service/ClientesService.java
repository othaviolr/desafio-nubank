package springboot.desafio.nubank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.desafio.nubank.dto.ClientesDTO;
import springboot.desafio.nubank.dto.ClientesResponseDTO;
import springboot.desafio.nubank.dto.ContatoResponseDTO;
import springboot.desafio.nubank.model.Clientes;
import springboot.desafio.nubank.model.Contatos;
import springboot.desafio.nubank.repository.ClientesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarCliente(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if (dto.getContatos() != null && dto.getContatos().size() >  0){
            List<Contatos> contatos = dto.getContatos().stream().map(c -> {
                Contatos contato = new Contatos();
                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos() {
        return  clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ContatoResponseDTO> listarContatosPorCliente(Long clienteId) {

        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());

            return contatoDTO;
        }).collect(Collectors.toList());
    }

    private ClientesResponseDTO toDTO(Clientes cliente) {
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());

            return contatoDTO;
        }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;
    }
}
