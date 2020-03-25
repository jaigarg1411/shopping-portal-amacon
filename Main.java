import java.util.*;
import pack.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Database all = new Database();
		Vector<Customer> customers_login = new Vector<Customer>();
		int revenue = 0;
		File file = new File("input.txt"); 
    	Scanner sci = new Scanner(file);
    	while(sci.hasNextLine())
    	{
    		String path = sci.next();
    		String name = sci.next();
    		int price = sci.nextInt();
    		int quantity = sci.nextInt();
    		all.insert(path,name,price,quantity);
    	}

    	File file1 = new File("customer_input.txt"); 
    	Scanner sci1 = new Scanner(file1);
    	String l;
    	while(sci1.hasNextLine())
    	{
    		
    		String user = sci1.next();
    		String pass = sci1.next();
    		int fund = sci1.nextInt();
    		Customer c9 = new Customer(fund,user,pass);
    		all.add_id(user,pass);
    		while((l = sci1.next()).equals("line") == false)
    		{
    			String product = l;
    			int quantity = sci1.nextInt();
    			Database d = new Database();
				Database.Product p1 = all.search(product);
				if(p1.return_price() != -1)
				{
					Customer temp = new Customer();
					Customer.Cart_product pp = temp.new Cart_product(p1,quantity);
					c9.add_to_cart(pp);
				}
    		}
    		customers_login.add(c9);
    	}

		int option,option1,option2,option3;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n\nWELCOME");
		while(true)
		{
			System.out.println("Enter the option:- ");
			System.out.println("1. Administrator");
			System.out.println("2. Customer");
			System.out.println("0. EXIT");
			option = sc.nextInt();
			if(option == 0)
			{
				break;
			}

			else if(option == 1)
			{
				while(true)
				{
					System.out.println("");
					System.out.println("Enter the option:- ");
					System.out.println("1. Insert Product/Category");
					System.out.println("2. Delete Product/Category");
					System.out.println("3. Search product");
					System.out.println("4. Modify product");
					System.out.println("0. EXIT as Administrator");
					option1 = sc.nextInt();
					if(option1 == 0)
					{
						break;
					}

					else if(option1 == 1)
					{
						System.out.print("Enter path:- ");
						String path = sc.next();
						System.out.print("Enter name of product:- ");
						String name2= sc.next();
						System.out.print("Enter price of product:- ");
						int price = sc.nextInt();
						System.out.print("Enter no. of units of product:- ");
						int units = sc.nextInt();
						all.insert(path,name2,price,units);
					}

					else if(option1 == 2)
					{
						System.out.print("Enter path:- ");
						String name = sc.next();
						all.delete(name);
					}

					else if(option1 == 3)
					{
						System.out.print("Enter name of product:- ");
						String name = sc.next();
						Database d = new Database();
						Database.Product p = all.search(name);
					}

					else if(option1 == 4)
					{
						System.out.print("Enter name of product:- ");
						String name= sc.next();
						System.out.print("Enter new price of product:- ");
						int price = sc.nextInt();
						System.out.print("Enter no. of units of product:- ");
						int units = sc.nextInt();
						all.modify(name,price,units);
					}					
				}
			}

			else if(option == 2)
			{
				while(true)
				{
					System.out.println("");
					System.out.println("Enter the option:- ");
					System.out.println("1. Register");
					System.out.println("2. Login");
					System.out.println("0. EXIT as Customer");
					option2 = sc.nextInt();

					if(option2 == 0)
					{
						break;
					}

					else if(option2 == 1)
					{
						String u,p;
						while(true)
						{
							System.out.print("Enter username(press 0 to exit Register):- ");
							u = sc.next();
							if(u.equals("0"))
							{
								break;
							}
							int[] a = all.check_id(u,"");
							if(a[0] == 1)
							{
								System.out.println("Username already exist!");
							}
							else
							{
								break;
							}
						}
						if(u.equals("0"))
						{
							continue;
						}
						System.out.print("Enter password:- ");
						p = sc.next();

						all.add_id(u,p);
						System.out.println("Successfully registered!");
					}

					else if(option2 == 2)
					{
						String u;
						String p = "";
						int[] a = new int[2];
						a[0] = 0;
						a[1] = 0;
						while(true)
						{
							System.out.print("Enter username (press 0 to exit Login):- ");
							u = sc.next();
							if(u.equals("0"))
							{
								break;
							}
							System.out.print("Enter password:- ");
							p = sc.next();
							a = all.check_id(u,p);
							if(a[0] == 0)
							{
								System.out.println("Username invalid!");
							}
							else if(a[0] == 1 && a[1] == 0)
							{
								System.out.println("Password invalid");
							}

							else if(a[0] == 1 && a[1] == 1)
							{
								break;
							}
						}

						if(a[0] == 1 && a[1] == 1)
						{
							Customer c = new Customer();
							int i,index=-1;
							for(i=0;i<customers_login.size();i++)
							{
								if((customers_login.get(i).get_user_name().equals(u)) && (customers_login.get(i).get_pass_word().equals(p)))
								{
									index = i;
									break;
								}
							}

							if(index != -1)
							{
								c = customers_login.get(index);
							}

							else
							{
								System.out.print("Enter the initial amount of funds:- ");
								int funds;
								funds = sc.nextInt();
								Customer c1 = new Customer(funds,u,p);
								customers_login.add(c1);
								c = c1;
							}

							while(true)
							{
								System.out.println("");
								System.out.println("Enter the option:- ");
								System.out.println("1. Add more funds");
								System.out.println("2. Add product");
								System.out.println("3. Check out cart");
								System.out.println("4. View available funds");
								System.out.println("0. LOGOUT");
								option3 = sc.nextInt();
								if(option3 == 0)
								{
									break;
								}

								else if(option3 == 1)
								{
									System.out.print("Enter the amount of funds to be added:- ");
									int f = sc.nextInt();
									c.modify_funds(f);
								}

								else if(option3 == 2)
								{
									System.out.print("Enter the name of product:- ");
									String pro = sc.next();
									int quantity;
									Database d = new Database();
									Database.Product p1 = all.search(pro);
									if(c.check(pro) == true)
									{
										System.out.println("Product already exist in your cart!");
									}

									if((p1.return_price() != -1) && (c.check(pro) == false))
									{
										while(true)
										{
											System.out.print("Enter the quantity of product:- ");
											quantity = sc.nextInt();
											if(quantity <= p1.return_units())
											{
												break;
											}

											else
											{
												System.out.println("Quantity exceeds no. of units available!");
											}
										}
										Customer temp = new Customer();
										Customer.Cart_product pp = temp.new Cart_product(p1,quantity);
										c.add_to_cart(pp);
										System.out.println("Product successfully added!");
									}
								}

								else if(option3 == 3)
								{
									int no_items_in_cart = c.cart.size();
									System.out.println("Total no. of items in your cart are:- " + no_items_in_cart);

									int amount_in_cart=0;
									int yy = 0;
									for(yy=0;yy<no_items_in_cart;yy++)
									{
										amount_in_cart += (c.cart.get(yy).return_price()*c.cart.get(yy).quantity);
									}

									if(c.view_funds() >= amount_in_cart)
									{
										int ii=0;
										for(ii=0;ii<no_items_in_cart;ii++)
										{
											boolean sell = all.sale(c.cart.get(ii).p,c.cart.get(ii).quantity,c.funds);
											if(sell == true)
											{
												int j = ii+1;
												System.out.println("Product " + j + " successfully sold!");
												c.modify_funds((c.cart.get(ii).quantity)*(c.cart.get(ii).p.return_price())*(-1));
												revenue += (c.cart.get(ii).quantity)*(c.cart.get(ii).p.return_price());
											}

											else if(sell == false)
											{
												int j = ii+1;
												System.out.println("Product " + j + " cannot be sold!");
												break;
											}
										}
										c.cart.clear();
									}
									else
									{
										System.out.println("You don't have enough fund!");
										System.out.print("Press 1 if you want to clear your cart:- ");
										int ll = sc.nextInt();
										if(ll == 1)
										{
											c.cart.clear();
										}
									}
									all.check_database();
								}

								else if(option3 == 4)
								{
									System.out.println("Your available funds are:- " + c.view_funds());
								}
							}
						}
					}
				}
			}
		}

		PrintWriter pw = new PrintWriter("input.txt");
		pw.close();
		PrintWriter pw1 = new PrintWriter("customer_input.txt");
		pw1.close();

		Writer wr = new FileWriter("input.txt");
		int temp1=0,temp2=0,temp3=0;
		boolean bool = false;
		for(temp1=0;temp1<all.data.size();temp1++)
		{
			for(temp2=0;temp2<all.data.get(temp1).subcategory_array.size();temp2++)
			{
				for(temp3=0;temp3<all.data.get(temp1).subcategory_array.get(temp2).products_array.size();temp3++)
				{
					String a = all.data.get(temp1).return_name() + ">" + all.data.get(temp1).subcategory_array.get(temp2).return_name();
					String b = all.data.get(temp1).subcategory_array.get(temp2).products_array.get(temp3).return_name();
					int pri = all.data.get(temp1).subcategory_array.get(temp2).products_array.get(temp3).return_price();
					int uni = all.data.get(temp1).subcategory_array.get(temp2).products_array.get(temp3).return_units();
					if(bool == true)
					{
						wr.write("\n");
					}
					bool = true;
					wr.write(a);
					wr.write(" ");
					wr.write(b);
					wr.write(" ");
					wr.write(String.valueOf(pri));
					wr.write(" ");
					wr.write(String.valueOf(uni));
				}
			}
		}
		wr.flush();
		wr.close();

		Writer wr1 = new FileWriter("customer_input.txt");
		temp1 = 0;
		temp2 = 0;
		bool = false;
		for(temp1=0;temp1<customers_login.size();temp1++)
		{
			Customer c8 = customers_login.get(temp1);
			String u = c8.get_user_name();
			String pa = c8.get_pass_word();
			int f = c8.view_funds();
			if(bool == true)
			{
				wr1.write("\n");
			}
			wr1.write(u);
			wr1.write(" ");
			wr1.write(pa);
			wr1.write(" ");
			wr1.write(String.valueOf(f));
			wr1.write("\n");
			bool = true;
			for(temp2=0;temp2<c8.cart.size();temp2++)
			{
				String paa = c8.cart.get(temp2).return_name();
				int q = c8.cart.get(temp2).quantity;
				wr1.write(paa);
				wr1.write(" ");
				wr1.write(String.valueOf(q));
				wr1.write("\n");
			}
			wr1.write("line");
		}
		wr1.flush();
		wr1.close();
		System.out.println("Total revenue generated is:- " + revenue);
	}
}
