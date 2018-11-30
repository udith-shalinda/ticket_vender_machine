/*
SE/2016/047
J.A.D.U.Shalinda
*/


import java.util.*;
import java.io.*;


public class tvm_withoop {
	static String state = "No";
	//add Details to a file;
	void addList(Object obj ,ArrayList<Object> list,String filepath){
		objectIo objectio = new objectIo();
		list.add(obj);
		objectio.writeobjectToFile(obj,filepath);
	}
	void printList(ArrayList<Object> list,String filepath){
		objectIo objectio = new objectIo();
		for(Object ob : list){
			//ob = null;
			ob =  (Details)objectio.ReadObjectFromFile(filepath);
			System.out.println(ob);
		}
	}
	
    public static void main(String[] args)throws IOException{
		Scanner scan = new Scanner(System.in);
		ArrayList<Object> list = new ArrayList();
		Details details = new Details();	  
		Input_moneyNbalance im = new Input_moneyNbalance();			
		money_input_handle money_input = new money_input_handle();  
		tvm_withoop tv = new tvm_withoop();
		objectIo objectio = new objectIo();
		String filePathmoney = "D:\\java_tests\\money";
		String filePathDetails = "D:\\java_tests\\details";
		
		objectio.writeobjectToFile(money_input,filePathmoney);              //writting money_input_handle to a file;
		money_input_handle moneyMoney = (money_input_handle)objectio.ReadObjectFromFile(filePathmoney);     //readObject
		System.out.println(moneyMoney);
		
		while(money_input.money_in_the_bank()==0){
			System.out.println("Not ready\nNeed to refill\nEnter 1 to refill\nAnother value to exit");
			if(scan.nextInt()==1){
				money_input.put_money_machine();
				objectio.writeobjectToFile(money_input,filePathmoney);         //writting money_input_handle to a file;
			}else{
				System.exit(0);
			}
			
		}
		
		System.out.println("\nReady");
		System.out.println("Enter 1 to use\nEnter 2 to Admin\nEnter 3 to exit\nEnter 4 to complain adout service");
		int choice = scan.nextInt();
		switch(choice){
			case 1:
					
				details.passenger_details();
				int calculate_price1 = details.calculate_price();
				System.out.println("Your ticket price is : "+ calculate_price1);
				int sum_paid_money = money_input.sum_of_input_money(calculate_price1);  //should pass the calculate_price;
				int calculate_balace1 = im.calculate_balace(calculate_price1,sum_paid_money);
				System.out.println("Your balance is : "+ calculate_balace1);
				money_input.remove_money(calculate_balace1);
				//tv.addList(details,list,filePathDetails);
				//writting money_input_handle to a file;
				objectio.writeobjectToFile(money_input,filePathmoney);
				//money_input.print_money();		//printing the rest money;					
				break;
			case 2:
				System.out.println("1 : refill\n2 : see the remain money\n3 : see the total\n4 : see the total transanctions");
				int choice1 = scan.nextInt();
				if(choice1==1){
					money_input.put_money_machine();
					//writeObject
					objectio.writeobjectToFile(money_input,filePathmoney);
				}else if(choice1==2){
					money_input.print_money();

				}else if(choice1==3){
					money_input.totalMoney();
				}else if(choice1==4){
					tv.printList(list,filePathDetails);  //have to find a solution;
				}
				break;
			case 3:
				System.exit(0);
				break;
				
			case 4:
				
				System.out.println("Enter your complain here");
				String one = scan.next();
				String complain = scan.nextLine();
				break;
				
		}
		
		tv.main(args);
      
    }
    
}


class Details implements Serializable{

		int destination;
		int adults;
		int children;
		int price;
	
		
    
        public void passenger_details(){
            Scanner scan = new Scanner(System.in);
            
            do{
                System.out.print("Enter your  destination station as 1 to 20 : ");
                int destination = scan.nextInt();
				this.destination = destination;
            }while(destination > 20);   
            
            System.out.print("\nHow many adults : ");
            adults = scan.nextInt();
			this.adults = adults;
            System.out.print("\nHow many children : ");
            children = scan.nextInt();
			this.children = children;
        }
        public int calculate_price(){
            price = destination*adults*20 + destination*children*10; 
            this.price = price;
            return price;
        }
		public String toString(){
			return "destination :\t adults : \t children : \t price : ";
		}
		
}

class Input_moneyNbalance {
		 int inputMoney;
		 int balance;
	
	int check_input_money(int price){
		Scanner scanner = new Scanner(System.in);
 
		do{
           System.out.print("\nEnter the money : ");
           int inputMoney = scanner.nextInt();
		   this.inputMoney = inputMoney;
		}while(inputMoney < price);
			return inputMoney;
	}
	public int calculate_balace(int price,int inputMoney){

            balance = inputMoney - price; 
            return balance;
        }
}

class money_input_handle implements Serializable{
	
	
		static int thousand;
		static int fivehunderd;
		static int hundred;
		static int fifty;
		static int twenty;
		static int ten;
		static int five;
		static int two;
		static int one;
		static int total = 0;
	
		
	
