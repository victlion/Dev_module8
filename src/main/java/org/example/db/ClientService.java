package org.example.db;

import org.example.db.exception.InvalidNameException;
import org.example.db.exception.NumberFormatException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    Connection connection;

    public ClientService() {
        connection = ConnectedDB.getConnection();
    }

    public long create(String name) {
        if (validateName(name)) {
            String textSql = "INSERT INTO client VALUES (?,?);";
            int id = getMaxId() + 1;
            try {
                PreparedStatement statement = connection.prepareStatement(textSql);
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return id;
        } else {
            return -1;
        }
    }

    public String getById(long id) {
        String name = "";
        if (validateId((int) id)) {
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT NAME FROM CLIENT WHERE ID = ?");
                statement.setInt(1, (int) id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    name = resultSet.getString("NAME");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return name;
        }
        return "";
    }

    public void setName(long id, String name) {
        if (validateId((int) id) && validateName(name)) {
            String textSql = "UPDATE CLIENT SET NAME=? WHERE ID=?;";
            try {
                PreparedStatement statement = connection.prepareStatement(textSql);
                statement.setString(1, name);
                statement.setInt(2, (int) id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteById(long id) {
        if (validateId((int) id)) {
            deleteProjectWorker((int) id);
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM PROJECT WHERE CLIENT_ID=?;");
                statement.setInt(1, (int) id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM CLIENT WHERE ID=?;");
                statement.setInt(1, (int) id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();
        Client client = new Client();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("ID"));
                client.setName(resultSet.getString("NAME"));
                result.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void deleteProjectWorker(int id) {
        List<Integer> idList = new ArrayList<>();
        idList = getIdProject(id);
        for (Integer ID : idList) {
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM PROJECT_WORKER WHERE PROJECT_ID=?;");
                statement.setInt(1, ID);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Integer> getIdProject(int id) {
        List<Integer> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM PROJECT WHERE CLIENT_ID = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private int getMaxId() {
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM CLIENT WHERE ID = (SELECT MAX(ID) FROM CLIENT)");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private boolean validateName(String name) {
        if (name.length() < 3 || name.length() > 21) {
            try {
                throw new InvalidNameException("name length 3 <> 19");
            } catch (InvalidNameException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private boolean validateId(int id) {
        if (id < 1) {
            try {
                throw new NumberFormatException("id > 0 !");
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
