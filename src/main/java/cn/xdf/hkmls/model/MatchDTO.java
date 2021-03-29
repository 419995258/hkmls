package cn.xdf.hkmls.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private String childId;
    private String simvalues;
    private ChildDTO childObj;
}
