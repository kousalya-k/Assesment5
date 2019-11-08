package Main;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Dao.ProductDao;
import model.Product;

class NameException extends Exception {
	public NameException(String message) {
		super(message);
	}
}

public class Mainclass {
	class NameException extends Exception {
		public NameException(String message) {
			super(message);
		}
	}

	public boolean validatename(String name) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
		try {
			if (p.matcher(name).matches()) {
				return false;
			}
			if (!(p.matcher(name).matches())) {
				throw new NameException("Name Should not contain numbers or special characters or be null");
			} else {
				return false;
			}
		} catch (NameException e) {
			System.err.println(e.getMessage());
			return true;
		}
	}

	public static void main(String[] args) {
		Mainclass m = new Mainclass();
		ProductDao sDao = new ProductDao();
		Product s = new Product();
		Scanner sc = new Scanner(System.in);
		int input = -1;
		String id;
		do {
			System.out.println("\n");
			System.out.println("Press 1 to Enter new product details");
			System.out.println("Press 2 to Get Product Details");
			System.out.println("Press 3 to Delete Product details");
			System.out.println("Press 4 to Update Product details");
			System.out.println("Press 5 to search the Product");
			System.out.println("Press 0 to exit ");
			System.out.println("\n");
			System.out.println("Please enter your option");
			input = sc.nextInt();
			sc.nextLine();
			switch (input) {
			case 1:
				System.out.printf("%70s\n", "ENTER DETAILS OF Product");
				System.out.println("Id : ");
				id = sc.nextLine();

				System.out.println("Name of Product : ");
				String name = sc.nextLine();
				while (m.validatename(name)) {
					System.out.println("Please enter valid Name of patient : ");
					name = sc.nextLine();
				}
				System.out.println("Desc : ");
				String Desc = sc.nextLine();
				System.out.println("Price : ");
				String price = sc.nextLine();
				System.out.println("Quantity : ");
				String Quan = sc.nextLine();
				Product pro = new Product(id, name, Desc, price, Quan);

				sDao.create(pro);
				break;

			case 2:
				sDao.viewproducts();
				break;

			case 3:
				System.out.println("Enter the Id of the product to Delete:");
				String idtoFind = sc.nextLine();
				sDao.delete(idtoFind);
				
			case 4:
				System.out.println("Enter the new Details");
				System.out.println("Id : ");
				String newid = sc.nextLine();

				System.out.println("Name of Product : ");
				String newname = sc.nextLine();
				while (m.validatename(newname)) {
					System.out.println("Please enter valid Name of patient : ");
					String newname1 = sc.nextLine();
				}
				System.out.println("Desc : ");
				String nDesc = sc.nextLine();
				System.out.println("Price : ");
				String nprice = sc.nextLine();
				System.out.println("Quantity : ");
				String nQuan = sc.nextLine();
				Product product = new Product(newid, newname, nDesc, nprice, nQuan);

				sDao.update(product,newid);
				break;

			case 5:

				System.out.println("Enter the ID of Product to Search:");
				String idtoSearch = sc.nextLine();
				sDao.searchproducts(idtoSearch);

			}

		} while (input != 0);
	}
}
