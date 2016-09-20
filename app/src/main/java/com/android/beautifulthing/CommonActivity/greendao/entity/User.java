package com.android.beautifulthing.CommonActivity.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by keshanpu on 16/9/19.
 */
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String password;

    @Generated(hash = 1691991404)
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