		//money_input_handle(){}//can call without 9 parameters
	/*money_input_handle(int thousand,int fivehunderd,int hundred,int fifty,int twenty,int ten,int five,int two,int one){
		this.thousand = thousand;
		this.fivehunderd = fivehunderd;
		this.hundred = hundred;
		this.fifty = fifty;
		this.twenty = twenty;
		this.ten = ten;
		this.five = five;
		this.two = two;
		this.one = one;	
	}*/
	
	
	void print_money(){
		System.out.println("thousand = "+thousand+ "\nfivehunderd = "+fivehunderd+"\nhundred = "+hundred+"\nfifty = "+fifty+ "\ntwenty = "+twenty+"\nten = "+ten+"\nfive = "+five+"\ntwo = "+two+"\none = "+one);
	}
	
	int sum_of_input_money(int calculate_price){
		money_input_handle money_input = new money_input_handle();  
		Scanner scan = new Scanner(System.in);
		int sum;
		int arr[] = new int[9];
		String[] str = {"thousand" , "fivehundred","hundred","fifty","twenty","ten","five","two","one"};
			for(int i = 0; i < 9; i++){
				System.out.println(str[i]);
				arr[i] = scan.nextInt(); 
			}
			sum =arr[0]*1000 + arr[1]*500 + arr[2]*100+arr[3]*50+arr[4]*20+arr[5]*10+arr[6]*5+arr[7]*2+arr[8];
			
			if(calculate_price > sum){
				money_input.sum_of_input_money(calculate_price);
			}else{
				money_input.add_money(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8]);	
			}
		total+= calculate_price;
		return sum;
	}
	void put_money_machine(){
		money_input_handle money_input = new money_input_handle();  
		Scanner scan = new Scanner(System.in);
		int arr[] = new int[9];
		String[] str = {"thousand" , "fivehundred","hundred","fifty","twenty","ten","five","two","one"};
			for(int i = 0; i < 9; i++){
				System.out.println(str[i]);
				arr[i] = scan.nextInt(); 
			}
			money_input.add_money(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8]);
		
	}
	
	void add_money(int thousand2,int fivehunderd2,int hundred2,int fifty2,int twenty2,int ten2,int five2,int two2,int one2){
		money_input_handle money_input = new money_input_handle();
		thousand += thousand2;
		fivehunderd += fivehunderd2;
		hundred += hundred2;
		fifty += fifty2;
		twenty += twenty2;
		ten += ten2;
		five += five2;
		two +=two2;
		one += one2;
		
		
	}
	
	static int sum=0;
	void remove_money(int balance){
		money_input_handle money_input = new money_input_handle();
		while(balance>=1000){
			sum+=1000;
			thousand-=1;
			balance-=1000;
		}
		while(balance >=500){
			sum+=500;
			fivehunderd-=1;
			balance-=500;
		}
		while(balance>=100){
			sum+=100;
			hundred-=1;
			balance-=100;
		}
		while(balance >=50){
			sum+=50;
			fifty-=1;
			balance-=50;
		}
		while(balance >=20){
			sum+=20;
			twenty-=1;
			balance-=20;
		}
		while(balance>=10){
			sum+=10;
			ten-=1;
			balance-=10;
		}
		while(balance >=5){
			sum+=5;
			five-=1;
			balance-=5;
		}
		while(balance >=2){
			sum+=2;
			two-=1;
			balance-=2;
		}
		while(balance>=1){
			sum+=1;
			one-=1;
			balance-=1;
		}
		
		//System.out.println(sum);//just checking;
		
	
	}
 
	int money_in_the_bank(){
		if(thousand<3 ||fivehunderd<3 ||hundred<3 ||fifty<3 ||twenty<3 ||ten<3 ||five<3 ||two<3 ||one<3){
			
			return 0;
		}else
			return 1;
	}
	void totalMoney(){
		System.out.println("Total transanctions : "+ total);
	}
	public String toString(){
		return "thousand = "+thousand+ "\nfivehunderd = "+fivehunderd+"\nhundred = "+hundred+"\nfifty = "+fifty+ "\ntwenty = "+twenty+"\nten = "+ten+"\nfive = "+five+"\ntwo = "+two+"\none = " + one;
	}
	
   
}


class objectIo implements Serializable{
	
	
		/*void main(){
			objectIo objectio = new objectIo();
			//Student st = new Student("udith",21,47);     //writeObject
			//obj.writeobjectToFile(st,filePath);
			for(int i =0; i < 2;i++){
				Student student = (Student)obj.ReadObjectFromFile(filePath);     //readObject
				System.out.println(student);
			}
			
			
		}*/
	void writeobjectToFile(Object obj,String filePath){
		try{
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(obj);
			objectOut.close();
			System.out.println("Succesfully written to the file");
		}catch(Exception ex){
			ex.printStackTrace();
		}
			
	}
	Object ReadObjectFromFile(String filePath){
		try{
			FileInputStream fileIn= new FileInputStream(filePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			System.out.println("Succesfully imported objects");
			objectIn.close();
			return obj;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
}




