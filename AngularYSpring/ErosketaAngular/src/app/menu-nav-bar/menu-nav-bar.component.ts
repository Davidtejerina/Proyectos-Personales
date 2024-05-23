import { Component, OnInit } from '@angular/core'
import { Router, RouterLink } from '@angular/router'
import { CommonModule } from '@angular/common'
import { CarritoDynamicComponent } from '../carrito-dynamic/carrito-dynamic.component'
import { UserService } from '../services/user/user.service'
import { LoginService } from '../services/auth/login.service'
import { User } from '../classes (interfaces)/user'
import { CartService } from '../services/cart/cart.service'
import { Observable } from 'rxjs'
import Swal from 'sweetalert2'


@Component({
  selector: 'app-menu-nav-bar',
  standalone: true,
  imports: [RouterLink, CommonModule, CarritoDynamicComponent],
  templateUrl: './menu-nav-bar.component.html',
  styleUrl: './menu-nav-bar.component.css'
})


export class MenuNavBarComponent implements OnInit {
  viewCart: boolean = false
  productsInCart: number
  user: User = new User()


  constructor(
    private cartService: CartService,
    private userService: UserService,
    private loginService: LoginService,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
        this.fetchCartInfo()
      }
    )
  }



  fetchCartInfo() {
    this.cartService.countByClient(this.user.email).subscribe(count => {
      this.productsInCart = count
    })
  }


  //Muestra el carrito o lo esconde
  showCart() {
    this.viewCart = !this.viewCart
  }


  isAdmin(): boolean {
    if (this.user.role == "ADMIN") return true
    return false
  }


  logOut() {
    Swal.fire({
      text: '¿Cerrar sesión?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí',
      cancelButtonText: 'No, gracias',
      customClass: {
        confirmButton: 'm-2 btn btn-success',
        cancelButton: 'm-2 btn btn-danger',
      },
      buttonsStyling: false,
    }).then(
      result => {
        if (result.isConfirmed) {
          this.loginService.logout()
          this.router.navigate(['login'])
        }
      }
    )
  }
}
