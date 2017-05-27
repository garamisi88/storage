package storage.service.impl;

import java.util.List;

import storage.dao.impl.OrderDaoImpl;
import storage.model.MyOrder;
import storage.model.OrderItem;
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
	public void save(MyOrder order) throws Exception {
		if( validateOrder(order) )
			orderDao.save(order);
		
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#update(storage.model.MyOrder)
	 */
	@Override
	public void update(MyOrder order) throws Exception {
		if( validateOrder(order) )
			orderDao.update(order);
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#validateOrder(storage.model.MyOrder)
	 */
	@Override
	public boolean validateOrder(MyOrder order) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see storage.service.OrderService#getOrderSum(storage.model.MyOrder)
	 */
	@Override
	public float getOrderSum(MyOrder order) {
		float price = 0;
		System.out.println(order.getOrderItems().size()+" db");
		if(order.getOrderItems().size() > 0){
			for (OrderItem item : order.getOrderItems()) {
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
		List<MyOrder> orders = null;
		
		switch( type ){
			case "all":
				orders = orderDao.getAll();
				break;
			case "closed":
				orders = orderDao.getClosedOrders();
				break;
			default:
				orders = orderDao.getActiveOrders();
				break;
		}
		
		return orders;
	}

}
