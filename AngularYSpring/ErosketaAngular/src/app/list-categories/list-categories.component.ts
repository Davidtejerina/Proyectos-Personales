import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import { CategoryService } from '../services/category/category.service'
import { Router } from '@angular/router'
import { Category } from '../classes (interfaces)/category'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import Swal from 'sweetalert2'
import { User } from '../classes (interfaces)/user'
import { UserService } from '../services/user/user.service'


@Component({
  selector: 'app-list-categories',
  standalone: true,
  imports: [CommonModule, MenuNavBarComponent],
  templateUrl: './list-categories.component.html',
  styleUrl: './list-categories.component.css'
})


export class ListCategoriesComponent implements OnInit {
  categories: Category[] = []
  user: User = new User()

  
  constructor(
    private categoryService: CategoryService, 
    private userService: UserService,
    private router: Router
    ) { }


  //Nada mas cargar la página mostraremos todas las categorías
  ngOnInit(): void {
    localStorage.removeItem('selectedSortOption');
    this.getCategoryList()
    this.userService.getUser().subscribe(userData => {
      this.user = userData
    })
  }


  //Devuelve todas las categorias
  private getCategoryList() {
    this.categoryService.getCategoryList().subscribe(
      categoryList => {
        this.categories = categoryList
      })
  }


  //Redirige a la página que muestra lista de productos de cada categoria
  listProducts(id: number) {
    this.router.navigate(['products-by-category', id])
  }


  //Redirige a la página con el formulario para actualizar una categoria determinada segun su id
  updateCategory(id: number) {
    this.router.navigate(['update-category', id])
  }


  //Borra una categoria determinada segun su id
  deleteCategory(id: number) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Confirma si deseas eliminar la categoría',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, elimínala',
      cancelButtonText: 'No, cancelar',
      customClass: {
        confirmButton: 'm-2 btn btn-success',
        cancelButton: 'm-2 btn btn-danger',
      },
      buttonsStyling: false,
    }).then(
      result => {
        if (result.isConfirmed) {
          this.categoryService.deleteCategory(id).subscribe(
            () => {
              this.getCategoryList()
              window.location.reload()
            },
            error => {
              console.error('Error al eliminar la categoría', error)

              Swal.fire(
                'Error',
                'Ha ocurrido un error al intentar eliminar la categoría',
                'error',
              )
            }
          )
        }
      }
    )
  }

  isAdmin(): boolean{
    if(this.user.role=="ADMIN") return true
    return false
  }
}