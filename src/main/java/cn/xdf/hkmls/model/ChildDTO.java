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
public class ChildDTO {
    private String childId;
    private String location;
    private String age;
    private String character;
    private String gender;
    private List<String> hobby;
    private String hpGender;
    private String hopAges;
    private ResourceDTO resource;
    private ResourceDTO hpResource;
}
