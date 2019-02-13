package org.manu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigApplier {

    public static void main(String[] args) throws IOException {

//        printYamel();
//        writeJson();

//        ConfigSearchContext context = new ConfigSearchContext("PAK", "Kerala", "TVM");
        ConfigSearchContext context = new ConfigSearchContext("India", "Kerala", "TVM");
//        ConfigSearchContext context = new ConfigSearchContext("Japan", "Kerala", "TVM");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            ConfigNode tree = mapper.readValue(new File("resources/config-tree.yml"), ConfigNode.class);
//            System.out.println(ReflectionToStringBuilder.toString(tree, ToStringStyle.MULTI_LINE_STYLE));

            Configuration config = tree.getConfig(context);
            System.out.println("Configuration = " + config);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void writeJson() throws IOException {
        ConfigNode tree = createTree();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new FileOutputStream("resources/config-tree.json"), tree);
    }

    private static void printYamel() {
        ConfigNode tree = createTree();

        ObjectMapper ymlObjectMapper = new ObjectMapper(new YAMLFactory());

        String yamlString = null;
        try {
            yamlString = ymlObjectMapper.writeValueAsString(tree);
            System.out.println(yamlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // normally, rethrow exception here - or don't catch it at all.
        }
    }

    private static ConfigNode createTree() {
        ConfigNode countryNode = new ConfigNode();
        countryNode.setConfigDecision(ConfigDecision.COUNTRY);
//        countryNode.setName("Parent Config");
        countryNode.setConfiguration(Configuration.builder().companyName("UST").companyUrl("ust.co").email("ust@com").phoneNumber("1234").build());

        ConfigNode india = new ConfigNode();
        india.setConfigDecision(ConfigDecision.STATE);
//        india.setName("IND");
        india.setConfiguration(Configuration.builder().companyName("UST-IND").companyUrl("ust.in").email("ust@in").phoneNumber("1234").build());

        Map<String, ConfigNode> childNodes = new HashMap<>();
        childNodes.put("IND", india);
        countryNode.setChildNodes(childNodes);
        return countryNode;
    }


}
