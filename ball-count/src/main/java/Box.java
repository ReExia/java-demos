/**
 * Created by setsuna on 2017/5/18.
 */
public class Box {

    private int id;
    private String memberA;
    private String memberB;
    private String desc;

    public Box(int id, String memberA, String memberB) {
        this.id = id;
        this.memberA = memberA;
        this.memberB = memberB;
        if (memberA.equals("/") || memberB.equals("/")){
            if (id == 10) {
                this.desc = "normal";
            }else {
                this.desc = "spare";
            }
        }
        else if (memberA.contains("X")){
            if (id == 10) {
                this.desc = "normal";
            }else {
                this.desc = "strike";
            }
        }
        else {
                this.desc = "normal";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberA() {
        return memberA;
    }

    public void setMemberA(String memberA) {
        this.memberA = memberA;
    }

    public String getMemberB() {
        return memberB;
    }

    public void setMemberB(String memberB) {
        this.memberB = memberB;
    }

    public String getDesc() {
        return desc;
    }


}
