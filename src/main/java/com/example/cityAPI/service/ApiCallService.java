package com.example.cityAPI.service;

import com.example.cityAPI.model.ApiCall;
import com.example.cityAPI.model.City;
import com.example.cityAPI.model.Data;

import java.util.List;

public interface ApiCallService {

    void save(ApiCall apiCall);

    List<ApiCall> localFindAll();

    ApiCall localFindById(int id);

    void localDeleteById(int id);

}
