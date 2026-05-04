package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.content.api.vo.ProvisionView;
import com.old.silence.web.data.ProjectedPayloadType;

import java.util.List;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "provision", path = "/api/v1")
public interface ProvisionFeignClient {

    @GetMapping(value = "/provisions")
    <T> List<T> findByScenarioCodeAndChannelCode(@RequestParam String scenarioCode, @RequestParam String channelCode,
                                                 @ProjectedPayloadType(ProvisionView.class) Class<T> projectionType);
}
