package com.leo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.dao.AuditoriumDao;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Auditorium;
import com.leo.utils.Convert;

import java.sql.SQLException;

public class ServiceRegistry {
    private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
    private Convert convert = new Convert();
    private XmlMapper xmlMapper = new XmlMapper();

    public ResponseDto handleRequest(String request) throws SQLException, JsonProcessingException {
        RequestDto requestDto
                = xmlMapper.readValue(request, RequestDto.class);
        String serviceName = requestDto.getServiceName();
        switch (serviceName) {
            case "CREATE_AUDITORIUM":
                RequestDto<Auditorium> requestDto1 = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                Auditorium auditorium = requestDto1.getPayload();
                return auditoriumManagerController.add(auditorium);
//            case "read":
//                return "Response from MyServiceController for read operation";
            case "UPDATE_AUDITORIUM":
                requestDto1 = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                auditorium = requestDto1.getPayload();
                return auditoriumManagerController.edit(auditorium);
            case "DELETE_AUDITORIUM":
                requestDto1 = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                auditorium = requestDto1.getPayload();
                return auditoriumManagerController.delete(auditorium);
            case "GET_AUDITORIUM":
                AuditoriumDao auditoriumDao = new AuditoriumDao();
                return auditoriumManagerController.getAll();
            case "SEARCH_AUDITORIUM":
                RequestDto<SearchDto> requestDto2 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {});
                String key = requestDto2.getPayload().getKey();
                String value = requestDto2.getPayload().getValue();
                return auditoriumManagerController.search(key, value);
            default:
                throw new IllegalArgumentException("Invalid service name: " + requestDto.getServiceName());
        }
    }
}

