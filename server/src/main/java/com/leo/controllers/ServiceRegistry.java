package com.leo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.controllers.admin.MovieManagerController;
import com.leo.controllers.admin.ShowtimeManagerController;
import com.leo.controllers.admin.UserManagerController;
import com.leo.dtos.ListDto;
import com.leo.dtos.LoginDto;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Auditorium;
import com.leo.models.Movie;
import com.leo.models.Showtime;
import com.leo.models.User;
import com.leo.utils.ObjectMappers;

import java.sql.SQLException;

public class ServiceRegistry {
  private static ServiceRegistry instance;
  private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
  private MovieManagerController movieManagerController = new MovieManagerController();
  private UserManagerController userManagerController = new UserManagerController();
  private ShowtimeManagerController showtimeManagerController = new ShowtimeManagerController();

  private ObjectMapper xmlMapper = ObjectMappers.getInstance();

  public ResponseDto<?> handleRequest(String request) throws SQLException, JsonProcessingException {
    RequestDto<?> reqDto = xmlMapper.readValue(request, RequestDto.class);
    String serviceName = reqDto.getServiceName();
    ResponseDto<?> responseDto;
    switch (serviceName) {
      case "PING": {
        responseDto = new ResponseDto<>("", "PONG", "PING", null, "SUCCESS");
        break;
      }
      case "CREATE_AUDITORIUM": {
        RequestDto<Auditorium> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
        });
        Auditorium auditorium = requestDto.getPayload();
        responseDto = auditoriumManagerController.add(auditorium);
        break;
      }
      case "UPDATE_AUDITORIUM": {
        RequestDto<Auditorium> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
        });
        Auditorium auditorium = requestDto.getPayload();
        responseDto = auditoriumManagerController.edit(auditorium);
      }
      case "DELETE_AUDITORIUM_BY_IDS": {
        RequestDto<ListDto<Integer>> requestDto = xmlMapper.readValue(request,
            new TypeReference<RequestDto<ListDto<Integer>>>() {
            });
        responseDto = auditoriumManagerController.deleteByIds(requestDto.getPayload());
        break;
      }
      case "GET_AUDITORIUM":
        responseDto = auditoriumManagerController.getAll();
        break;
      case "SEARCH_AUDITORIUM": {
        RequestDto<SearchDto> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
        });
        String key = requestDto.getPayload().getKey();
        String value = requestDto.getPayload().getValue();
        responseDto = auditoriumManagerController.search(key, value);
        break;
      }
      case "GET_AUDITORIUM_BY_ID": {
        RequestDto<Integer> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Integer>>() {
        });
        responseDto = auditoriumManagerController.get(requestDto.getPayload());
        break;
      }
      // ----------------------------MOVIE-------------------------------------
      case "CREATE_MOVIE": {
        RequestDto<Movie> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Movie>>() {
        });
        Movie movie = requestDto.getPayload();
        responseDto = movieManagerController.add(movie);
        break;
      }
      case "UPDATE_MOVIE": {
        RequestDto<Movie> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Movie>>() {
        });
        Movie movie = requestDto.getPayload();
        responseDto = movieManagerController.edit(movie);
        break;
      }
      case "DELETE_MOVIE_BY_IDS": {
        RequestDto<ListDto<Integer>> requestDto = xmlMapper.readValue(request,
            new TypeReference<RequestDto<ListDto<Integer>>>() {
            });
        responseDto = movieManagerController.deleteByIds(requestDto.getPayload());
        break;
      }
      case "GET_MOVIE":
        responseDto = movieManagerController.getAll();
        break;
      case "SEARCH_MOVIE": {
        RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
        });
        String key = requestDto4.getPayload().getKey();
        String value = requestDto4.getPayload().getValue();
        responseDto = movieManagerController.search(key, value);
        break;
      }
      case "GET_MOVIE_BY_ID": {
        RequestDto<Integer> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Integer>>() {
        });
        responseDto = movieManagerController.get(requestDto.getPayload());
        break;
      }
      // ----------------------------USER-------------------------------------
      case "CREATE_USER": {
        RequestDto<User> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<User>>() {
        });
        User user = requestDto.getPayload();
        responseDto = userManagerController.add(user);
        break;
      }
      case "UPDATE_USER": {
        RequestDto<User> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<User>>() {
        });
        User user = requestDto.getPayload();
        responseDto = userManagerController.edit(user);
        break;
      }
      case "DELETE_USER_BY_IDS": {
        RequestDto<ListDto<Integer>> requestDto = xmlMapper.readValue(request,
            new TypeReference<RequestDto<ListDto<Integer>>>() {
            });
        responseDto = userManagerController.deleteByIds(requestDto.getPayload());
        break;
      }
      case "GET_USER":
        responseDto = userManagerController.getAll();
        break;
      case "GET_USER_BY_USERNAME": {
        RequestDto<String> requestDto = xmlMapper.readValue(request,
            new TypeReference<RequestDto<String>>() {
            });
        responseDto = userManagerController.getByUsername(requestDto.getPayload());
        break;
      }
      case "SEARCH_USER": {
        RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
        });
        String key = requestDto4.getPayload().getKey();
        String value = requestDto4.getPayload().getValue();
        responseDto = userManagerController.search(key, value);
        break;
      }
      case "GET_USER_BY_ID": {
        RequestDto<Integer> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Integer>>() {
        });
        responseDto = userManagerController.get(requestDto.getPayload());
        break;
      }
      case "LOGIN": {
        RequestDto<LoginDto> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<LoginDto>>() {
        });
        LoginDto payload = requestDto.getPayload();
        User user = userManagerController.getByUsername(payload.getUsername()).getPayload();
        responseDto = new ResponseDto<String>();
        if (user == null) {
          responseDto.setMessage("No username found");
          responseDto.setStatus("FAILURE");
          break;
        }
        if (!payload.getPassword().equals(user.getPassword())) {
          responseDto.setMessage("Wrong password");
          responseDto.setStatus("FAILURE");
          break;
        }
        responseDto.setMessage("Login successfully");
        responseDto.setStatus("SUCCESS");
        break;
      }
      // ----------------------------SHOWTIME-------------------------------------
      case "CREATE_SHOWTIME": {
        RequestDto<Showtime> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Showtime>>() {
        });
        Showtime showtime = requestDto.getPayload();
        responseDto = showtimeManagerController.add(showtime);
        break;
      }
      case "UPDATE_SHOWTIME": {
        RequestDto<Showtime> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Showtime>>() {
        });
        Showtime showtime = requestDto.getPayload();
        responseDto = showtimeManagerController.edit(showtime);
        break;
      }
      case "DELETE_SHOWTIME_BY_IDS": {
        RequestDto<ListDto<Integer>> requestDto = xmlMapper.readValue(request,
            new TypeReference<RequestDto<ListDto<Integer>>>() {
            });
        responseDto = showtimeManagerController.deleteByIds(requestDto.getPayload());
        break;
      }
      case "GET_SHOWTIME":
        responseDto = showtimeManagerController.getAll();
        break;
      case "SEARCH_SHOWTIME": {
        RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
        });
        String key = requestDto4.getPayload().getKey();
        String value = requestDto4.getPayload().getValue();
        responseDto = showtimeManagerController.search(key, value);
        break;
      }
      case "GET_SHOWTIME_BY_ID": {
        RequestDto<Integer> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Integer>>() {
        });
        responseDto = showtimeManagerController.get(requestDto.getPayload());
        break;
      }
      default:
        throw new IllegalArgumentException("Invalid service name: " + reqDto.getServiceName());
    }
    responseDto.setServiceName(serviceName);
    return responseDto;
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
