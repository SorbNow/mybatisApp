package com.sorb.mybatisapp.mapper;

import com.sorb.mybatisapp.model.Region;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM region")
    List<Region> getRegions();

    @Select("SELECT * FROM region WHERE id = #{id}")
    Region getRegionById(int id);

    @Insert("INSERT into region (fullName,shortName) VALUES(#{fullName}, #{shortName})")
    Integer addRegion(Region region);

    @Update("UPDATE region SET fullName=#{fullName}, shortName =#{shortName} WHERE id =#{id}")
    void updateRegion(Region region);

    @Delete("DELETE FROM region WHERE id =#{id}")
    void deleteRegion(int id);

}
