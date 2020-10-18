package bean;

public class Teacher {
    private int id;
    private int num;
    private String name;
    private String sex;
    private String account;
    private String password;
    private int temp;
    private String time;
    private String place;

    public Teacher() {
    }

    public Teacher(int id, int num, String name, String sex, String account, String password, int temp, String time, String place) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.sex = sex;
        this.account = account;
        this.password = password;
        this.temp = temp;
        this.time = time;
        this.place = place;
    }

    @Override
    public String toString() {
        return "工号：" + num + "，姓名：" + name + "，性别：" + sex + "，温度：" + temp + "，打卡时间：" + time + "，打卡地点：" + place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
