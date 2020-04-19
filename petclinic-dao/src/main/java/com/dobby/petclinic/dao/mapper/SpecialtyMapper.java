package com.dobby.petclinic.dao.mapper;


import com.dobby.petclinic.dao.util.MyMapper;
import com.dobby.petclinic.domain.Specialty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialtyMapper extends MyMapper<Specialty> {


    List<Specialty> findByVetId(@Param("vetId") int vetId);

}