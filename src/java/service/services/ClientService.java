package service.services;

import dao.entities.ClientEntity;
import dao.repositories.ClientRepository;
import org.json.simple.parser.ParseException;
import service.domain_objects.Client;
import service.mappers.ClientMapper;

import java.io.IOException;
import java.util.Optional;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private ClientMapper clientMapper = new ClientMapper();

    public ClientService() throws IOException, ParseException {
    }

    public boolean isAdmin(String username) throws Exception {
        Optional<ClientEntity> clientEntityOptional = clientRepository.get(username);
        ClientEntity clientEntity = clientEntityOptional.orElse(null);
        if (clientEntity != null){
            return clientEntity.isAdmin();
        }
        else{
            throw new Exception("No such user");
        }
    }

    public Client getByUsername(String username) throws Exception {
        Optional<ClientEntity> clientEntityOptional = clientRepository.get(username);
        ClientEntity clientEntity = clientEntityOptional.orElse(null);
        if (clientEntity != null){
            return clientMapper.mapToClient(clientEntity);
        }
        else{
            throw new Exception("No such user");
        }
    }
}
