package com.leo.service.impl;

import java.util.List;

import com.leo.models.Showtime;
import com.leo.service.IShowtimeService;

public class ShowtimeService implements IShowtimeService {
    private static IShowtimeService instance;

    @Override
    public Showtime get(int showtimeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void deleteById(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<Showtime> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public List<Showtime> searchByKey(String string, String valueOf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByKey'");
    }

    @Override
    public void save(Showtime showtime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Showtime showtime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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

    @Override
    public void deleteByIds(List<Integer> selectedIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByIds'");
    }
}
