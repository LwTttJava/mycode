package stream;

public class People{
    private String name;
    private Integer ager;
    private String teacherName;

    public People(String name, Integer ager,String teacherName) {
        this.name = name;
        this.ager = ager;
        this.teacherName = teacherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAger() {
        return ager;
    }

    public void setAger(Integer ager) {
        this.ager = ager;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("People{");
        sb.append("name='").append(name).append('\'');
        sb.append(", ager=").append(ager);
        sb.append(", teacherName='").append(teacherName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}