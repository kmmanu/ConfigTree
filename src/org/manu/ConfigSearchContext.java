package org.manu;

import lombok.Data;

@Data
public class ConfigSearchContext {
    private final String countryName;
    private final String stateName;
    private final String districtName;
}
