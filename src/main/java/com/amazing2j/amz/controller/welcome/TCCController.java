package com.amazing2j.amz.controller.welcome;

import com.amazing2j.amz.common.JsonResult;
import com.amazing2j.amz.entity.TCCEntity;
import com.amazing2j.amz.common.JsonResponse;
import com.amazing2j.amz.service.TCCService.TCCService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/TCC")
public class TCCController {

    final TCCService tccService;

    public TCCController(TCCService tccService) {
        this.tccService = tccService;
    }

    @PostMapping(value = "/create_TCC")
    public JsonResult createTCC(@RequestBody TCCEntity entity) {
        return JsonResponse.getSuccessResult(tccService.createTCC(entity));
    }

    @GetMapping(value = "/find_TCC_by_id/{id}")
    public JsonResult findTCCById(@PathVariable long id) {
        return JsonResponse.getSuccessResult(tccService.findTCCById(id));
    }

    @GetMapping(value = "/find_all_TCC")
    public JsonResult findAllTCC() {
        return JsonResponse.getSuccessResult(tccService.findAllTCC());
    }
}
