package wzy;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;

import java.util.UUID;

public class Person {
    @JSONField(name = "uuid")
    private String uuid;//主键
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "phone")
    private String phoneNumber;//手机
    @JSONField(name = "tel")
    private String tel;//座机
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "qq")
    private String QQ;
    @JSONField(name = "gender")
    private Gender gender; //性别
    @JSONField(name = "company")
    private String Company;
    @JSONField(name = "office")
    private String office;//职业

    public Gender getGender() {
        return gender;
    }

    public String getCompany() {
        return Company;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getOffice() {
        return office;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getQQ() {
        return QQ;
    }

    public String getTel() {
        return tel;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        if (this.uuid != null) {
            this.uuid = uuid;
        }
    }

    public Person() {
        this.uuid = UUID.randomUUID().toString();
        this.name = "<空>";
    }

    public String toString() {
        if (gender != null) {
            return name + " " + phoneNumber + " " + (gender.equals(Gender.male) ? "男" : "女");
        } else {
            return name + " " + phoneNumber + " 未知";
        }
    }
}
