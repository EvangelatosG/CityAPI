package com.example.cityAPI.service;

import com.example.cityAPI.dao.ApiCallDAO;
import com.example.cityAPI.model.ApiCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    ApiCallDAO apiCallDAO;

    @Autowired
    public ApiCallServiceImpl(ApiCallDAO apiCallDAO){
        this.apiCallDAO = apiCallDAO;
    }

    @Override
    @Transactional
    public void save(ApiCall apiCall) {
        apiCallDAO.save(apiCall);
    }

    @Override
    @Transactional
    public List<ApiCall> localFindAll() {
        return apiCallDAO.localFindAll();
    }

    @Override
    @Transactional
    public ApiCall localFindById(int id) {
        return apiCallDAO.localFindById(id);
    }

    @Override
    @Transactional
    public void localDeleteById(int id) {
        apiCallDAO.localDeleteById(id);
    }

}
