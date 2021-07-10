package com.sorb.mybatisapp.mapper;

import com.sorb.mybatisapp.model.Region;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM region")
    List<Region> getRegions();

    @Select("SELECT * FROM region WHERE id = #{id}")
    Region getRegionById(int id);

    @Select("INSERT into region (fullName,shortName) VALUES(#{fullName}, #{shortName})")
    Region addRegion(int id);

    @Update("UPDATE region SET fullName=#{fullName}, shortName =#{shortName} WHERE id =#{id}")
    void updateRegion(Region region);

    @Delete("DELETE FROM region WHERE id =#{id}")
    void deleteRegion(int id);

}
