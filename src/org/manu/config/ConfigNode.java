package org.manu.config;

import lombok.Data;
import org.manu.ConfigSearchContext;

import java.util.HashMap;
import java.util.Map;

@Data
public class ConfigNode {
    private ConfigDecision configDecision;
    private Configuration configuration;
    private Map<String, ConfigNode> childNodes = new HashMap<>();

    public Configuration getConfig(ConfigSearchContext searchContext) {
        return getConfig(searchContext, this);
    }


    private static Configuration getConfig(ConfigSearchContext searchContext, ConfigNode configNode) {
        if (configNode == null) {
            return null;
        }

        Configuration configuration = configNode.getConfiguration();

        String nextNodeName = configNode.getConfigDecision().getText(searchContext);
        if (nextNodeName == null
                || configNode.getChildNodes() == null
                || !configNode.getChildNodes().keySet().contains(nextNodeName)) {
            return configuration;
        }
        ConfigNode childNodeToSearch = configNode.getChildNodes().get(nextNodeName);
        return getConfig(searchContext, childNodeToSearch);
    }
}
