package pack;
import java.util.*;

public class Database
{
	public Vector<Category> data = new Vector<Category>();
	public Vector<User_pass_data> customer_data = new Vector<User_pass_data>();

	public class User_pass_data
	{
		private String username;
		private String password;

		public User_pass_data(String iuser,String ipass)
		{
			this.username = iuser;
			this.password = ipass;
		}

		public String get_username()
		{
			return username;
		}

		public String get_password()
		{
			return password;
		}
	}

	public class Product
	{
		private String pro_name;
		private int price;
		private int no_units;

		public Product(String iname,int iprice,int ino_units)
		{
			this.pro_name = iname;
			this.price = iprice;
			this.no_units = ino_units;
		}

		public void modify(int iprice,int ino_units)
		{
			this.price = iprice;
			this.no_units = ino_units;
		}

		public Product(Product tobecopied)
		{
			this.pro_name = tobecopied.pro_name;
			this.price = tobecopied.price;
			this.no_units = tobecopied.no_units;
		}

		public String return_name()
		{
			return pro_name;
		}
		public int return_price()
		{
			return price;
		}
		public int return_units()
		{
			return no_units;
		}
	}

	public class Subcategory
	{
		private String sub_name;
		public Vector<Product> products_array = new Vector<Product>();
		public void add_product(String name,int price,int no_units)
		{
			Product pro = new Product(name,price,no_units);
			products_array.add(pro);
		}

		public Subcategory(String iname)
		{
			this.sub_name = iname;
		}

		public String return_name()
		{
			return sub_name;
		}
	}

	public class Category
	{
		private String cat_name;
		public Vector<Subcategory> subcategory_array = new Vector<Subcategory>();

		public Category(String name)
		{
			this.cat_name = name;
		}

		public void add_subcategory(String name)
		{
			Subcategory sub = new Subcategory(name);
			subcategory_array.add(sub);
		}

		public String return_name()
		{
			return cat_name;
		}
	}

