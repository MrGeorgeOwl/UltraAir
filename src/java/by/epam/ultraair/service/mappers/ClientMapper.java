package by.epam.ultraair.service.mappers;

import by.epam.ultraair.dao.entities.ClientEntity;
import by.epam.ultraair.service.domain_objects.Client;

public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity){
        String username = clientEntity.getUsername();

        return new Client(username, false);
    }
}
