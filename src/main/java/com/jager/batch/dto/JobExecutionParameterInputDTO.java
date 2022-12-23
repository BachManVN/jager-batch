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
public class JobExecutionParameterInputDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3450749593175516730L;
	
	private String name;
    private String value;

}
