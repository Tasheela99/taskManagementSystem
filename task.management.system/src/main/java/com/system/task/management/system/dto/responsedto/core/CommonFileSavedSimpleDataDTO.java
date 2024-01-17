package com.system.task.management.system.dto.responsedto.core;


import com.system.task.management.system.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFileSavedSimpleDataDTO implements SuperDTO {
    private String hash;
    private String directory;
    private String fileName;
    private String resourceUrl;
}
