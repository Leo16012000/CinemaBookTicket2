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
        .sendRequest(Sockets.getSocket(), "SEARCH_MOVIE", SearchDto.builder().key(key).value(term).build(),
            new TypeReference<ResponseDto<List<Movie>>>() {
            })
        .getPayload();
  }

  @Override
  public List<Movie> getAll() throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_MOVIE", null, new TypeReference<ResponseDto<List<Movie>>>() {
        }).getPayload();
  }

  @Override
  public Movie get(int selectedId) throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "GET_MOVIE_BY_ID", selectedId, new TypeReference<ResponseDto<Movie>>() {
        }).getPayload();
  }

  @Override
  public void save(Movie movie) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_MOVIE", movie, new TypeReference<ResponseDto<Void>>() {
    }).getPayload();
  }

  @Override
  public void deleteByIds(List<Integer> selectedIds) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "DELETE_MOVIE_BY_IDS", selectedIds,
        new TypeReference<ResponseDto<Void>>() {
        });
  }

  @Override
  public void update(Movie movie) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "UPDATE_MOVIE", movie, new TypeReference<ResponseDto<Void>>() {
    }).getPayload();
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
