package com.leo.service.impl;

import java.util.List;

import com.google.protobuf.Service;
import com.leo.component.ServiceHandler;
import com.leo.models.Auditorium;
import com.leo.service.IAuditoriumService;

public class AuditoriumService implements IAuditoriumService {
    private static IAuditoriumService instance;

    private ServiceHandler serviceHandler = ServiceHandler.getInstance();

    @Override
    public Auditorium get(int auditoriumId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Auditorium> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public List<Auditorium> searchByKey(String string, String valueOf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByKey'");
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void save(Auditorium showtime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Auditorium showtime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
    public static IAuditoriumService getInstance() {
        if (instance == null) {
            synchronized (AuditoriumService.class) {
                if (instance == null) {
                    instance = new AuditoriumService();
                }
            }
        }
        return instance;
    }
}
