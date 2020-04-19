package com.dobby.petclinic.web.controller;
import com.dobby.petclinic.domain.Owner;
import com.dobby.petclinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liguoqing
 * @date 2019-01-18
 */
@Controller
public class OwnerController {



    @Autowired
    private ClinicService clinicService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    /**
     * 跳转到 findOwners 页面
     */
    @GetMapping("/owners/find")
    public String initFindForm(ModelMap modelMap) {
        modelMap.put("owner", new Owner());
        return "owners/findOwners";
    }


    /**
     * 查询 owner 列表
     */
    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, ModelMap model) {
        List<Owner> results = new ArrayList<Owner>();
        // 输入参数为空，返回所有数据
        if (StringUtils.isEmpty(owner.getLastName())) {
            results = this.clinicService.findAllOwners();
        } else {
            // 根据 LastName 查询数据
            results = this.clinicService.findOwnerByLastName(owner.getLastName());
        }
        if (results.isEmpty()) {
            // 没有找到数据
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            //只找到一条数据，跳转的详情页面
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // 找到多条记录，跳转到列表页面
            model.put("selections", results);
            return "owners/ownersList";
        }
    }


    /**
     * 显示指定 id 的 owner 信息
     */
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Owner owner = clinicService.findOwnerById(ownerId);
        mav.addObject(owner);
        return mav;
    }



    @GetMapping("/owners/new")
    public String initCreationForm(ModelMap model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            this.clinicService.saveOrUpdateOwner(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }


    /**
     * 进入修改主人信息页面
     */
    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, ModelMap model) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        model.addAttribute(owner);
        return "owners/createOrUpdateOwnerForm";
    }


    /**
     * 修改主人信息
     */
    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            this.clinicService.saveOrUpdateOwner(owner);
            return "redirect:/owners/{ownerId}";
        }
    }

}
