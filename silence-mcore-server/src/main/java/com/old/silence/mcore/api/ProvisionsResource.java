package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.ProvisionFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.ProvisionMcoreView;

import java.util.List;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class ProvisionsResource {

    private final ProvisionFeignClient provisionFeignClient;

    public ProvisionsResource(ProvisionFeignClient provisionFeignClient) {
        this.provisionFeignClient = provisionFeignClient;
    }

    @GetMapping(value = "/provisions")
    public ApiResult<List<ProvisionMcoreView>> query(@RequestParam String scenarioCode, @RequestParam String channelCode) {
        return ApiResult.success(provisionFeignClient.findByScenarioCodeAndChannelCode(scenarioCode, channelCode, ProvisionMcoreView.class));
    }

}
