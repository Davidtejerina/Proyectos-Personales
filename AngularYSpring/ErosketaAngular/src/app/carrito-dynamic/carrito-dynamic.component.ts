import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import { Router } from '@angular/router'
import { User } from '../classes (interfaces)/user'
import { UserService } from '../services/user/user.service'
import { CartService } from '../services/cart/cart.service'
import { Observable } from 'rxjs'
import { Cart } from '../classes (interfaces)/cart'
import { Product } from '../classes (interfaces)/product'


@Component({
  selector: 'app-carrito-dynamic',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carrito-dynamic.component.html',
  styleUrl: './carrito-dynamic.component.css'
})


export class CarritoDynamicComponent implements OnInit {
  myCart: Observable<Cart[]>
  user: User = new User()
  cartTotal: Observable<number>


  constructor(
    private userService: UserService,
    private cartService: CartService,
    private router: Router
  ) {}



  ngOnInit(): void {
    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
        this.updateMyCart()
        this.cartTotal = this.totalCart()
      }
    )
  }


  updateMyCart() {
    this.cartService.getListByUser(this.user.email).subscribe(data => {
      this.myCart = new Observable<Cart[]>(observer => observer.next(data))
    })
  }


  updateUnits(operation: string, amount: number, product: Product) {
    if (operation === 'minus' && amount > 0) amount--
    if (operation === 'add') amount++
    if (amount === 0) this.deleteProductFromCart(product.id)
    else {
      this.cartService.updateProductQuantity(this.user.email, product.id, amount).subscribe(() => {
        this.updateMyCart()
        this.cartTotal = this.totalCart()
      })
    }
  }

  deleteProductFromCart(productId: number) {
    this.cartService.removeProduct(productId).subscribe(() => {
      this.updateMyCart()
      this.cartTotal = this.totalCart()
    })
    window.location.reload()
  }


  deleteAllProductsFromCart() {
    this.cartService.removeAllProducts(this.user.email).subscribe(() => {
      this.updateMyCart()
      this.cartTotal = this.totalCart()
    })
    window.location.reload()
  }
  


  totalProduct(price: number, ammount: number): string {
    return (price * ammount).toFixed(2)
  }


  totalCart(): Observable<number> {
    return this.cartService.calculateTotalCart(this.user.email)
  }


  details() {
    this.router.navigate(['carrito-details'])
  }
}
