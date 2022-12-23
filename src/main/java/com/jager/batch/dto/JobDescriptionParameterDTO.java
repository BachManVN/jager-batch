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
public class JobDescriptionParameterDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6020773732226528140L;
	private String name;
    private String type;
    private String defaultValue;
}
