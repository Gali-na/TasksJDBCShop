package DAO;

import Model.Client;

import java.util.List;

public interface ClientDAO {
    boolean addClient(Client client);
    boolean updatePasswordClient(Client client, String newPassword);

    List<Client> showAllClient();

    List<Client> customerSearchByEmail(String email);


}
