package com.example.cityAPI.dao;

import com.example.cityAPI.model.ApiCall;
import com.example.cityAPI.model.City;
import com.example.cityAPI.model.Data;

import javax.persistence.EntityManager;
import java.util.List;

public interface ApiCallDAO {

    void save(ApiCall apiCall);

    List<ApiCall> localFindAll();

    ApiCall localFindById(int id);

    void localDeleteById(int id);

}
