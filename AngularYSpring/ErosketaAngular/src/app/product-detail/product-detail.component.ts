import { Component, OnInit } from '@angular/core'
import { ProductService } from '../services/product/product.service'
import { ActivatedRoute, Router, RouterLink } from '@angular/router'
import Swal from 'sweetalert2'
import { CommonModule } from '@angular/common'
import { Product } from '../classes (interfaces)/product'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { User } from '../classes (interfaces)/user'
import { CartService } from '../services/cart/cart.service'
import { CartRequest } from '../classes (interfaces)/cart-request'
import { UserService } from '../services/user/user.service'


@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [CommonModule, MenuNavBarComponent, RouterLink],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.css'
})


export class ProductDetailComponent implements OnInit {
  id: number
  product: Product = new Product()
  isAvailableStock: { [productId: number]: boolean } = {}
  isDisabled = false
  isAdded = false
  user: User = new User()


  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private userService: UserService,
    private router: Router,
    private path: ActivatedRoute
  ) { }


  //Al cargar la página queremos mostrar los datos del producto cuyo id hemos especificado en la ruta
  ngOnInit(): void {
    this.id = this.path.snapshot.params['id']
    this.isAvailableStock = history.state.isAvailableStock || {}

    this.productService.getProductById(this.id).subscribe(
      dato => {
        this.product = dato
      },
      error => {
        console.error(error)

        Swal.fire({
          icon: 'error',
          title: 'El producto con ID: "' + this.id + '" no existe',
          showConfirmButton: true
        })

        this.router.navigate(['all-categories'])
      }
    )

    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
        this.cartService.getListByUser(this.user.email).subscribe(
          cart => {
            console.log(cart)
            cart.forEach(item => {
              if(this.id == item.product.id) this.isAdded = true
            })
          }
        )
      },
    )
  }


  //Añade un producto al carro con su cantidad de unidades
  addToCart(product: Product, cantidadInput: number) {
    const cartItem: CartRequest = {
      user: this.user,  
      product: product,   
      amount: cantidadInput
    }

    this.cartService.addProduct(cartItem).subscribe(
      added => {
        window.location.reload()
      }
    )

    
  }


 
  //Devuelve si el producto tiene stock o no
  hasStock(): boolean {
    return this.isAvailableStock[this.id] == true
  }


  //Deshabilita el botón en caso de no cumplir los requisitos necesarios para comprar el producto
  disabledbutton(cantidadInput: HTMLInputElement) {
    this.isDisabled = cantidadInput.valueAsNumber > 10 || cantidadInput.valueAsNumber == 0 || cantidadInput.value == ""
  }
}
