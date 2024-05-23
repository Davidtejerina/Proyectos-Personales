import { Component, OnInit } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { CategoryService } from '../services/category/category.service'
import { ActivatedRoute, Router } from '@angular/router'
import Swal from 'sweetalert2'
import { CommonModule } from '@angular/common'
import { Category } from '../classes (interfaces)/category'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'


@Component({
  selector: 'app-update-category',
  standalone: true,
  imports: [CommonModule, FormsModule, MenuNavBarComponent],
  templateUrl: './update-category.component.html',
  styleUrl: './update-category.component.css'
})


export class UpdateCategoryComponent implements OnInit {
  id: number
  category: Category = new Category()


  constructor(private categoryService: CategoryService, private router: Router, private path: ActivatedRoute) { }


  //Nada mas iniciar la página se recoge el id de la ruta y según ese id se coge una categoría u otra
  ngOnInit(): void {
    this.id = this.path.snapshot.params['id']

    this.categoryService.getCategoryListById(this.id).subscribe(
      categoryBD => {
        this.category = categoryBD
      }, 
      error => {
        console.log(error)

        Swal.fire({
          icon: 'error',
          title: 'La categoria con ID: "' + this.id + '" no existe',
          showConfirmButton: true
        });

        this.router.navigate(['all-categories'])
      })
  }


  //Cuando se envia el form se actualiza la categoria
  updateCategory(): void {
    this.categoryService.putCategory(this.id, this.category).subscribe(dato => {
      this.router.navigate(['all-categories'])

      Swal.fire({
        icon: 'success',
        title: 'La categoria "' + this.category.name + '" ha sido actualizada con éxito',
        showConfirmButton: true
      });
    }, error => console.log(error))
  }
}
