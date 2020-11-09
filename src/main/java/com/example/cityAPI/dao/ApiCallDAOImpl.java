package com.example.cityAPI.dao;

import com.example.cityAPI.model.ApiCall;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ApiCallDAOImpl implements ApiCallDAO {

    EntityManager entityManager;

    @Autowired
    public ApiCallDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(ApiCall apiCall) {

        //Get Hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //1)Save apiCall if id=0 or 2)update apiCall if id!=0
        currentSession.saveOrUpdate(apiCall);

    }

    @Override
    public List<ApiCall> localFindAll() {

        //Get Hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //Create query
        Query<ApiCall> theQuery = currentSession.createQuery("from apicall",ApiCall.class);

        //Execute query and get result list
        List<ApiCall> apiCalls = theQuery.getResultList();

        //Return the result
        return apiCalls;

    }

    @Override
    public ApiCall localFindById(int id) {

        //Get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //Get the apicall
        ApiCall apiCall = currentSession.get(ApiCall.class, id);

        //Return apicall
        return apiCall;
    }

    @Override
    public void localDeleteById(int id) {

        //Get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //Get the apicall
        ApiCall apiCall = currentSession.get(ApiCall.class, id);

        //Remove this apicall
        currentSession.delete(apiCall);

    }

}
