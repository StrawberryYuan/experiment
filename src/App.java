// 客户类
class Customer {
  private String id;
  private String name;
  private String address;

  public Customer(String id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "客户: " + name + ", " + id + ", " + address;
  }
}

// 税率策略抽象类
abstract class Tax {
  public abstract double calculateTax();
}

// 美国税率策略
class USTax extends Tax {
  @Override
  public double calculateTax() {
    return 0.3;
  }
}

// 中国税率策略
class ChinaTax extends Tax {
  @Override
  public double calculateTax() {
    return 0.2;
  }
}

// 订单类
class Order {
  private String orderId;
  private Customer customer;
  private String productName;
  private String productCode;
  private double price;
  private int quantity;

  public Order(String orderId, Customer customer, String productName, String productCode, double price, int quantity) {
    this.orderId = orderId;
    this.customer = customer;
    this.productName = productName;
    this.productCode = productCode;
    this.price = price;
    this.quantity = quantity;
  }

  // 根据地址获取相应税率对象
  private Tax getTaxObjFromAddress(String address) {
    if (address.contains("中国")) {
      return new ChinaTax();
    } else if (address.contains("USA")) {
      return new USTax();
    }
    return null; // 默认情况可返回一个默认税率策略，或抛出异常
  }

  // 处理订单，计算总价
  public void process() {
    Tax tax = getTaxObjFromAddress(customer.getAddress());
    if (tax != null) {
      double taxRate = tax.calculateTax();
      double totalPrice = price * quantity * (1 + taxRate);
      System.out.println("订单号: " + orderId + ", " + customer);
      System.out.println("编号: " + productCode + ", 名称: " + productName + ", 价格: " + price + ", 数量: " + quantity);
      System.out.println("税率: " + taxRate + ", 最终总价: " + totalPrice);
    } else {
      System.out.println("无法识别的地址, 计算失败");
    }
  }
}

// 主类
public class App {
  public static void main(String[] args) {
    Customer c1 = new Customer("3601342222197208180030", "张三", "中国·江西·南昌·北京西路437号");
    Customer c2 = new Customer("987654321", "Alan Kay", "New Mexico, Rio Rancho, 4100 Sara Road, USA");

    Order order1 = new Order("001", c1, "立柱灯笼", "9505900000", 150, 10000);
    Order order2 = new Order("002", c2, "立柱灯笼", "9505900000", 150, 10000);

    order1.process();
    order2.process();
  }
}