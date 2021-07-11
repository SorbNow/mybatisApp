package com.sorb.mybatisapp.service;

import com.sorb.mybatisapp.model.Region;

import java.util.List;

public interface RegionService {
    List<Region> getRegions();

    Region getRegionById(int id);

    Integer createRegion(Region region);

    void updateRegion(Region region, int id);

    void deleteRegion(int id);
}
