package com.dobby.petclinic.mapper;

import com.dobby.petclinic.common.MyMapper;
import com.dobby.petclinic.domain.Visit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liguoqing
 */
public interface VisitMapper extends MyMapper<Visit> {

    @Select("select * from visits where pet_id = #{petId}")
    List<Visit> findVisitsByPetId(@Param("petId") Integer petId);
}
