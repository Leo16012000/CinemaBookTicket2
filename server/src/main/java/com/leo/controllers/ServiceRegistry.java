package com.leo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.controllers.admin.AuditoriumManagerController;
import com.leo.controllers.admin.MovieManagerController;
import com.leo.controllers.admin.ShowtimeManagerController;
import com.leo.controllers.admin.UserManagerController;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.Auditorium;
import com.leo.models.Movie;
import com.leo.models.Showtime;
import com.leo.models.User;
import com.leo.utils.Convert;
import com.leo.utils.ObjectMappers;

import java.sql.SQLException;

public class ServiceRegistry {
    private static ServiceRegistry instance;
    private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
    private MovieManagerController movieManagerController = new MovieManagerController();
    private UserManagerController userManagerController = new UserManagerController();
    private ShowtimeManagerController showtimeManagerController = new ShowtimeManagerController();

    private Convert convert = new Convert();
    private ObjectMapper xmlMapper = ObjectMappers.getInstance();

    public ResponseDto handleRequest(String request) throws SQLException, JsonProcessingException {
        RequestDto reqDto
                = xmlMapper.readValue(request, RequestDto.class);
        String serviceName = reqDto.getServiceName();
        switch (serviceName) {
            case "CREATE_AUDITORIUM": {
                RequestDto<Auditorium> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                Auditorium auditorium = requestDto.getPayload();
                return auditoriumManagerController.add(auditorium);
            }
            case "UPDATE_AUDITORIUM": {
                RequestDto<Auditorium> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                Auditorium auditorium = requestDto.getPayload();
                return auditoriumManagerController.edit(auditorium);
            }
            case "DELETE_AUDITORIUM": {
                RequestDto<Auditorium> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Auditorium>>() {
                });
                Auditorium auditorium = requestDto.getPayload();
                return auditoriumManagerController.delete(auditorium);
            }
            case "GET_AUDITORIUM":
                return auditoriumManagerController.getAll();
            case "SEARCH_AUDITORIUM": {
                RequestDto<SearchDto> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
                });
                String key = requestDto.getPayload().getKey();
                String value = requestDto.getPayload().getValue();
                return auditoriumManagerController.search(key, value);
            }
//                ----------------------------MOVIE-------------------------------------
            case "CREATE_MOVIE": {
                RequestDto<Movie> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Movie>>() {
                });
                Movie movie = requestDto.getPayload();
                return movieManagerController.add(movie);
            }
            case "UPDATE_MOVIE": {
                RequestDto<Movie> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Movie>>() {
                });
                Movie movie = requestDto.getPayload();
                return movieManagerController.edit(movie);
            }
            case "DELETE_MOVIE": {
                RequestDto<Movie> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Movie>>() {
                });
                Movie movie = requestDto.getPayload();
                return movieManagerController.delete(movie);
            }
            case "GET_MOVIE":
                return movieManagerController.getAll();
            case "SEARCH_MOVIE": {
                RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
                });
                String key = requestDto4.getPayload().getKey();
                String value = requestDto4.getPayload().getValue();
                return movieManagerController.search(key, value);
            }
            //                ----------------------------USER-------------------------------------
            case "CREATE_USER": {
                RequestDto<User> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<User>>() {
                });
                User user = requestDto.getPayload();
                return userManagerController.add(user);
            }
            case "UPDATE_USER": {
                RequestDto<User> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<User>>() {
                });
                User user = requestDto.getPayload();
                return userManagerController.edit(user);
            }
            case "DELETE_USERr": {
                RequestDto<User> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<User>>() {
                });
                User user = requestDto.getPayload();
                return userManagerController.delete(user);
            }
            case "GET_USER":
                return userManagerController.getAll();
            case "SEARCH_USER": {
                RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
                });
                String key = requestDto4.getPayload().getKey();
                String value = requestDto4.getPayload().getValue();
                return userManagerController.search(key, value);
            }
            //                ----------------------------SHOWTIME-------------------------------------
            case "CREATE_SHOWTIME": {
                RequestDto<Showtime> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Showtime>>() {
                });
                Showtime showtime = requestDto.getPayload();
                return showtimeManagerController.add(showtime);
            }
            case "UPDATE_SHOWTIME": {
                RequestDto<Showtime> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Showtime>>() {
                });
                Showtime showtime = requestDto.getPayload();
                return showtimeManagerController.edit(showtime);
            }
            case "DELETE_SHOWTIMEr": {
                RequestDto<Showtime> requestDto = xmlMapper.readValue(request, new TypeReference<RequestDto<Showtime>>() {
                });
                Showtime showtime = requestDto.getPayload();
                return showtimeManagerController.delete(showtime);
            }
            case "GET_SHOWTIME":
                return showtimeManagerController.getAll();
            case "SEARCH_SHOWTIME": {
                RequestDto<SearchDto> requestDto4 = xmlMapper.readValue(request, new TypeReference<RequestDto<SearchDto>>() {
                });
                String key = requestDto4.getPayload().getKey();
                String value = requestDto4.getPayload().getValue();
                return showtimeManagerController.search(key, value);
            }
            default:
                throw new IllegalArgumentException("Invalid service name: " + reqDto.getServiceName());
        }
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
