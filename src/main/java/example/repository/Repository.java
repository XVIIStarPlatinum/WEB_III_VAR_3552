package example.repository;

import example.entity.ResultEntity;

import java.util.Collection;

public interface Repository {

    void addNewResult(ResultEntity result);

    void updateResult(Long result_id, ResultEntity result);

    ResultEntity getResultById(Long result_id);

    Collection<ResultEntity> getAllResults();

    void deleteResult(ResultEntity result);

    void clearResults();
}
