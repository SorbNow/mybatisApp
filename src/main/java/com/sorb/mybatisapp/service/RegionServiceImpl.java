package com.sorb.mybatisapp.service;

import com.sorb.mybatisapp.mapper.RegionMapper;
import com.sorb.mybatisapp.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiPredicate;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public List<Region> getRegions() {

        List<Region> region = regionMapper.getRegions();
        if (region != null) log.info("Regions list fetched successfully");
        return region;
    }

    @Override
    @Cacheable("regions")
    public Region getRegionById(int id) {
        Region region = regionMapper.getRegionById(id);
        if (region != null) log.info("Region fetched successfully");
        return region;
    }

    @Override
    @CachePut(value = "regions")
    public Integer createRegion(Region region) {
        Integer result = null;
        if (region.hasAllFilledFields()) result = regionMapper.addRegion(region.trimFields());
        if (result != null) log.info("Region with full name " + region.getFullName() + " created");
        return result;
    }

    @Override
    @CachePut(value = "regions", key = "#id")
    public Region updateRegion(Region region, int id) {
        BiPredicate<String, String> isRequireUpdate =
                (newString, oldString) -> !newString.equals(oldString) &&
                        !newString.trim().equals(oldString) &&
                        !newString.trim().isEmpty();
        int count = 0;
        Region oldRegion = regionMapper.getRegionById(id);
        if (isRequireUpdate.test(region.getFullName(), oldRegion.getFullName())) {
            oldRegion.setFullName(region.getFullName().trim());
            count++;
        }
        if (isRequireUpdate.test(region.getShortName(), oldRegion.getShortName())) {
            oldRegion.setShortName(region.getShortName().trim());
            count++;
        }

        if (count > 0) {
            regionMapper.updateRegion(oldRegion);
            log.info("Region with id: " + id + " updated");
        } else {
            log.info("Nothing to update in region with id: " + id);
        }
        return regionMapper.getRegionById(id);
    }

    @Override
    @CacheEvict(value = "regions", key = "#id")
    public void deleteRegion(int id) {
        regionMapper.deleteRegion(id);
        if (regionMapper.getRegionById(id) == null) log.info("Region with id: " + id + " deleted");
    }

/*    private Region trimFields(Region region) {
        region.setFullName(region.getFullName().trim());
        region.setShortName(region.getShortName().trim());
        return region;
    }*/

  /*  private Region updateRegionFields(Region newRegionFields, int id) {
        Region region = regionMapper.getRegionById(id);
        if (newRegionFields.getFullName().equals(region.getFullName()) &&
                newRegionFields.getFullName() != null &&
                !newRegionFields.getFullName().trim().isEmpty())
            region.setFullName(newRegionFields.getFullName());
        if (newRegionFields.getShortName().equals(region.getFullName()) &&
                newRegionFields.getShortName() != null &&
                !newRegionFields.getShortName().trim().isEmpty())
            region.setShortName(newRegionFields.getShortName());

        return region;
    }

    private boolean isRequireUpdate(String newString, String oldString) {
        if (!newString.equals(oldString) &&
                newString != null &&
                !newString.trim().isEmpty())
            return true;
        return false;
        Predicate<>
    }
*/
}
