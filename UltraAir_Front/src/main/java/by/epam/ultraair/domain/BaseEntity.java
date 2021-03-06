package by.epam.ultraair.domain;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public boolean isNew(){
        return this.id == null;
    }
}
