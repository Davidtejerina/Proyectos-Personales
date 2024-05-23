import { CommonModule } from '@angular/common'
import { Component, OnInit } from '@angular/core'
import { Router, RouterLink } from '@angular/router'
import { User } from '../classes (interfaces)/user'
import { UserService } from '../services/user/user.service'
import { Observable } from 'rxjs'
import { CartService } from '../services/cart/cart.service'
import { Cart } from '../classes (interfaces)/cart'
import { MenuNavBarComponent } from '../menu-nav-bar-order/menu-nav-bar.component'
import { OrderService } from '../services/order/order.service'
import { OrderRequest } from '../classes (interfaces)/order-request'


@Component({
  selector: 'app-carrito-details',
  standalone: true,
  imports: [CommonModule, RouterLink, MenuNavBarComponent],
  templateUrl: './carrito-details.component.html',
  styleUrl: './carrito-details.component.css'
})


export class CarritoDetailsComponent implements OnInit {
  myCart: Observable<Cart[]>
  user: User = new User()
  cartTotalPrice: number



  constructor(
    private cartService: CartService,
    private userService: UserService,
    private orderService: OrderService,
    private router: Router
  ) { }



  ngOnInit(): void {
    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
        this.updateMyCart()
        this.totalCart()
      },
      error => {
        console.error('Error al obtener información del usuario:', error);
      }
    )
  }


  //Actualiza el carrito
  updateMyCart() {
    this.cartService.getListByUser(this.user.email).subscribe(data => {
      this.myCart = new Observable<Cart[]>(observer => observer.next(data))
    })
  }


  //Devuelve el precio total del carrito
  totalCart(): void {
    this.cartService.calculateTotalCart(this.user.email).subscribe(
      total => {
        this.cartTotalPrice = parseFloat(total.toFixed(2))
      }
    )
  }


  //Devuelve el precio total del producto del carrito multiplicando su precio unitario con sus unidades
  totalProduct(price: number, ammount: number): string {
    return (price * ammount).toFixed(2)
  }


  //Redirige a la página de pagos
  saveOrder() {
    const order: OrderRequest = {
      user: this.user,
      total: this.cartTotalPrice,
      date: new Date(),
      address: this.user.postal_code+", "+this.user.city+", "+this.user.country
    }

    this.orderService.addOrder(order).subscribe(
      () => {
        this.router.navigate(['pay-method']);
      },
      error => {
        console.error("Error al añadir la orden:", error);
      }
    );
  }

}
