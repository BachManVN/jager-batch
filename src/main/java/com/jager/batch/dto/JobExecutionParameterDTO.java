package com.jager.batch.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExecutionParameterDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2521243246772357454L;
	private String name;
    private String type;
    private String value;
    private String identifyCharacter;

}
