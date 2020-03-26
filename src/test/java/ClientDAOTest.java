import dao.entities.ClientEntity;
import dao.repositories.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ClientDAOTest {
    Logger logger = LogManager.getLogger();

    @Test
    public void constructorTest() throws ParseException, java.text.ParseException, IOException {
        ClientRepository clientRepository = new ClientRepository();
        logger.info(clientRepository.toString());
    }

    @Test
    public void flightTest() throws ParseException, java.text.ParseException, IOException {
        ClientRepository clientRepository = new ClientRepository();
        Optional<ClientEntity> clientEntityOptional = clientRepository.get("admin");
        ClientEntity clientEntity = clientEntityOptional.orElse(new ClientEntity());

        logger.info(clientEntity.toString());
    }

    @Test
    public void saveTest() throws IOException, ParseException {
        ClassLoader classLoader = ClientRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("Clients.json").getFile());
        String fileName = file.getAbsolutePath();

        logger.info("Saved at " + fileName);
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.save();

    }
}
