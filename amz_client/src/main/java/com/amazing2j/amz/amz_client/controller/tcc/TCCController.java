package com.amazing2j.amz.amz_client.controller.tcc;

import com.amazing2j.amz.amz_client.model.dto.TCCRegisterPayload;
import com.amazing2j.amz.amz_client.service.tcc.TCCService;
import com.amazing2j.amz.amz_server.utils.JsonResponse;
import com.amazing2j.amz.amz_server.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/TCC")
public class TCCController {

    final TCCService tccService;

    public TCCController(TCCService tccService) {
        this.tccService = tccService;
    }

    @PostMapping(value = "register_TCC")
    public JsonResult registerTCC(@RequestBody TCCRegisterPayload tccRegisterPayload) {
        return JsonResponse.getSuccessResult(tccService.createTCC(tccRegisterPayload));
    }

    @GetMapping(value = "get_all_TCC")
    public JsonResult getAllTCC() {
        return JsonResponse.getSuccessResult(tccService.findAllTCC());
    }
}
