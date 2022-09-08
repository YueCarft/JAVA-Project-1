package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class Teamservice {
    private static int counter=1; //给menberid赋值
    private final int MAX_MENBER=5;//限制团队人数
    private Programmer[] team=new Programmer[MAX_MENBER];//保存开发团队成员
    private int total;//记录实际的开发团队的人数

    public Teamservice() {
    }

    //获取开发团队的所有成员
    public Programmer[] getTeam(){
        Programmer[] team=new Programmer[total];
        for(int i=0;i<team.length;i++){
            team[i]=this.team[i];
        }
        return team;
    }

    //增加指定的开发团队的成员
    public void addMember(Employee e) throws TeamException {
        //成员已满无法加入
        if(total>=MAX_MENBER){
            throw new TeamException("成员已满无法加入");
        }
        //该成员不是开发人员，无法添加
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //员工已经在团队中,....
        if(isExist(e)){
            throw  new TeamException("该员工已经在团队中");
        }
        //员工在休假无法加入
        Programmer p=(Programmer) e; //不会出现异常
        if(p.getStatus().getNAME().equals("BUSY")){
            throw new TeamException("该员工已经是某团队成员");
        }else if(p.getStatus().getNAME().equals("VOCATION")){
            throw new TeamException("该员工正在休假");
        }
        //团队中只能有一名架构师，两个设计师，三个程序员
        int numOfArch=0,numOfDes=0,numOfPro=0;
        for(int i=0;i<total;i++){
            if(team[i] instanceof Architect){
                numOfArch++;
            } else if (team[i] instanceof Designer) {
                numOfDes++;
            } else if (team[i] instanceof Programmer) {
                numOfPro++;
            }
        }
        if(p instanceof Architect){
            if(numOfArch>=1){
                throw new TeamException("团队至多有一名架构师");
            }
        } else if (p instanceof Designer) {
            if(numOfDes>=2){
                throw new TeamException("团队至多有两名设计师");
            }
        } else if (p instanceof Programmer) {
            if(numOfPro>=3){
                throw new TeamException("团队至多有三名程序员");
            }
        }

        team[total++]=p;          //团队人员加一
        p.setStatus(Status.BUSY);//成员转态转变
        p.setMemberId(counter++);//memberID加一
    }



     //判断指定 的员工 是否存在于开发团队中
    private boolean isExist(Employee e) {
        for(int i=0;i<total;i++){
            if(team[i].getId()==e.getId()){
                return true;
            }
        }
        return false;
    }

    //删除开发团队的成员
    public void removeMember(int memberID) throws TeamException {
        int i=0;
        for(;i<total;i++){
            if(team[i].getMemberId()==memberID){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //没找到指定的ID
        if(i==total){
            throw new TeamException("找不到指定menberid的员工，删除失败");
        }
        //后一元素覆盖前一元素
        for(int j=i+1;j<total;j++){
            team[j-1]=team[j];
        }
        team[total-1]=null;
        total--;
    }
}
