const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    })
}
if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    })
}

// Client-side shopping cart logic
let cart = JSON.parse(localStorage.getItem('cart')) || [];

function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
}

function getCart() {
    return cart;
}

function addItemToCart(item) {
    const existingItemIndex = cart.findIndex(cartItem => cartItem.id === item.id);
    if (existingItemIndex > -1) {
        cart[existingItemIndex].quantity += item.quantity;
    } else {
        cart.push(item);
    }
    saveCart();
    console.log('Cart contents:', cart);
    return cart;
}

function updateCartItemQuantity(id, quantity) {
    const itemIndex = cart.findIndex(cartItem => cartItem.id === id);
    if (itemIndex > -1) {
        if (quantity === 0) {
            cart.splice(itemIndex, 1);
        } else {
            cart[itemIndex].quantity = quantity;
        }
        saveCart();
        console.log('Cart item quantity updated:', cart);
        return cart;
    }
    return null;
}

function removeItemFromCart(id) {
    const initialLength = cart.length;
    cart = cart.filter(cartItem => cartItem.id !== id);
    if (cart.length < initialLength) {
        saveCart();
        console.log('Item removed from cart:', cart);
        return cart;
    }
    return null;
}

document.addEventListener('DOMContentLoaded', () => {
    const cartButtons = document.querySelectorAll('.pro .cart');

    cartButtons.forEach(button => {
        button.addEventListener('click', async (event) => {
            event.preventDefault();

            const productId = button.dataset.productId;
            const productName = button.dataset.productName;
            const productPrice = parseFloat(button.dataset.productPrice);

            if (productId && productName && productPrice) {
                const item = {
                    id: productId,
                    name: productName,
                    price: productPrice,
                    quantity: 1
                };
                addItemToCart(item);
                alert(`${productName} added to cart!`);
                // Optionally, update a cart display on the page
            } else {
                console.error('Missing product data attributes for cart item.');
            }
        });
    });

    // Initial cart load (optional, depending on how you want to display cart)
    console.log('Initial cart:', getCart());

    const newsletterSignupBtn = document.getElementById('newsletter-signup-btn');
    if (newsletterSignupBtn) {
        newsletterSignupBtn.addEventListener('click', () => {
            const emailInput = document.getElementById('newsletter-email');
            if (emailInput && emailInput.value) {
                alert(`Thank you for signing up for our newsletter with ${emailInput.value}!`);
                emailInput.value = ''; // Clear the input
            } else {
                alert('Please enter a valid email address.');
            }
        });
    }
});
