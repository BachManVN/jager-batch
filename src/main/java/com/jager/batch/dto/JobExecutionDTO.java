package com.jager.batch.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExecutionDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
    private String jobName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime lastUpdated;

    private String status;
    private String exitCode;
    private String exitMessage;

    private List<JobExecutionParameterDTO> parameters = new ArrayList<>();
}
