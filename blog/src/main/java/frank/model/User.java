package frank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//Lombok包下的注解
@Setter
@Getter
@ToString
public class User {

    private Integer id;
    private String name;
    private Date creatTime;
}
