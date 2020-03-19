package service;

import DAO.ClientEntity;
import DAO.ClientRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Optional;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();

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
}
