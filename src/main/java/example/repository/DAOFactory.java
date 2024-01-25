package example.repository;

import java.util.Properties;

public class DAOFactory {
    private static Repository repository;

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public Repository getResultDAO() {
        if (repository == null)
            repository = new ResultRepository();
        return repository;
    }
}
