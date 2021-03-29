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
public class ViewVO {

    private ChildDTO childObj;
    private List<MatchDTO> countrysideList;
    private List<MatchDTO> cityList;
}
