



package com.cx.calculator.test;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.cx.calculator.manager.Calculate;

/**
 * 控制层，用awt设计一个计算器，
 * 设计框架，事件监听，重点考虑功能操作，
 * 如何实现加减乘除
 * @author Administrator
 *
 */

public class TestCalculator {

	Frame frame=new Frame("简易计算器");
	Calculate c=new Calculate();

	
	public Button[] b=new Button[10];//数字类型按钮
	
	public Button[] cal=new Button[8];//功能类型按钮
	
	public TextField field=new TextField(30);//定义一个宽度为30的文本框
	public int flag=0;//是否清空
	public int flag2=-1; //
	public int Key=-1;// 是否 按了 = 后 再操作的功能
	
	public String load1=new String();
	public String load2=new String();
	
	//定义菜单栏  菜单只能加到框架上，不能加到面板上，不能布局
	MenuBar mBar=new MenuBar();//菜单条   长长的一行  菜单条不监听
	Menu opMenu=new Menu("操作");//菜单
	Menu other=new Menu("其他");
	MenuItem about=new MenuItem("关于");//菜单项  可监听  action事件  
	
	MenuItem reset=new MenuItem("重置");
	
	MenuItem exit=new MenuItem("退出");
	
	
	
	public void init() {	
		load1 = load2 = null;//存储数字的容器
        Key = -1;//功能按钮标志位
        flag2 = -1;//数字按钮标志位
        flag = 0;//数据存储标志位
        field.setText("");
        
		Panel p=new Panel();
		
		for(int i=0;i<=9;i++) {
			b[i]=new Button(""+i);//将数字键依次从0开始命名
		}
		 for(int i=0;i<=9;i++) 
		 b[i].addActionListener(new NumListen());
		 
		//功能键命名
		cal[0]=new Button("+");
		cal[1]=new Button("-");
		cal[2]=new Button("*");
		cal[3]=new Button("/");
		cal[4]=new Button("=");
		cal[5]=new Button("退格");
		cal[6]=new Button(".");
		cal[7]=new Button("AC");
		
		//添加事件监听器
		cal[0].addActionListener(new CalListen());
		cal[1].addActionListener(new CalListen());
		cal[2].addActionListener(new CalListen());
		cal[3].addActionListener(new CalListen());
		cal[4].addActionListener(new CalListen());
		
		//退格
		
		cal[5].addActionListener(new CalListen());{
			
		}
		
		
		cal[5].addActionListener(e->
        {
            field.setText(field.getText().substring(0,field.getText().length()-1));
        });
        cal[6].addActionListener(e->
        {
            field.setText(field.getText()+".");
        });
	  
        exit.addActionListener(e->System.exit(0));
        reset.addActionListener(e->
        {
            load1 = load2 = null;
            Key = -1;
            flag2 = -1;
            flag = 0;
            field.setText("");
        });
        
        cal[7].addActionListener(e->
        {
            load1 = load2 = null;
            Key = -1;
            flag2 = -1;
            flag = 0;
            field.setText("");
        });
       
        
        
	//按顺序依次将按键添加到面板p中
		p.add(b[1]); p.add(b[2]); p.add(b[3]); p.add(cal[0]);
		p.add(b[4]); p.add(b[5]); p.add(b[6]); p.add(cal[1]);
		p.add(b[7]); p.add(b[8]); p.add(b[9]); p.add(cal[2]);
		p.add(cal[5]); p.add(b[0]); p.add(cal[6]); p.add(cal[3]);
		p.add(cal[7]);p.add(cal[4]);
		
		
		field.setEditable(false);//文本框不能修改
		frame.add(field,BorderLayout.NORTH);//将文本框以边界布局的方式置于最顶层，并添加到面板中
		p.setLayout(new GridLayout(5,4,4,4));//将面板设为网格布局
		frame.add(p);//将面板添加到框架中
		frame.setMenuBar(mBar);//设置菜单栏，将菜单栏添加到框架中，不需要添加到面板中
		mBar.add(opMenu);//将菜单添加到菜单栏中
		mBar.add(other);//将菜单添加到菜单栏中
		other.add(about);//将菜单项添加到菜单中
		opMenu.add(reset);//重置
		opMenu.add(exit);//退出
		frame.setSize(350, 280);//设置框架大小
		frame.setVisible(true);//可视话
		frame.addWindowListener(new WindowAdapter() {//关闭窗口  匿名内部类  直接new一个适配器  实现任意方法
				public void windowClosing(WindowEvent wevent) {
					System.exit(0);
				}
			});	
	}
	  
	   
	
