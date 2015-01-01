package com.argo.web.controllers.acl;

import com.argo.acl.SysResource;
import com.argo.acl.service.SysResourceService;
import com.argo.core.exception.EntityNotFoundException;
import com.argo.core.web.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by $User on 2014-10-08 09:58.
 */

@Controller
@RequestMapping("/a/acl/sys/resource")
public class SysResourceController extends AclBaseController {

    @Autowired
    private SysResourceService sysResourceService;

    @RequestMapping(value="all", method = RequestMethod.GET)
    public ModelAndView all(ModelAndView model){

        List<SysResource> list = sysResourceService.findAll();
        model.setViewName("/admin/acl/sys/resource/all");
        model.addObject("roles", list);

        return model;
    }

    @RequestMapping(value="select", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponse<SysResource> select(ModelAndView model, JsonResponse<SysResource> actResponse){

        List<SysResource> list = sysResourceService.findAll();
        actResponse.getData().addAll(list);

        return actResponse;
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView model){

        model.setViewName("/admin/acl/sys/resource/add");
        model.addObject("res", new SysResource());

        return model;
    }

    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
    public ModelAndView view(ModelAndView model, @PathVariable Long id){

        try {
            SysResource res = sysResourceService.findById(id);
            model.addObject("res", res);
            model.setViewName("/admin/acl/sys/resource/view");
        } catch (EntityNotFoundException e) {
            RedirectView view = new RedirectView("/admin/acl/sys/resource/404");
            model.setView(view);
        }

        return model;
    }

    @RequestMapping(value="create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponse postCreate(@Valid DetailForm form, BindingResult result, JsonResponse actResponse) throws Exception {

        if (result.hasErrors()){
            this.wrapError(result, actResponse);
            return actResponse;
        }

        SysResource res = new SysResource();
        res.setName(form.getName());
        res.setTitle(form.getTitle());
        res.setKindId(form.getKindId());
        Long id = sysResourceService.add(res);
        res.setId(id.intValue());

        actResponse.add(res);

        return actResponse;
    }

    @RequestMapping(value="save/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponse postSave(@Valid DetailForm form, BindingResult result, @PathVariable Long id, JsonResponse actResponse) throws Exception {

        if (result.hasErrors()){
            this.wrapError(result, actResponse);
            return actResponse;
        }

        SysResource res = new SysResource();
        res.setName(form.getName());
        res.setTitle(form.getTitle());
        res.setKindId(form.getKindId());
        res.setId(id.intValue());

        sysResourceService.update(res);

        return actResponse;
    }

    @RequestMapping(value="remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponse postRemove(@PathVariable Long id, JsonResponse actResponse) throws Exception {

        if (id != null) {
            sysResourceService.remove(id);
        }

        return actResponse;
    }
}