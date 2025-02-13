package ca.project.giangma.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.project.giangma.beans.Cart;
import ca.project.giangma.repository.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model) {
        Optional<Cart> cartOptional = cartService.getLatestCart();
        cartOptional.ifPresent(cart -> model.addAttribute("cart", cart));
        return "cart.html";
    }

    @PostMapping("/addItem")
    public String addToCart(@RequestParam Long itemId, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
        try {
            cartService.addToCart(itemId, quantity);
            redirectAttributes.addFlashAttribute("successMessage", "Item added to cart successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        try {
            cartService.checkout();
            redirectAttributes.addFlashAttribute("successMessage", "Checkout successful.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cart";
    }
}
