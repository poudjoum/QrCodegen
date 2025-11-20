package com.jumpytech.QrCodeGen.dto;

import java.util.List;

public record QrResponse(
		String payload,
	    List<String> files     // chemins relatifs pour front
		) {

}
