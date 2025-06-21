package com.shop.in.servicImpl;

import com.shop.in.model.Holiday;
import com.shop.in.repository.HolidayRepo;
import com.shop.in.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepo repo;

    @Override
    public List<Holiday> getHolidaysList() {
        return repo.findAll();
    }
}
