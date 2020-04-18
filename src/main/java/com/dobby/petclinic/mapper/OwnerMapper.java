package com.dobby.petclinic.mapper;

import com.dobby.petclinic.common.MyMapper;
import com.dobby.petclinic.domain.Owner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OwnerMapper extends MyMapper<Owner> {

    @Select("SELECT * FROM owners WHERE last_name like #{lastName} ")
    List<Owner> findOwnerByLastName(@Param("lastName") String lastName);



}
