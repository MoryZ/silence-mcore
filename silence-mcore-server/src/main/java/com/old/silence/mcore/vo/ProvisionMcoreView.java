package com.old.silence.mcore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.old.silence.content.domain.enums.ProvisionChannel;
import com.old.silence.content.domain.enums.ProvisionScenario;

import java.math.BigInteger;

/**
 * @author moryzang
 */
public interface ProvisionMcoreView {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigInteger getId();

    String getName();

    String getUrl();

    ProvisionScenario getScenarioCode();

    ProvisionChannel getChannelCode();
}
