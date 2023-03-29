package com.leo.controllers.admin;

import com.leo.dao.MovieDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Movie;

import java.util.List;

public class MovieManagerController {
  private MovieDao movieDao = new MovieDao();

  public MovieManagerController() {}

  public ResponseDto getAll() {
    ResponseDto<ListDto<Movie>> responseDto = new ResponseDto();
    try{
      ListDto<Movie> listDto = new ListDto();
      listDto.setList(movieDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list movies successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
  public ResponseDto add(Movie movie){
    ResponseDto responseDto = new ResponseDto();
    try{
      Integer id = movieDao.save(movie);
      responseDto.setMessage("Add movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto edit(Movie movie) {
    ResponseDto responseDto = new ResponseDto();
    try{
      movieDao.update(movie);
      responseDto.setMessage("Edit movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto delete(Movie movie) {
    ResponseDto responseDto = new ResponseDto();
    try{
      movieDao.delete(movie);
      responseDto.setMessage("Delete movie successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto search(String key, String value) {
    ResponseDto<ListDto<Movie>> responseDto = new ResponseDto();
    try{
      ListDto<Movie> listDto = new ListDto();
      List<Movie> movies = movieDao.searchByKey(key, value);
      listDto.setList(movies);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list movies successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
