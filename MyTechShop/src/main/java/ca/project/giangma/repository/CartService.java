package ca.project.giangma.repository;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.project.giangma.beans.Cart;
import ca.project.giangma.beans.CartItem;
import ca.project.giangma.beans.Item;
import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final HttpSession session;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository, HttpSession session) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.session = session;
    }

    public Optional<Cart> getLatestCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return Optional.of(cart);
    }

    @Transactional
    public void addToCart(Long itemId, int quantity) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addItem(item, quantity);
            cartRepository.save(cart); // This will save the cart and the associated items
        } else {
            throw new RuntimeException("Item not found");
        }
    }

    @Transactional
    public void checkout() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            for (CartItem cartItem : cart.getItems()) {
                Item item = cartItem.getItem();
                item.setQuantity(item.getQuantity() - cartItem.getQuantity());
                itemRepository.save(item);
            }
            cart.clear();
            session.setAttribute("cart", cart);
        }
    }
}