   //对键盘0-9进行监听，重点解决当按下按钮时，是直接set该按钮，还是要进行连接，即先拿到文本框里的内容，再一起set进去
	//通过设置标志位
	class  NumListen implements ActionListener{
		public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == b[0])
            {
             if(flag2 == -1)// 
                   field.setText(field.getText()+"0");//即先拿到文本框里的内容，再一起set进去
                else
                {
                   field.setText("0");//直接set该按钮
                   flag2 = -1;
                }
            }
            else if(e.getSource() == b[1])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"1");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("1");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[2])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"2");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("2");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[3])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"3");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("3");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[4])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"4");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("4");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[5])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"5");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("5");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[6])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"6");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("6");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[7])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"7");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("7");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[8])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"8");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("8");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[9])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"9");//即先拿到文本框里的内容，再一起set进去
                else
                {
                    field.setText("9");
                    flag2 = -1;
                }
            }
        }       
    }

	
	
	//进行功能运算监听，重点解决数据的存储问题，设置两个容器
	class CalListen implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == cal[0])
            {
                if(flag == 0)
                {
                    load1 = field.getText();//第一次先将第一个数据存进来
                    flag = 1;//更改标志位
                    flag2 = Key = 0;
                    field.setText("");
                    
                }
                
                else if(flag == 1)
                {
                    load2 = field.getText();
                    field.setText(String.valueOf(c.add(Double.parseDouble(load1), Double.parseDouble(load2))));
                    System.out.println(load1);
                    load1 = field.getText();
                    load2 = null;
                    flag2=0;//按钮标志位，可进行连加，连减等类似操作
                   
                }
            }
            else if(e.getSource() == cal[1])
            {
                if(flag == 0)
                {
                    load1 = field.getText();
                    flag = 1;
                    flag2 = Key = 1;
                    field.setText("");
                }
                else if(flag == 1)
                {
                    load2 = field.getText();
                    field.setText(String.valueOf(c.sub(Double.parseDouble(load1), Double.parseDouble(load2))));
                    load1 = field.getText();
                    load2 = null;
                    flag2=0;//按钮标志位，可进行连加，连减等类似操作

                    
                }
            }
            else if(e.getSource() == cal[2])
            {
                if(flag == 0)
                {
                    load1 = field.getText();
                    flag = 1;
                    flag2 = Key = 2;
                    field.setText("");
                }
                else if(flag == 1)
                {
                    load2 = field.getText();
                    field.setText(String.valueOf(c.multi(Double.parseDouble(load1), Double.parseDouble(load2))));
                    load1 = field.getText();
                    load2 = null;
                    flag2=0;//按钮标志位，可进行连加，连减等类似操作

                    
                }
            }
            else if(e.getSource() == cal[3])
            {
                if(flag == 0)
                {
                    load1 = field.getText();
                    flag = 1;
                    flag2 = Key = 3;
                }
                else if(flag == 1)
                {
                    load2 = field.getText();
                    field.setText(String.valueOf(c.dev(Double.parseDouble(load1), Double.parseDouble(load2))));
                    load1 = field.getText();
                    load2 = null;
                    flag2=0;//按钮标志位，可进行连加，连减等类似操作

                    
                }
            }
            else if(e.getSource() == cal[4])
            {	
                if(load1 != null && load2 == null)
                {
                    load2 = field.getText();
                    
                    if(Key == 0)
                  
                    	field.setText(String.valueOf(c.add(Double.parseDouble(load1), Double.parseDouble(load2))));
                    
                    else if(Key == 1)
                       field.setText(String.valueOf(c.sub(Double.parseDouble(load1), Double.parseDouble(load2))));
                    else if(Key == 2)
                        field.setText(String.valueOf(c.multi(Double.parseDouble(load1), Double.parseDouble(load2))));
                    else if(Key == 3)
                       field.setText(String.valueOf(c.dev(Double.parseDouble(load1), Double.parseDouble(load2))));
                
                    Key = -1;
                    flag = 0;
                    flag2 = 0;//按钮标志位，等于输出后，进行下一次计算，需直接set按钮，而如果不修改按钮标志位的话。会进行拼接操作
                    load1 = field.getText();          
                    load2 = null;                
               }
               
            }        
            
        }
       
    }
	   
	   //主方法
			public static void main(String[] args) {	
				TestCalculator t=new TestCalculator();
				t.init();	     
			}
}
