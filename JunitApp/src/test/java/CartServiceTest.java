import exception.InvalidNameException;
import exception.InvalidPriceException;
import model.CartItem;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.CartService;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceTest {



        CartService cartService = new CartService();

        @Test
        public void computeNormalUserTest(){

            List<CartItem> items = List.of(
                    new CartItem(1,"Phone", BigDecimal.valueOf(200),2)
            );

            User user = new User(1,"nikila","NORMAL");

            BigDecimal total = cartService.computeTotalCost(items,user);

            Assertions.assertEquals(BigDecimal.valueOf(400), total);
        }

        @Test
        public void computePremiumDiscountTest(){

            List<CartItem> items = List.of(
                    new CartItem(1,"Laptop", BigDecimal.valueOf(600),1)
            );

            User user = new User(2,"nikila","PREMIUM");

            BigDecimal total = cartService.computeTotalCost(items,user);

            Assertions.assertEquals(BigDecimal.valueOf(540.0), total);
        }

        @Test
        public void nullItemsTest(){

            User user = new User(1,"nikila","NORMAL");

            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> cartService.computeTotalCost(null,user)
            );
        }

        @Test
        public void invalidPriceTest(){

            List<CartItem> items = List.of(
                    new CartItem(1,"Phone", BigDecimal.valueOf(-100),1)
            );

            User user = new User(1,"nikila","NORMAL");

            Assertions.assertThrows(
                    InvalidPriceException.class,
                    () -> cartService.computeTotalCost(items,user)
            );
        }

        @Test
        public void invalidNameTest(){

            List<CartItem> items = List.of(
                    new CartItem(1,"", BigDecimal.valueOf(100),1)
            );

            User user = new User(1,"nikila","NORMAL");

            Assertions.assertThrows(
                    InvalidNameException.class,
                    () -> cartService.computeTotalCost(items,user)
            );
        }
    @Test
    void nullUserTest() {
        List<CartItem> items = List.of(new CartItem(1,"Phone", BigDecimal.valueOf(100),1));
        Assertions.assertThrows(NullPointerException.class,
                () -> cartService.computeTotalCost(items, null));
    }

    @Test
    void invalidUsernameTest() {
        List<CartItem> items = List.of(new CartItem(1,"Phone", BigDecimal.valueOf(100),1));
        User user = new User(1, "", "NORMAL");
        Assertions.assertThrows(RuntimeException.class,
                () -> cartService.computeTotalCost(items, user));
    }

    @Test
    void invalidStatusTest() {
        List<CartItem> items = List.of(new CartItem(1,"Phone", BigDecimal.valueOf(100),1));
        User user = new User(1, "nikila", "GOLD");
        Assertions.assertThrows(RuntimeException.class,
                () -> cartService.computeTotalCost(items, user));
    }

    @Test
    void totalGreaterThan1000DiscountTest() {
        List<CartItem> items = List.of(
                new CartItem(1,"TV", BigDecimal.valueOf(600),2) // 1200
        );
        User user = new User(1,"nikila","NORMAL");

        BigDecimal total = cartService.computeTotalCost(items, user);
        //Assertions.assertEquals(BigDecimal.valueOf(1140.0), total); // 5% off
        Assertions.assertEquals(0, total.compareTo(BigDecimal.valueOf(1140)));
    }

    @Test
    void premiumDiscountTest() {
        List<CartItem> items = List.of(
                new CartItem(1,"Laptop", BigDecimal.valueOf(600),1) // >500
        );
        User user = new User(1,"nikila","PREMIUM");

        BigDecimal total = cartService.computeTotalCost(items, user);
        Assertions.assertEquals(BigDecimal.valueOf(540.0), total); // 10% off
    }
    @Test
    void itemNameNullTest() {

        List<CartItem> items = List.of(
                new CartItem(1, null, BigDecimal.valueOf(100), 1)
        );

        User user = new User(1, "nikila", "NORMAL");

        Assertions.assertThrows(
                InvalidNameException.class,
                () -> cartService.computeTotalCost(items, user)
        );
    }
    @Test
    void usernameNullTest() {

        List<CartItem> items = List.of(
                new CartItem(1,"Phone", BigDecimal.valueOf(100),1)
        );

        User user = new User(1,null,"NORMAL");

        Assertions.assertThrows(
                RuntimeException.class,
                () -> cartService.computeTotalCost(items,user)
        );
    }

    @Test
    void premiumUserNoDiscountTest(){

        List<CartItem> items = List.of(
                new CartItem(1,"Mouse", BigDecimal.valueOf(100),2) // 200
        );

        User user = new User(1,"nikila","PREMIUM");

        BigDecimal total = cartService.computeTotalCost(items,user);

        Assertions.assertEquals(0, total.compareTo(BigDecimal.valueOf(200)));
    }
    @Test
    void noDiscountCaseTest() {
        List<CartItem> items = List.of(
                new CartItem(1,"Mouse", BigDecimal.valueOf(100),2) // 200

        );
        User user = new User(1,"nikila","NORMAL");

        BigDecimal total = cartService.computeTotalCost(items, user);
        Assertions.assertEquals(BigDecimal.valueOf(200), total);
    }
    }





