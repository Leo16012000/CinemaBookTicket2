package com.leo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;
import com.leo.utils.Convert;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ServiceRegistry {
    private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
    private Convert convert = new Convert();

    private XmlMapper xmlMapper = new XmlMapper();

    public ResponseDto handleRequest(String request) throws SQLException, JsonProcessingException {
        RequestDto requestDto
                = xmlMapper.readValue(request, RequestDto.class);
        String serviceName = requestDto.getServiceName();
        LinkedHashMap payload = requestDto.getPayload();
        switch (serviceName) {
            case "CREATE_AUDITORIUM":
                Auditorium auditorium = new Auditorium(Integer.parseInt(payload.get("auditoriumNum").toString()), Integer.parseInt(payload.get("seatsRowNum").toString()), Integer.parseInt(payload.get("seatsColumnNum").toString()));
                return auditoriumManagerController.actionAdd(auditorium);
//            case "read":
//                return "Response from MyServiceController for read operation";
            case "UPDATE_AUDITORIUM":
                auditorium = new Auditorium(Integer.parseInt(payload.get("id").toString()), Integer.parseInt(payload.get("auditoriumNum").toString()), Integer.parseInt(payload.get("seatsRowNum").toString()), Integer.parseInt(payload.get("seatsColumnNum").toString()));
                return auditoriumManagerController.actionEdit(auditorium);
            case "DELETE_AUDITORIUM":
                auditorium = new Auditorium();
                auditorium.setId(Integer.parseInt(payload.get("id").toString()));
                return auditoriumManagerController.actionDelete(auditorium);
            default:
                throw new IllegalArgumentException("Invalid service name: " + requestDto.getServiceName());
        }
    }
}

