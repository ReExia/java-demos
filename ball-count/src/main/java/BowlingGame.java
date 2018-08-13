import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
      return gainResult(bowlingCode);
    }
    public  void addBox(String temp,List<Box> list){
        if(temp.length() > 1 ){
            list.add(new Box(list.size(),String.valueOf(temp.charAt(0)),String.valueOf(temp.charAt(1))));
        }else {
            if (temp.length() > 0){
                if(temp.equals("X")){
                    list.add(new Box(list.size(),String.valueOf(temp.charAt(0)),""));
                }else {
                    list.add(new Box(list.size(),String.valueOf(temp.charAt(0)),""));
                }
            }
        }
    }

    public  List<Box> gainBoxList(String str){
        List<Box> list = new ArrayList<Box>();
        String temp = "";
        do{

            if(list.size() == 0){
                temp = str.substring(0,str.indexOf("|"));
                addBox(temp,list);
                str = str.substring(str.indexOf("|")+1);
            }else {
                if (list.size() < 10){
                    temp = str.substring(0,str.indexOf("|"));
                    addBox(temp,list);
                    str = str.substring(str.indexOf("|")+1);
                }else{
                    temp = str.substring(str.indexOf("|")+1,str.length());
                    addBox(temp,list);
                    temp = "";
                }
            }
        }while(!temp.equals(""));
        return list;
    }

    public  int countStrike(List<Box> list,Box box){

        if (box.getId() == 9){
            Box box1 = list.get(box.getId()+1);
            return 10+score(box1.getMemberA())+score(box1.getMemberB());
        }else {
            Box box1 = list.get(box.getId()+1);
            if (box1.getDesc().equals("strike")){
                return 10+score(box1.getMemberA())+score(list.get(box.getId()+2).getMemberA());
            }
            else if(box1.getDesc().equals("spare")){
                return 20;
            }
            else {
                return 10+score(box1.getMemberA())+score(box1.getMemberB());
            }
        }
    }

    public  int countSpare(List<Box> list,Box box){
        Box box1 = list.get(box.getId()+1);
        return 10 + score(box1.getMemberA());
    }

    public  int countNormal(List<Box> list,Box box){
        return score(box.getMemberA())+score(box.getMemberB());
    }


    public  int score(String str){
        switch (str){
            case "X":return 10;
            case "-":return 0;
            case "/":return 0;
            case "" : return 0;
            default: return Integer.parseInt(str);
        }
    }

    public  int gainResult(String str){
        List<Box> list = gainBoxList(str);
        int result = 0 ;
        for(int i = 0 ; i < list.size() ; i++){
            Box box = list.get(i);
            if (i < 10){
                switch (box.getDesc()){
                    case "strike" : result += countStrike(list,box);break;
                    case "spare" : result += countSpare(list,box);break;
                    case "normal" : result += countNormal(list,box);break;
                }
            }
        }
        return result;
    }
}
