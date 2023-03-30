package com.leo.service.impl;

import java.io.IOException;
import java.util.List;

import com.leo.dtos.ResponseDto;
import com.leo.utils.ServiceHandler;
import org.apache.commons.math3.util.Pair;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.models.Seat;
import com.leo.service.ISeatService;
import com.leo.utils.Sockets;

public class SeatService implements ISeatService {
  private static ISeatService instance;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public List<Seat> getByAuditoriumIdAndShowtimeId(int auditoriumId, int showtimeId) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "", Pair.create(auditoriumId, showtimeId),
            new TypeReference<ResponseDto<List<Seat>>>() {
            })
        .getPayload();
  }

  public static ISeatService getInstance() {
    if (instance == null) {
      synchronized (SeatService.class) {
        if (instance == null) {
          instance = new SeatService();
        }
      }
    }
    return instance;
  }
}
