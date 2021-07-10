package com.sorb.mybatisapp.mapper;

import com.sorb.mybatisapp.model.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM region")
    List<Region> getRegions();

}
