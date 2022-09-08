package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.Teamservice;

public class TeamView {
        private NameListService listSvc=new NameListService();
        private Teamservice teamSvc=new Teamservice();

        public void enterMainMenu(){
            boolean loopflag=true;
            char menu=0;
            while(loopflag){
                if(menu!='1'){
                    listAllEmployees();
                }
                System.out.println("1：团队列表  2：添加团队成员  3：删除团队成员  4：退出 请选择（1-4）：");
                menu=TSUtility.readMenuSelection();
                switch (menu){
                    case '1':
                        getTeam();
                        break;
                    case '2':
                        addMember();
                        break;
                    case '3':
                        deleteMember();
                        break;
                    case '4':
                        System.out.println("是否要退出Y/N");
                        char isExit=TSUtility.readConfirmSelection();
                        if(isExit=='Y'){
                            loopflag=false;
                        }
                        break;

                }
            }

        }
        private void listAllEmployees(){
            System.out.println("------------------------开发团队调度软件---------------------------\n");
            Employee[] employees= listSvc.getAllEmployee();
            if(employees==null || employees.length==0){
                System.out.println("公司没有员工信息");
            }else{
                System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
                for(int i=0;i<employees.length;i++){
                    System.out.println(employees[i]);
                }

            }
            System.out.println("-------------------------------------------------------------------");

        }
        private void getTeam(){
            System.out.println("---------------团队成员列表-----------------\n");
            Programmer[] team=teamSvc.getTeam();
            if(team==null || team.length==0){
                System.out.println("开发团队没有成员！！");
            }else{
                System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
                for(int i=0;i<team.length;i++){
                    System.out.println(team[i].getDetailsForTeam());
                }
            }
            System.out.println("-------------------------------------------------------------------");
        }
        private void addMember(){

            System.out.println("-------------------添加团队成员---------------------");
            System.out.println("请输入要添加的员工ID");
            int id=TSUtility.readInt();
            try{
                Employee emp=listSvc.getEmployee(id);
                teamSvc.addMember(emp);
                System.out.println("添加成功");
                TSUtility.readReturn();
            }catch(TeamException e){
               System.out.println("添加失败，原因："+e.getMessage());
            }
        }
        private void deleteMember(){
            System.out.println("---------------------------删除团队成员--------------------------------");
            System.out.println("请输入要删除的员工ID");
            int memberid=TSUtility.readInt();

            System.out.println("确认是否删除Y/N");
            char isDelete=TSUtility.readConfirmSelection();
            if(isDelete=='N'){
                return;
            }
            try{
                teamSvc.removeMember(memberid);
                System.out.println("删除成功");
            }catch (TeamException e) {
                System.out.println("删除失败，原因："+e.getMessage());
            }
               TSUtility.readReturn(); //按回车继续

        }

    public static void main(String[] args) {
        TeamView view=new TeamView();
        view.enterMainMenu();
    }



}
