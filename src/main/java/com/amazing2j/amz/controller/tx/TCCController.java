package com.amazing2j.amz.controller.tx;

import com.amazing2j.amz.model.dto.TCCRegisterPayload;
import com.amazing2j.amz.utils.JsonResult;
import com.amazing2j.amz.utils.JsonResponse;
import com.amazing2j.amz.service.impl.TCCService.TCCService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/TCC")
public class TCCController {

    final TCCService tccService;

    public TCCController(TCCService tccService) {
        this.tccService = tccService;
    }

    @PostMapping(value = "/register_TCC")
    public JsonResult createTCC(@RequestBody TCCRegisterPayload payload) {
        return JsonResponse.getSuccessResult(tccService.createTCC(payload));
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
