package org.manu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.manu.config.ConfigNode;
import org.manu.config.Configuration;

import java.io.File;
import java.io.IOException;

public class ConfigApplier {

    public static void main(String[] args) throws IOException {
        ConfigNode tree = readConfigTreeFromYaml();
        System.out.println("Searching for UST Kerala details");
        ConfigSearchContext context = new ConfigSearchContext("India", "Kerala", "TVM");
        Configuration config = tree.getConfig(context);
        System.out.println("Kerala Configuration = " + config);

        System.out.println("\nSearching for UST Japan details");
        ConfigSearchContext jpContext = new ConfigSearchContext("Japan", "Fukushima", "Fukushima City");
        Configuration jpConfig = tree.getConfig(jpContext);
        System.out.println("Japan Configuration = " + jpConfig);

    }

    private static ConfigNode readConfigTreeFromYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ConfigNode tree = mapper.readValue(new File("resources/config-tree.yml"), ConfigNode.class);
//        System.out.println("Config Tree = \n" + tree);
        return tree;
    }

}
