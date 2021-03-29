package cn.xdf.hkmls.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HpListDTO {
    private String childId;
    private String simvalues;
    private List<String> values;
    private ChildDTO childObj;
}
