package org.manu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private String companyName;
    private String companyUrl;
    private String phoneNumber;
    private String email;

    public void merge(Configuration prevConfig) {
        if (isEmpty(companyName)) {
            companyName = prevConfig.companyName;
        }
        if (isEmpty(companyUrl)) {
            companyUrl = prevConfig.companyUrl;
        }
        if (isEmpty(phoneNumber)) {
            phoneNumber = prevConfig.phoneNumber;
        }
        if (isEmpty(email)) {
            email = prevConfig.email;
        }
    }
}
