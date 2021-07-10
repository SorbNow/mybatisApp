package com.sorb.mybatisapp.service;

import com.sorb.mybatisapp.mapper.RegionMapper;
import com.sorb.mybatisapp.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public List<Region> getRegions() {
        return regionMapper.getRegions();
    }

    @Override
    public Region getRegionById(int id) {
        return regionMapper.getRegionById(id);
    }

    @Override
    public Integer createRegion(Region region) {
        return regionMapper.addRegion(region);
    }

    @Override
    public void updateRegion(Region region, int id) {
       regionMapper.updateRegion(region.getFullName(), region.getShortName(), id);
    }

    @Override
    public void deleteRegion(int id) {
        regionMapper.deleteRegion(id);
    }
}
