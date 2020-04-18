package com.dobby.petclinic.dao.mapper;


import com.dobby.petclinic.dao.util.MyMapper;
import com.dobby.petclinic.domain.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;
@Mapper
public interface PetMapper extends MyMapper<Pet> {

    @Select("select * from pets p where p.owner_id = #{ownerId}")
    Set<Pet> selectByOwnerId(Integer owneId);

}
