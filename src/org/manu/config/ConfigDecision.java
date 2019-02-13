package org.manu.config;

import org.manu.ConfigSearchContext;

import java.util.function.Function;

public enum ConfigDecision {
    COUNTRY(ConfigSearchContext::getCountryName),
    STATE(ConfigSearchContext::getStateName),
    DISTRICT(ConfigSearchContext::getDistrictName),
    UNDEFINED(context -> null);

    private Function<ConfigSearchContext, String> decisionFunction;

    ConfigDecision(Function<ConfigSearchContext, String> decisionFunction){
        this.decisionFunction = decisionFunction;
    }

    public String getText(ConfigSearchContext context){
        return decisionFunction.apply(context);
    }


}
