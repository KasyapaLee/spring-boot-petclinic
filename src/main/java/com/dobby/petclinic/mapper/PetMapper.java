package com.dobby.petclinic.mapper;

import com.dobby.petclinic.common.MyMapper;
import com.dobby.petclinic.domain.Owner;
import com.dobby.petclinic.domain.Pet;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface PetMapper extends MyMapper<Pet> {


    @Select("select * from pets p where p.owner_id = #{ownerId}")
    Set<Pet> selectByOwnerId(Integer owneId);

}
