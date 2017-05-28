package storage.service.impl;

import java.util.Collection;
import java.util.List;

import storage.dao.impl.OrderDaoImpl;
import storage.model.MyOrder;
import storage.model.OrderItem;
import storage.model.Product;
import storage.service.OrderService;

/**
 * Az {@link storage.service.OrderService} interfészt implementáló osztály.
 * @author gara.mihaly
 *
 */
public class OrderServiceImpl implements OrderService{

	private final static OrderDaoImpl orderDao = new OrderDaoImpl();
	/* (non-Javadoc)
	 * @see storage.service.OrderService#save(storage.model.MyOrder)
	 */
	@Override
	public void save(MyOrder order) throws IllegalArgumentException {
		if( validateOrder(order) )
			orderDao.save(order);
		
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#update(storage.model.MyOrder)
	 */
	@Override
	public void update(MyOrder order) throws IllegalArgumentException {
		if( validateOrder(order) ){
			orderDao.update(order);
		}
			
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#validateOrder(storage.model.MyOrder)
	 */
	@Override
	public boolean validateOrder(MyOrder order) throws IllegalArgumentException {
		if(order.getCustomer() == null)
			throw new IllegalArgumentException("Válasszon vásárlót!");
		if(order.getOrderItems().size() == 0)
			throw new IllegalArgumentException("Nem rendelt terméket a rendeléshez!");
		
		return true;
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#getOrderSum(storage.model.MyOrder)
	 */
	@Override
	public float getOrderSum(Collection<OrderItem> orderItems) {
		float price = 0;
		
		if(orderItems.size() > 0){
			for (OrderItem item : orderItems) {
				price += item.getQuantity() * item.getPrice();
			}
		}
		return price;
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#getAll(java.lang.String)
	 */
	@Override
	public List<MyOrder> getAll(String type) {
		switch( type ){
			case "all":
				return orderDao.getAll();
			case "closed":
				return orderDao.getClosedOrders();
			default:
				return orderDao.getActiveOrders();
		}
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#getProductCartQuantity(java.util.Collection, storage.model.Product)
	 */
	@Override
	public int getProductCartQuantity(Collection<OrderItem> orderItems, Product product){
		return orderItems.stream().filter(p -> p.getProduct().getId() == product.getId()).mapToInt(p -> p.getQuantity()).sum();
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#remove(storage.model.MyOrder)
	 */
	@Override
	public void remove(MyOrder order) {
		orderDao.remove(order);
	}

}
