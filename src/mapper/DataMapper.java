package mapper;

import domain.DomainObject;

public abstract class DataMapper {

	static OrderMapper orderMapper = new OrderMapper();
	static ProductMapper productMapper = new ProductMapper();
	static RegisteredUserMapper registeredUserMapper = new RegisteredUserMapper();
	static ShoppingCartMapper shoppingCartMapper = new ShoppingCartMapper();
	static WishListMapper wishListMapper = new WishListMapper();
	static AdministratorMapper administratorMapper = new AdministratorMapper();

	public abstract void insert(DomainObject object);

	public abstract void update(DomainObject object);

	public abstract void delete(DomainObject object);

	public static DataMapper getMapper(Class<? extends DomainObject> objectClass) {

		if (objectClass.equals(domain.Order.class)) {
			return orderMapper;
		}

		else if (objectClass.equals(domain.Product.class)) {
			return productMapper;
		}

		else if (objectClass.equals(domain.RegisteredUser.class)) {
			return registeredUserMapper;
		}

		else if (objectClass.equals(domain.ShoppingCart.class)) {
			return shoppingCartMapper;
		}

		else if (objectClass.equals(domain.WishList.class)) {
			return wishListMapper;
		} else if (objectClass.equals(domain.Administrator.class)) {
			return administratorMapper;
		}
		return null;
	}

}
