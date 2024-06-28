package com.entrega.util;

import com.entrega.model.dto.EntregaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static EntregaDTO readEntregaDTOFromJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), EntregaDTO.class);
    }
}