	public void insert(String path,String product_name,int price,int units)
	{
		int y = 0,count=0;
		for(y=0;y<path.length();y++)
		{
			if(path.charAt(y) == '>')
			{
				count++;
				break;
			}
		}
		
		if(count == 1)
		{
			String category_name = path.substring(0,y);
			String subcategory_name = path.substring(y+1);
			int i,index=-1;
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).return_name().equals(category_name))
				{
					index = i;
					break;
				}
			}

			if(index == -1)
			{
				Category cat = new Category(category_name);
				data.add(cat);
				index = data.size()-1;
			}

			if((category_name.length() != 0) && (subcategory_name.length() != 0))
			{	int j,index1=-1;
				for(j=0;j<data.get(index).subcategory_array.size();j++)
				{
					if(data.get(index).subcategory_array.get(j).return_name().equals(subcategory_name))
					{
						index1 = j;
						break;
					}
				}

				if(index1 == -1)
				{
					data.get(index).add_subcategory(subcategory_name);
				}

				for(j=0;j<data.get(index).subcategory_array.size();j++)
				{
					if(data.get(index).subcategory_array.get(j).return_name().equals(subcategory_name))
					{
						index1 = j;
						break;
					}
				}

				int k;
				boolean temp = false;
				for(k=0;k<data.get(index).subcategory_array.get(index1).products_array.size();k++)
				{
					if(data.get(index).subcategory_array.get(index1).products_array.get(k).return_name().equals(product_name))
					{
						temp = true;
						break;
					}
				}

				if(temp)
				{
					System.out.println("Product already exists!");
				}

				else
				{
					data.get(index).subcategory_array.get(index1).add_product(product_name,price,units);
					System.out.println("Product successfully added!");
				}
			}
		}
	}

	public void delete_subcategory(String category_name,String subcategory_name)
	{
		int i,index=-1;
		for(i=0;i<data.size();i++)
		{
			if(data.get(i).return_name().equals(category_name))
			{
				index = i;
				break;
			}
		}
		if(index == -1)
		{
			System.out.println("Category name invalid!");
		}
		else
		{
			int j,index1=-1;
			for(j=0;j<data.get(index).subcategory_array.size();j++)
			{
				if(data.get(index).subcategory_array.get(j).return_name().equals(subcategory_name))
				{
					index1 = j;
					break;
				}
			}
			if(index1 == -1)
			{
				System.out.println("Subcategory do not exist!");
			}
			else
			{
				data.get(index).subcategory_array.remove(index1);
				System.out.println("Subcategory removed!");
			}
		}
	}

	public void delete(String path)
	{
		int y=0;
		int count = 0;
		int first=0,second=0;
		for(y=0;y<path.length();y++)
		{
			if(path.charAt(y) == '>')
			{
				count++;
				if(count == 1)
				{
					first = y;
				}
				else
				{
					second = y;
				}
			}
		}


		if(count == 2)
		{
			String category_name = path.substring(0,first);
			String subcategory_name = path.substring(first+1,second);
			String product_name = path.substring(second+1);
			int i,index=-1;
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).return_name().equals(category_name))
				{
					index = i;
					break;
				}
			}

			if(index == -1)
			{
				System.out.println("Category name invalid!");
			}

			else
			{
				int j,index1=-1;
				for(j=0;j<data.get(index).subcategory_array.size();j++)
				{
					if(data.get(index).subcategory_array.get(j).return_name().equals(subcategory_name))
					{
						index1 = j;
						break;
					}
				}

				if(index1 == -1)
				{
					System.out.println("Subcategory name invalid!");
				}

				else
				{
					int k,index2=-1;
					for(k=0;k<data.get(index).subcategory_array.get(index1).products_array.size();k++)
					{
						if(data.get(index).subcategory_array.get(index1).products_array.get(k).return_name().equals(product_name))
						{
							index2 = k;
							break;
						}
					}

					if(index2 == -1)
					{
						System.out.println("Product do not exist!");
					}

					else
					{
						data.get(index).subcategory_array.get(index1).products_array.remove(index2);
						System.out.println("Product has been removed successfully!");
					}
				}
			}
		}

		else
		{
			String category_name = path.substring(0,first);
			String subcategory_name = path.substring(first+1);
			delete_subcategory(category_name,subcategory_name);
		}
	}

	public Product search(String product_name)
	{
		int i = 0;
		int j = 0;
		int k = 0;
		boolean available = false;
		for(i=0;i<data.size();i++)
		{
			for(j=0;j<data.get(i).subcategory_array.size();j++)
			{
				for(k=0;k<data.get(i).subcategory_array.get(j).products_array.size();k++)
				{
					if(data.get(i).subcategory_array.get(j).products_array.get(k).return_name().equals(product_name))
					{
						available = true;
						break;
					}
				}
				if(available == true)
				{
					break;
				}
			}
			if(available == true)
			{
				break;
			}
		}

		if(available == false)
		{
			System.out.println("Product not available!");
			Product p = new Product("-1",-1,-1);
			return p;
		}

		else
		{
			System.out.println("Path to the product is :- " + data.get(i).return_name() + " > " + data.get(i).subcategory_array.get(j).return_name() + " > " + product_name);
			System.out.println("Details of product are as follows:- ");
			System.out.println("Price:- " + data.get(i).subcategory_array.get(j).products_array.get(k).return_price());
			System.out.println("No. of units:- " + data.get(i).subcategory_array.get(j).products_array.get(k).return_units());
			return data.get(i).subcategory_array.get(j).products_array.get(k);
		}
	}

	public void modify(String product_name,int price,int units)
	{
		System.out.println("\nInitial details of product are:-");
		Product p = search(product_name);
		if(p.return_price() != -1)
		{
			p.modify(price,units);
		}
		System.out.println("\nFinal details of product are:-");
		Product p1 = search(product_name);
	}

	public boolean sale(Product p,int quantity,int remaining_funds)
	{
		boolean sold = false;
		int price_of_product = p.return_price();
		int units_available = p.return_units();
		int bill = quantity*price_of_product;

		if(quantity > units_available)
		{
			System.out.println("Insufficient stock available!");
		}

		else if(remaining_funds < bill)
		{
			System.out.println("Insufficient funds!");
		}

		else
		{
			sold = true;
			p.modify(price_of_product,units_available-quantity);
		}
		return sold;
	}

	public void check_database()
	{
		int i = 0;
		int j = 0;
		int k = 0;
		for(i=0;i<data.size();i++)
		{
			for(j=0;j<data.get(i).subcategory_array.size();j++)
			{
				Vector<Integer> v = new Vector<Integer>();
				for(k=0;k<data.get(i).subcategory_array.get(j).products_array.size();k++)
				{
					if(data.get(i).subcategory_array.get(j).products_array.get(k).return_units() == 0)
					{
						v.add(k);
					}
				}

				for(k=0;k<v.size();k++)
				{
					int y = v.get(k);
					data.get(i).subcategory_array.get(j).products_array.remove(y);
				}
			}
		}
	}

	public void add_id(String user,String pass)
	{
		User_pass_data id = new User_pass_data(user,pass);
		customer_data.add(id);
	}

	public int[] check_id(String user,String pass)
	{
		int[] a = new int[2];
		a[0] = 0;
		a[1] = 0;
		int i=0;
		for(i=0;i<customer_data.size();i++)
		{
			if(customer_data.get(i).get_username().equals(user))
			{
				a[0] = 1;
				if(customer_data.get(i).get_password().equals(pass))
				{
					a[1] = 1;
				}
				break;
			}
		}
		return a;
	}
}