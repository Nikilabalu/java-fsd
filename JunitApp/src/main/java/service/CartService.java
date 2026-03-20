package service;
import exception.InvalidNameException;
import exception.InvalidPriceException;
import model.CartItem;
import model.User;

import java.math.BigDecimal;
import java.util.List;
public class CartService {
        public BigDecimal computeTotalCost(List<CartItem> items, User user){

            if(items == null)
                throw new NullPointerException("Items list cannot be null");

            if(user == null)
                throw new NullPointerException("User cannot be null");

            if(user.getUsername() == null || user.getUsername().isEmpty())
                throw new RuntimeException("Invalid username");

            if(!user.getStatus().equalsIgnoreCase("NORMAL") &&
                    !user.getStatus().equalsIgnoreCase("PREMIUM"))
                throw new RuntimeException("Invalid user status");

            BigDecimal total = BigDecimal.ZERO;

            for(CartItem item : items){

                if(item.getPrice().compareTo(BigDecimal.ZERO) < 0)
                    throw new InvalidPriceException("Price cannot be negative");

                if(item.getName() == null || item.getName().isEmpty())
                    throw new InvalidNameException("Item name cannot be empty");

                BigDecimal cost =
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

                total = total.add(cost);
            }

            // Discount rules

            if(total.compareTo(BigDecimal.valueOf(1000)) > 0){
                total = total.multiply(BigDecimal.valueOf(0.95));
            }

            if(user.getStatus().equalsIgnoreCase("PREMIUM") &&
                    total.compareTo(BigDecimal.valueOf(500)) > 0){
                total = total.multiply(BigDecimal.valueOf(0.90));
            }

            return total;
        }
    }

