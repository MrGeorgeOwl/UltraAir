package service;

import dao.entities.ClientEntity;

public class ClientMapper {

    public Client mapToClient(ClientEntity clientEntity){
        String username = clientEntity.getUsername();

        return new Client(username, false);
    }
}
