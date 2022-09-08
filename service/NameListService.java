package service;

import domain.*;

import static service.Data.*;


public class NameListService { //将DATA中的数据封装到employees数组中
    private Employee[] employees;

    //给employee数组及元素初始化
    public NameListService(){
        employees=new Employee[EMPLOYEES.length];
        for(int i=0;i<employees.length;i++){
            int type=Integer.parseInt(EMPLOYEES[i][0]);

            //获取employee的基本信息
            int id=Integer.parseInt(EMPLOYEES[i][1]);
            String name=EMPLOYEES[i][2];
            int age=Integer.parseInt(EMPLOYEES[i][3]);
            double salary=Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;

            switch(type){
                case EMPLOYEE:
                    employees[i]=new Employee(id,name,age,salary);
                    break;
                case PROGRAMMER:
                    equipment=createEquipment(i);

                    employees[i]=new Programmer(id,name,age,salary,equipment);
                    break;
                case DESIGNER:
                    equipment=createEquipment(i);
                    bonus=Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i]=new Designer(id,name,age,salary,equipment,bonus);
                    break;
                case ARCHITECT:
                    equipment=createEquipment(i);
                    bonus=Double.parseDouble(EMPLOYEES[i][5]);
                    stock=Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i]=new Architect(id,name,age,salary,equipment,bonus,stock);
                    break;
            }
        }

    }

    //获取指定index员工的equipment
    private Equipment createEquipment(int index) {
        int key=Integer.parseInt(EQUIPMENTS[index][0]);

        String modelorname=EQUIPMENTS[index][1];
        switch (key){
            case PC: //21
                String display=EQUIPMENTS[index][2];
                return new PC(modelorname,display);
            case NOTEBOOK://22
                double price=Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelorname,price);
            case  PRINTER://23
                String type=EQUIPMENTS[index][2];
                return new Printer(modelorname,type);
        }
      return null;
    }

    //获取当前所有员工
    public Employee[] getAllEmployee(){
        return employees;
    }

    //获取指定ID的员工对象
    public Employee getEmployee(int id) throws TeamException {
        for(int i=0;i<employees.length;i++){
            if(employees[i].getId()==id){
                return employees[i];
            }
        }
        throw new TeamException("找不到指定员工");

    }
}
