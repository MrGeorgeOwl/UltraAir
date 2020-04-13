package by.epam.ultraair.persistence.mappers;

import by.epam.ultraair.dao.entities.ClientEntity;
import by.epam.ultraair.persistence.domain_objects.Client;

public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity){
        String username = clientEntity.getUsername();

        return new Client(username, false);
    }
}
