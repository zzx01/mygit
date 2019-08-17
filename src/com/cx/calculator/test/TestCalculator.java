



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
 * ���Ʋ㣬��awt���һ����������
 * ��ƿ�ܣ��¼��������ص㿼�ǹ��ܲ�����
 * ���ʵ�ּӼ��˳�
 * @author Administrator
 *
 */

public class TestCalculator {

	Frame frame=new Frame("���׼�����");
	Calculate c=new Calculate();

	
	public Button[] b=new Button[10];//�������Ͱ�ť
	
	public Button[] cal=new Button[8];//�������Ͱ�ť
	
	public TextField field=new TextField(30);//����һ�����Ϊ30���ı���
	public int flag=0;//�Ƿ����
	public int flag2=-1; //
	public int Key=-1;// �Ƿ� ���� = �� �ٲ����Ĺ���
	
	public String load1=new String();
	public String load2=new String();
	
	//����˵���  �˵�ֻ�ܼӵ�����ϣ����ܼӵ�����ϣ����ܲ���
	MenuBar mBar=new MenuBar();//�˵���   ������һ��  �˵���������
	Menu opMenu=new Menu("����");//�˵�
	Menu other=new Menu("����");
	MenuItem about=new MenuItem("����");//�˵���  �ɼ���  action�¼�  
	
	MenuItem reset=new MenuItem("����");
	
	MenuItem exit=new MenuItem("�˳�");
	
	
	
	public void init() {	
		load1 = load2 = null;//�洢���ֵ�����
        Key = -1;//���ܰ�ť��־λ
        flag2 = -1;//���ְ�ť��־λ
        flag = 0;//���ݴ洢��־λ
        field.setText("");
        
		Panel p=new Panel();
		
		for(int i=0;i<=9;i++) {
			b[i]=new Button(""+i);//�����ּ����δ�0��ʼ����
		}
		 for(int i=0;i<=9;i++) 
		 b[i].addActionListener(new NumListen());
		 
		//���ܼ�����
		cal[0]=new Button("+");
		cal[1]=new Button("-");
		cal[2]=new Button("*");
		cal[3]=new Button("/");
		cal[4]=new Button("=");
		cal[5]=new Button("�˸�");
		cal[6]=new Button(".");
		cal[7]=new Button("AC");
		
		//����¼�������
		cal[0].addActionListener(new CalListen());
		cal[1].addActionListener(new CalListen());
		cal[2].addActionListener(new CalListen());
		cal[3].addActionListener(new CalListen());
		cal[4].addActionListener(new CalListen());
		
		//�˸�
		
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
       
        
        
	//��˳�����ν�������ӵ����p��
		p.add(b[1]); p.add(b[2]); p.add(b[3]); p.add(cal[0]);
		p.add(b[4]); p.add(b[5]); p.add(b[6]); p.add(cal[1]);
		p.add(b[7]); p.add(b[8]); p.add(b[9]); p.add(cal[2]);
		p.add(cal[5]); p.add(b[0]); p.add(cal[6]); p.add(cal[3]);
		p.add(cal[7]);p.add(cal[4]);
		
		
		field.setEditable(false);//�ı������޸�
		frame.add(field,BorderLayout.NORTH);//���ı����Ա߽粼�ֵķ�ʽ������㣬����ӵ������
		p.setLayout(new GridLayout(5,4,4,4));//�������Ϊ���񲼾�
		frame.add(p);//�������ӵ������
		frame.setMenuBar(mBar);//���ò˵��������˵�����ӵ�����У�����Ҫ��ӵ������
		mBar.add(opMenu);//���˵���ӵ��˵�����
		mBar.add(other);//���˵���ӵ��˵�����
		other.add(about);//���˵�����ӵ��˵���
		opMenu.add(reset);//����
		opMenu.add(exit);//�˳�
		frame.setSize(350, 280);//���ÿ�ܴ�С
		frame.setVisible(true);//���ӻ�
		frame.addWindowListener(new WindowAdapter() {//�رմ���  �����ڲ���  ֱ��newһ��������  ʵ�����ⷽ��
				public void windowClosing(WindowEvent wevent) {
					System.exit(0);
				}
			});	
	}
	  
	   
	
   //�Լ���0-9���м������ص��������°�ťʱ����ֱ��set�ð�ť������Ҫ�������ӣ������õ��ı���������ݣ���һ��set��ȥ
	//ͨ�����ñ�־λ
	class  NumListen implements ActionListener{
		public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == b[0])
            {
             if(flag2 == -1)// 
                   field.setText(field.getText()+"0");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                   field.setText("0");//ֱ��set�ð�ť
                   flag2 = -1;
                }
            }
            else if(e.getSource() == b[1])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"1");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("1");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[2])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"2");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("2");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[3])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"3");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("3");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[4])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"4");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("4");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[5])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"5");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("5");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[6])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"6");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("6");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[7])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"7");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("7");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[8])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"8");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("8");
                    flag2 = -1;
                }
            }
            else if(e.getSource() == b[9])
            {
                if(flag2 == -1)
                    field.setText(field.getText()+"9");//�����õ��ı���������ݣ���һ��set��ȥ
                else
                {
                    field.setText("9");
                    flag2 = -1;
                }
            }
        }       
    }

	
	
	//���й�������������ص������ݵĴ洢���⣬������������
	class CalListen implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == cal[0])
            {
                if(flag == 0)
                {
                    load1 = field.getText();//��һ���Ƚ���һ�����ݴ����
                    flag = 1;//���ı�־λ
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
                    flag2=0;//��ť��־λ���ɽ������ӣ����������Ʋ���
                   
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
                    flag2=0;//��ť��־λ���ɽ������ӣ����������Ʋ���

                    
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
                    flag2=0;//��ť��־λ���ɽ������ӣ����������Ʋ���

                    
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
                    flag2=0;//��ť��־λ���ɽ������ӣ����������Ʋ���

                    
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
                    flag2 = 0;//��ť��־λ����������󣬽�����һ�μ��㣬��ֱ��set��ť����������޸İ�ť��־λ�Ļ��������ƴ�Ӳ���
                    load1 = field.getText();          
                    load2 = null;                
               }
               
            }        
            
        }
       
    }
	   
	   //������
			public static void main(String[] args) {	
				TestCalculator t=new TestCalculator();
				t.init();	     
			}
}
