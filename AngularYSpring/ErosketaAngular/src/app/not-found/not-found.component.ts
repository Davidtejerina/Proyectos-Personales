import { Component } from '@angular/core'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { RouterLink } from '@angular/router'


@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [MenuNavBarComponent, RouterLink],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.css'
})


export class NotFoundComponent {
}
