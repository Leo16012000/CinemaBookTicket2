package com.leo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Auditorium;
import com.leo.utils.ObjectMappers;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceRegistry {
  private static ServiceRegistry instance;
  private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
  private ObjectMapper xmlMapper = ObjectMappers.getInstance();

  public ResponseDto<?> handleRequest(String request) throws SQLException, JsonProcessingException {
    RequestDto<?> requestDto = xmlMapper.readValue(request, RequestDto.class);
    checkAuthentication(requestDto);
    String serviceName = requestDto.getServiceName();
    switch (serviceName) {
      case "CREATE_AUDITORIUM":
        RequestDto<Auditorium> requestDto1 = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
        });
        Auditorium auditorium = requestDto1.getPayload();
        return auditoriumManagerController.add(auditorium);
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
        return auditoriumManagerController.getAll();
      case "SEARCH_AUDITORIUM":
        RequestDto<SearchDto> requestDto2 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
        });
        String key = requestDto2.getPayload().getKey();
        String value = requestDto2.getPayload().getValue();
        return auditoriumManagerController.search(key, value);
      default:
        throw new IllegalArgumentException("Invalid service name: " + requestDto.getServiceName());
    }
  }

  private void checkAuthentication(RequestDto<?> requestDto) {
    if (requestDto.getAuthentication() == null) {
        requestDto.setAuthenticated(false);
        return;
    }
    String authentication = (String) requestDto.getAuthentication();
    // TODO: Check validity of authentication before set authenticated
    requestDto.setAuthenticated(true);
  }

  public static ServiceRegistry getInstance() {
    if (instance == null) {
      synchronized (ServiceRegistry.class) {
        if (instance == null) {
          instance = new ServiceRegistry();
        }
      }
    }
    return instance;
  }
}
