package com.leo.controllers.admin;

import com.leo.dao.MovieDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Movie;

import java.util.List;

public class MovieManagerController {
  private MovieDao movieDao = MovieDao.getInstance();

  public MovieManagerController() {
  }

  public ResponseDto<Movie> get(Integer id) {
    ResponseDto<Movie> responseDto = new ResponseDto<>();
    try {
      Movie movie = movieDao.get(id);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get movie successfully");
      responseDto.setPayload(movie);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<Movie>> getAll() {
    ResponseDto<ListDto<Movie>> responseDto = new ResponseDto<>();
    try {
      ListDto<Movie> listDto = new ListDto<>();
      listDto.setList(movieDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list movies successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> add(Movie movie) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      movieDao.save(movie);
      responseDto.setMessage("Add movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> edit(Movie movie) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      movieDao.update(movie);
      responseDto.setMessage("Edit movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> delete(Movie movie) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      movieDao.delete(movie);
      responseDto.setMessage("Delete movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> deleteByIds(List<Integer> ids) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      movieDao.deleteByIds(ids);
      responseDto.setMessage("Delete movies successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<Movie>> search(String key, String value) {
    ResponseDto<ListDto<Movie>> responseDto = new ResponseDto<>();
    try {
      ListDto<Movie> listDto = new ListDto<>();
      List<Movie> movies = movieDao.searchByKey(key, value);
      listDto.setList(movies);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list movies successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
