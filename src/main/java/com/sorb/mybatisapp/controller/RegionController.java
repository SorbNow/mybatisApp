package com.sorb.mybatisapp.controller;

import com.sorb.mybatisapp.model.Region;
import com.sorb.mybatisapp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getRegionList() {
        List<Region> regions = regionService.getRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }
}
