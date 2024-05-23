import { Component } from '@angular/core'
import { ProductService } from '../services/product/product.service'
import { ActivatedRoute, Router, RouterLink } from '@angular/router'
import { CommonModule } from '@angular/common'
import { CategoryService } from '../services/category/category.service'
import Swal from 'sweetalert2'
import { Category } from '../classes (interfaces)/category'
import { Product } from '../classes (interfaces)/product'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { WishListService } from '../services/wishList/wish-list.service'
import { UserService } from '../services/user/user.service'
import { User } from '../classes (interfaces)/user'
import { WishListRequest } from '../classes (interfaces)/wish-list-request'
import { FormsModule } from '@angular/forms'


@Component({
  selector: 'app-list-products',
  standalone: true,
  imports: [CommonModule, MenuNavBarComponent, FormsModule, RouterLink],
  templateUrl: './list-products.component.html',
  styleUrl: './list-products.component.css'
})


export class ListProductsComponent {
  id: number
  user: User = new User()
  products: Product[]
  category: Category = new Category()
  isAvailableStock: { [productId: number]: boolean } = {}
  selectedSortOption: string = 'default';


  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private userService: UserService,
    private wishListService: WishListService,
    private router: Router,
    private path: ActivatedRoute
  ) { }


  //Nada mas entrar a la página, recoge el id de la categoria y muestra todos sus productos
  ngOnInit(): void {
    this.id = this.path.snapshot.params['id']

    this.categoryService.getCategoryListById(this.id).subscribe(
      dato => {
        this.category = dato
        this.getProductsListByCategoryId()
      },
      error => {
        console.log(error)

        Swal.fire({
          icon: 'error',
          title: 'La categoria con ID: "' + this.id + '" no existe',
          showConfirmButton: true
        })

        this.router.navigate(['all-categories'])
      })
  }


  //Devuelve todos los productos de una determinada categoria
  private getProductsListByCategoryId() {
    this.userService.getUser().subscribe(userData => {
      this.user = userData
    })

    this.productService.getProductsByCategoryId(this.id).subscribe(
      datos => {
        this.products = datos

        this.products.forEach(product => {

          this.wishListService.isProductLiked(this.user.email, product.id).subscribe(
            isProductLiked => {
              product.isLiked = isProductLiked
            })

          this.productService.hasStock(product.id).subscribe(
            hasStock => {
              this.isAvailableStock[product.id] = hasStock
            })
        })

        const storedSortOption = localStorage.getItem('selectedSortOption')
        if (storedSortOption) {
          this.selectedSortOption = storedSortOption
          this.sortProducts()
        }
      })
  }


  //Redirige a la página de detalles del producto
  details(id: number) {
    localStorage.setItem('selectedSortOption', this.selectedSortOption)
    this.router.navigate(['product-details', id], {state: {isAvailableStock: this.isAvailableStock}})
  }


  //Devuelve si tiene stock o no
  hasStock(id: number): boolean {
    return this.isAvailableStock[id] || false
  }


  sortProducts() {
    switch (this.selectedSortOption) {
      case 'asc':
        this.products.sort((a, b) => a.price - b.price)
        break
      case 'desc':
        this.products.sort((a, b) => b.price - a.price)
        break
      default:
        break
    }
  }


  generateRandomProducts() {
    this.productService.getRandomProductsByCategory(this.id).subscribe(
      () => {
        this.getProductsListByCategoryId()
      },
      (error) => {
        console.error('Error al generar productos', error);
      }
    )
  }


  toggleLike(product: Product) {
    this.wishListService.isProductLiked(this.user.email, product.id).subscribe(
      isProductLiked => {
        product.isLiked = isProductLiked

        if (!isProductLiked) {
          this.wishListService.addProductToWishList({ user: this.user, product: product } as WishListRequest).subscribe(
            newProduct => {
              localStorage.setItem('selectedSortOption', this.selectedSortOption)
              window.location.reload()
            }
          )
        }
        else this.wishListService.deleteProductFromWishList(this.user.email, product.id).subscribe(
          newProduct => {
            localStorage.setItem('selectedSortOption', this.selectedSortOption)
            window.location.reload()
          }
        )
      })
  }


  isAdmin(): boolean {
    if (this.user.role == "ADMIN") return true
    return false
  }
}
