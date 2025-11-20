package com.jumpytech.QrCodeGen.dto;

import java.util.List;

public record QrRequest( 
	String payload,
    List<Integer> sizes,   // ex: [256,512,1024]
    String prefix,         // ex: "client-123"
    String ecl      // "L","M","Q","H"
    
) {}

