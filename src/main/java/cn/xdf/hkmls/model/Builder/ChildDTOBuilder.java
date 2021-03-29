package cn.xdf.hkmls.model.Builder;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

import cn.xdf.hkmls.model.ChildDTO;
import cn.xdf.hkmls.model.ResourceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ChildDTOBuilder {
    public static ChildDTO of(ChildDTO childDTO){
        // 校验参数
        ResourceDTO resourceDTO = childDTO.getResource();
        List<String> books = resourceDTO.getBooks();
        Iterator<String> iterator = books.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(StringUtils.isBlank(str)){
                iterator.remove();
            }
        }
        List<String> toys = resourceDTO.getToys();
        iterator = toys.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(StringUtils.isBlank(str)){
                iterator.remove();
            }
        }

        resourceDTO = childDTO.getHpResource();
        books = resourceDTO.getBooks();
        iterator = books.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(StringUtils.isBlank(str)){
                iterator.remove();
            }
        }
        toys = resourceDTO.getToys();
        iterator = toys.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(StringUtils.isBlank(str)){
                iterator.remove();
            }
        }
        return childDTO;
    }

}
