package com.sorb.mybatisapp.service;

import com.sorb.mybatisapp.mapper.RegionMapper;
import com.sorb.mybatisapp.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    @Cacheable("regions")
    public List<Region> getRegions() {

        List<Region> region = regionMapper.getRegions();
        if (region != null) log.info("Region fetched successfully");
        return regionMapper.getRegions();
    }

    @Override
    @Cacheable(value = "regions")
    public Region getRegionById(int id) {
        Region region = regionMapper.getRegionById(id);
        if (region != null) log.info("Region fetched successfully");
        return region;
    }

    @Override
    @CachePut(value = "regions")
    public Integer createRegion(Region region) {

        Integer result = regionMapper.addRegion(region);
        if (result != null) log.info("Region with full name " + region.getFullName() + " created");
        return result;
    }

    @Override
    @CachePut(value = "regions", key = "#id")
    public void updateRegion(Region region, int id) {
        log.info("Region with id: " + id + " updated");
        regionMapper.updateRegion(region.getFullName(), region.getShortName(), id);
    }

    @Override
    @CacheEvict(value = "regions")
    public void deleteRegion(int id) {
        regionMapper.deleteRegion(id);
        if (regionMapper.getRegionById(id) == null) log.info("Region with id: " + id + " deleted");
    }


}
