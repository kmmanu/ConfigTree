package org.manu.config;

import lombok.Data;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.manu.ConfigSearchContext;

import java.util.HashMap;
import java.util.Map;

@Data
public class ConfigNode {
    /**
     * The static text field tells what type of nodes are present in the <code>childNodes</code>
     * Eg. if configDecision = COUNTRY,  the child nodes would be India, USA, Japan etc.
     */
    private ConfigDecision configDecision;
    private Configuration configuration;
    private Map<String, ConfigNode> childNodes = new HashMap<>();

    public Configuration getConfig(ConfigSearchContext searchContext) {
        return getConfig(searchContext, this, new Configuration());
    }


    private static Configuration getConfig(ConfigSearchContext searchContext, ConfigNode configNode, Configuration prevConfig) {
        if (configNode == null) {
            return null;
        }

        Configuration configuration = configNode.getConfiguration();
        configuration.merge(prevConfig);

        String nextNodeName = configNode.getConfigDecision().getText(searchContext);
        if (nextNodeName == null
                || configNode.getChildNodes() == null
                || !configNode.getChildNodes().keySet().contains(nextNodeName)) {
            return configuration;
        }
        ConfigNode childNodeToSearch = configNode.getChildNodes().get(nextNodeName);
        return getConfig(searchContext, childNodeToSearch, configuration);
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, new MultilineRecursiveToStringStyle()).toString();
    }
}
