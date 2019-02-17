package com.zerofiltre.snapanonyme.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends SuperClazz {

    @OneToMany
    private List<Article> articles;


}
