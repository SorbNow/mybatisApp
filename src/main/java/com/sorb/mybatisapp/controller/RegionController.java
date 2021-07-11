package com.sorb.mybatisapp.controller;

import com.sorb.mybatisapp.model.Region;
import com.sorb.mybatisapp.service.RegionService;
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
        if (!isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(regionService.getRegionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {

        if (!isRegionFilled(region)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Integer res = regionService.createRegion(region);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable int id, @RequestBody Region region) {
        if (!isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        regionService.updateRegion(region, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> deleteRegion(@PathVariable int id) {

        if (!isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        regionService.deleteRegion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isExists(int id) {
        Region region = regionService.getRegionById(id);
        return region != null;
    }

    private boolean isRegionFilled(Region region) {
        boolean result;
        try {
            result = region.getFullName() != null && region.getShortName() != null
                    && !region.getShortName().trim().isEmpty() && !region.getFullName().trim().isEmpty();
        } catch (NullPointerException e) {
            return false;
        }
        return result;
    }
}
