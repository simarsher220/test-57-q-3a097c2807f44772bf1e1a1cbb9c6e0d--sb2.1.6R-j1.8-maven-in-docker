package org.codejudge.sb.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true, doNotUseGetters=true)
@AllArgsConstructor
public class GenericResponseDto {
	private String status;
	private String reason;
	
	public GenericResponseDto(String status) {
		this.status = status;
	}
}
