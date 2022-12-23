package com.jager.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExecutionParameter {

    private String name;
    private String type;
    private String value;
    private String identifyCharacter;
}
