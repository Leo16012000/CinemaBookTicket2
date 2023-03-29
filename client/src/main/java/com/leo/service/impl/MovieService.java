package com.leo.service.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.component.ServiceHandler;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Movie;
import com.leo.service.IMovieService;
import com.leo.utils.Sockets;

public class MovieService implements IMovieService {
  private static IMovieService instance;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public List<Movie> searchByKey(String key, String term) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "", SearchDto.builder().key(key).value(term).build(),
            new TypeReference<ResponseDto<List<Movie>>>() {
            })
        .getPayload();
  }

  @Override
  public List<Movie> getAll() throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", null, new TypeReference<ResponseDto<List<Movie>>>() {
    }).getPayload();
  }

  @Override
  public Movie get(int selectedId) throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", selectedId, Movie.class).getPayload();
  }

  @Override
  public void save(Movie movie) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "", movie, Void.class).getPayload();
  }

  @Override
  public void deleteByIds(List<Integer> selectedIds) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "", selectedIds, Void.class);
  }

  @Override
  public void update(Movie movie) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "", movie, Void.class).getPayload();
  }

  public static IMovieService getInstance() {
    if (instance == null) {
      synchronized (MovieService.class) {
        if (instance == null) {
          instance = new MovieService();
        }
      }
    }
    return instance;
  }
}
