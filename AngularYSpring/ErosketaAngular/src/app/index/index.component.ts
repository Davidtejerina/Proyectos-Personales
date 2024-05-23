import { Component } from '@angular/core'
import { Router } from '@angular/router'


@Component({
  selector: 'app-index',
  standalone: true,
  imports: [],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})


export class IndexComponent {
  showMenu: boolean = false


  constructor(private router: Router){}


  goIn(){
    this.router.navigate(['login'])
  }
}
