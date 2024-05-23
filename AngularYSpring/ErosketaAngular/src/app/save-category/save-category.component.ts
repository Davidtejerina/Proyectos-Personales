import { Component } from '@angular/core'
import { Category } from '../classes (interfaces)/category'
import { FormsModule } from '@angular/forms'
import { CategoryService } from '../services/category/category.service'
import { Router } from '@angular/router'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'


@Component({
  selector: 'app-save-category',
  standalone: true,
  imports: [FormsModule, MenuNavBarComponent],
  templateUrl: './save-category.component.html',
  styleUrl: './save-category.component.css'
})


export class SaveCategoryComponent {
  category: Category = new Category()


  constructor(private categoryService: CategoryService, private router: Router) { }


  //Guarda los datos de la nueva categoria
  saveCategory() {
    this.categoryService.postCategory(this.category).subscribe(
      dato => {
        console.log(dato)
        this.router.navigate(['all-categories'])
      },
      error => console.log(error))
  }
}
