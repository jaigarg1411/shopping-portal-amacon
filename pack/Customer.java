package pack;
import java.util.*;

public class Customer
{
	public class Cart_product
	{
		Database d = new Database();
		public Database.Product p = d.new Product("-1",-1,-1);
		public int quantity;

		public Cart_product(Database.Product ip,int iquantity)
		{
			this.p = ip;
			this.quantity = iquantity;
		}

		public String return_name()
		{
			return this.p.return_name();
		}

		public int return_price()
		{
			return p.return_price();
		}
	}

	public Vector<Cart_product> cart = new Vector<Cart_product>();
	public int funds;
	private String user_name;
	private String pass_word;

	public Customer(int ifunds,String iuser_name,String ipass_word)
	{
		this.funds = ifunds;
		this.user_name = iuser_name;
		this.pass_word = ipass_word;
	}

	public Customer()
	{}

	public String get_user_name()
	{
		return user_name;
	}

	public String get_pass_word()
	{
		return pass_word;
	}

	public void modify_funds(int extra)
	{
		this.funds = this.funds + extra;
	}

	public void add_to_cart(Cart_product p)
	{
		if(p.quantity <= p.p.return_units())
		{
			cart.add(p);
		}

		else
		{
			System.out.println("Quantity demanded not available!");
		}
	}

	public boolean check(String name)
	{
		int i=0;
		for(i=0;i<cart.size();i++)
		{
			if(cart.get(i).return_name().equals(name))
			{
				return true;
			}
		}
		return false;
	}

	public int view_funds()
	{
		return funds;
	}
}