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
    return serviceHandler.sendRequest(Sockets.getSocket(), "", showtimeId, Showtime.class).getPayload();

  }

  @Override
  public List<Showtime> getAll() throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "", null, new TypeReference<ResponseDto<List<Showtime>>>() {
        }).getPayload();
  }

  @Override
  public List<Showtime> searchByKey(String key, String term) throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", SearchDto.builder().key(key).value(term).build(),
        new TypeReference<ResponseDto<List<Showtime>>>() {
        }).getPayload();
  }

  @Override
  public void save(Showtime showtime) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_AUDITORIUM", showtime, Void.class);
  }

  @Override
  public void update(Showtime showtime) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_AUDITORIUM", showtime, Void.class);
  }

  @Override
  public void deleteByIds(List<Integer> selectedIds) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "", selectedIds, Void.class);

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
