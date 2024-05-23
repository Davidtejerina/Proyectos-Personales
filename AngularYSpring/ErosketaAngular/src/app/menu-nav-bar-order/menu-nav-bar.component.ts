import { Component, OnInit } from '@angular/core'
import { Router, RouterLink } from '@angular/router'
import { CommonModule } from '@angular/common'
import { UserService } from '../services/user/user.service'
import { LoginService } from '../services/auth/login.service'
import { User } from '../classes (interfaces)/user'
import Swal from 'sweetalert2'


@Component({
  selector: 'app-menu-nav-bar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './menu-nav-bar.component.html',
  styleUrl: './menu-nav-bar.component.css'
})


export class MenuNavBarComponent implements OnInit {
  viewCart: boolean = false
  productsInCart: number
  user: User = new User()


  constructor(
    private userService: UserService,
    private loginService: LoginService,
    private router: Router) {
  }


  ngOnInit(): void {
    this.userService.getUser().subscribe(
      userData => {
        this.user = userData
      }
    )

    try {
      if(this.user==null) this.logOut()
    } catch (error) {
      this.logOut()
    }
  }


  isAdmin(): boolean {
    if (this.user.role == "ADMIN") return true
    return false
  }


  logOut() {
    this.loginService.logout()
    this.router.navigate(['login'])
  }
}
