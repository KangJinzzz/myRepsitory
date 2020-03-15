package frank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//Lombok包下的注解
@Setter
@Getter
@ToString
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Date createTime;
    private String userAccout;
}
