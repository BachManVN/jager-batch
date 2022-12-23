package com.jager.batch.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

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
public class StepExecutionDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6180595251943652387L;
	
	private long id;
    private String name;
    private String status;
    private long jobExecutionId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime lastUpdated;

    private long commitCount;
    private long readCount;
    private long filterCount;
    private long writeCount;
    private long readSkipCount;
    private long writeSkipCount;
    private long processSkipCount;
    private long rollbackCount;
    private String exitCode;
    private String exitMessage;

}
