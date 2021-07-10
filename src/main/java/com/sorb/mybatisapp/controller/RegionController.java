package com.sorb.mybatisapp.controller;

import com.sorb.mybatisapp.model.Region;
import com.sorb.mybatisapp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable int id) {
        return new ResponseEntity<>(regionService.getRegionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Integer res = regionService.createRegion(region);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable int id, @RequestBody Region region) {
        regionService.updateRegion(region, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> deleteRegion(@PathVariable int id){
        regionService.deleteRegion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
