package com.leo.service.impl;

import java.util.List;

import com.leo.models.Movie;
import com.leo.service.IMovieService;

public class MovieService implements IMovieService {
    private static IMovieService instance;

    @Override
    public List<Movie> searchByKey(String key, String term) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByKey'");
    }

    @Override
    public List<Movie> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Movie get(int selectedId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void deleteById(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void save(Movie movie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Movie movie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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

    @Override
    public void deleteByIds(List<Integer> selectedIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByIds'");
    }
}
