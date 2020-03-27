package service.mappers;

import dao.entities.ClientEntity;
import service.domain_objects.Client;

public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity){
        String username = clientEntity.getUsername();

        return new Client(username, false);
    }
}
