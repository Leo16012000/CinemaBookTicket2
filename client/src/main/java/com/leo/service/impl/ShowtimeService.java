package com.leo.service.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.component.ServiceHandler;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Showtime;
import com.leo.service.IShowtimeService;
import com.leo.utils.Sockets;

public class ShowtimeService implements IShowtimeService {
  private static IShowtimeService instance;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public Showtime get(int showtimeId) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_SHOWTIME_BY_ID", showtimeId, new TypeReference<ResponseDto<Showtime>>() {
        }).getPayload();

  }

  @Override
  public List<Showtime> getAll() throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_SHOWTIME", null, new TypeReference<ResponseDto<List<Showtime>>>() {
        }).getPayload();
  }

  @Override
  public List<Showtime> searchByKey(String key, String term) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "SEARCH_SHOWTIME", SearchDto.builder().key(key).value(term).build(),
            new TypeReference<ResponseDto<List<Showtime>>>() {
            })
        .getPayload();
  }

  @Override
  public void save(Showtime showtime) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_SHOWTIME", showtime,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  @Override
  public void update(Showtime showtime) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "UPDATE_SHOWTIME", showtime,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  @Override
  public void deleteByIds(List<Integer> selectedIds) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "DELETE_SHOWTIME_BY_IDS", selectedIds,
        new TypeReference<ResponseDto<Void>>() {
        });

  }

  public static IShowtimeService getInstance() {
    if (instance == null) {
      synchronized (ShowtimeService.class) {
        if (instance == null) {
          instance = new ShowtimeService();
        }
      }
    }
    return instance;
  }
}
