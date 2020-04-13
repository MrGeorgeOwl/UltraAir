package by.epam.ultraair.persistence.services;

import by.epam.ultraair.persistence.domain_objects.Client;
import by.epam.ultraair.persistence.mappers.ClientMapper;
import by.epam.ultraair.dao.entities.ClientEntity;
import by.epam.ultraair.dao.repositories.ClientRepository;
import org.json.simple.parser.ParseException;

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
