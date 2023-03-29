package com.leo.service.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.component.ServiceHandler;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Auditorium;
import com.leo.service.IAuditoriumService;
import com.leo.utils.Sockets;

public class AuditoriumService implements IAuditoriumService {
  private static IAuditoriumService instance;

  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public Auditorium get(int auditoriumId) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_AUDITORIUM_BY_ID", auditoriumId,
            new TypeReference<ResponseDto<Auditorium>>() {
            })
        .getPayload();
  }

  @Override
  public List<Auditorium> getAll() throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_AUDITORIUM", null,
            new TypeReference<ResponseDto<List<Auditorium>>>() {
            })
        .getPayload();
  }

  @Override
  public List<Auditorium> searchByKey(String key, String term) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "SEARCH_AUDITORIUM", SearchDto.builder().key(key).value(term).build(),
            new TypeReference<ResponseDto<List<Auditorium>>>() {
            })
        .getPayload();
  }

  @Override
  public void deleteByIds(List<Integer> ids) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "DELETE_AUDITORIUM_BY_IDS", ids,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  @Override
  public void save(Auditorium auditorium) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_AUDITORIUM", auditorium,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  @Override
  public void update(Auditorium auditorium) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "UPDATE_AUDITORIUM", auditorium,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  public static IAuditoriumService getInstance() {
    if (instance == null) {
      synchronized (AuditoriumService.class) {
        if (instance == null) {
          instance = new AuditoriumService();
        }
      }
    }
    return instance;
  }
}
