package com.dobby.petclinic.dao.mapper;

import com.dobby.petclinic.dao.util.MyMapper;
import com.dobby.petclinic.domain.Owner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author liguoqing
 */
public interface OwnerMapper extends MyMapper<Owner> {

    @Select("SELECT * FROM owners WHERE last_name like #{lastName} ")
    List<Owner> findOwnerByLastName(@Param("lastName") String lastName);



}
