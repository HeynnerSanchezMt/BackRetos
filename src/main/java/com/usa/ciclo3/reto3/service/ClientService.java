package com.usa.ciclo3.reto3.service;

import com.usa.ciclo3.reto3.model.Client;
import com.usa.ciclo3.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es el servicio de cliente
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Este metodo obtiene toda la lista de Clientes
     * @return
     */
    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    /**
     * Este metodo obtiene un cliente por Id
     * @param idClient
     * @return
     */
    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }

    /**
     * Este metodo guarda un cliente
     * @param client
     * @return
     */
    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> caux = clientRepository.getClient(client.getIdClient());
            if (caux.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    /**
     * Este metodo actualiza un cliente
     * @param client
     * @return
     */
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getEmail() != null) {
                    e.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    e.get().setPassword(client.getPassword());
                }
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                clientRepository.save(e.get());
                return e.get();

            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    /**
     * Este metodo borra un cliente
     * @param idClient
     * @return
     */
    public boolean deleteClient(int idClient) {
        Boolean aBoolean = getClient(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
