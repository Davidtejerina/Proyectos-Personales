import { Component, OnInit } from '@angular/core'
import { ProductService } from '../services/product/product.service'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { CommonModule } from '@angular/common'
import { Router, RouterLink } from '@angular/router'
import { WishListService } from '../services/wishList/wish-list.service'
import { UserService } from '../services/user/user.service'
import { User } from '../classes (interfaces)/user'
import { WishList } from '../classes (interfaces)/wish-list'
import { Observable } from 'rxjs'
import { Product } from '../classes (interfaces)/product'
import Swal from 'sweetalert2'


@Component({
  selector: 'app-wishes-list',
  standalone: true,
  imports: [MenuNavBarComponent, CommonModule, RouterLink],
  templateUrl: './wishes-list.component.html',
  styleUrl: './wishes-list.component.css'
})


export class WishesListComponent implements OnInit{
  wishList: Observable<WishList[]>
  user: User = new User()
  isAvailableStock: { [productId: number]: boolean } = {}


  constructor(
    private productService: ProductService,
    private wishListService: WishListService,
    private userService: UserService,
    private router: Router
  ){}


  ngOnInit(): void {
    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
        
        this.wishListService.getProductsLiked(this.user.email).subscribe(wishList => {
          this.wishList = new Observable<WishList[]>(observer => observer.next(wishList))
          console.log(wishList)
          
          this.wishList.forEach(wishList => {
            wishList.forEach(item => {
              
              this.wishListService.isProductLiked(this.user.email, item.product.id).subscribe(
                isProductLiked => {
                  item.product.isLiked = isProductLiked
                })

              this.productService.hasStock(item.product.id).subscribe(
                hasStock => {
                  this.isAvailableStock[item.product.id] = hasStock
                })
            })
          })
        })
      }
    )
  }


  //Redirige a la página de detalles del producto
  details(id: number) {
    this.router.navigate(['product-details', id], { state: { isAvailableStock: this.isAvailableStock } })
  }


  //Devuelve si tiene stock o no
  hasStock(id: number): boolean {
    return this.isAvailableStock[id] || false
  }


  toggleLike(product: Product) {
    Swal.fire({
      text: '¿Quitar de favoritos?',
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
          this.wishListService.isProductLiked(this.user.email, product.id).subscribe(
            isProductLiked => {
              product.isLiked = isProductLiked
      
              this.wishListService.deleteProductFromWishList(this.user.email, product.id).subscribe(
                newProduct => {
                  window.location.reload()
                }
              )
            })
        }
      }
    )
  }


  deleteAll(){
    this.wishListService.deleteAll(this.user.email).subscribe(
      deleted=>{
        window.location.reload()
      })
  }
}
