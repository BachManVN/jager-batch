package com.jager.batch.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDescriptionDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1808126525984331918L;
	
	private String name;
    private String description;
    private String lastExecutionStatus;
    private List<JobDescriptionParameterDTO> parameters = new ArrayList<>();
}
