package iouiri.hanane.backend.services;

import iouiri.hanane.backend.dtos.ClientDTO;
import iouiri.hanane.backend.entities.Client;
import iouiri.hanane.backend.mappers.ClientMapper;
import iouiri.hanane.backend.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return client != null ? clientMapper.toDTO(client) : null;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existing = clientRepository.findById(id).orElse(null);
        if (existing != null) {
            clientDTO.setId(id);
            Client updated = clientMapper.toEntity(clientDTO);
            return clientMapper.toDTO(clientRepository.save(updated));
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
