package org.manu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigApplier {

    public static void main(String[] args) throws IOException {
        // ConfigSearchContext context = new ConfigSearchContext("PAK", "Kerala", "TVM");
        ConfigSearchContext context = new ConfigSearchContext("India", "Kerala", "TVM");
        // ConfigSearchContext context = new ConfigSearchContext("Japan", "Kerala", "TVM");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            ConfigNode tree = mapper.readValue(new File("resources/config-tree.yml"), ConfigNode.class);
            // System.out.println(ReflectionToStringBuilder.toString(tree, ToStringStyle.MULTI_LINE_STYLE));

            Configuration config = tree.getConfig(context);
            System.out.println("Configuration = " + config);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
