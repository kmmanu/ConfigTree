package org.manu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private String companyName;
    private String companyUrl;
    private String phoneNumber;
    private String email;
}
