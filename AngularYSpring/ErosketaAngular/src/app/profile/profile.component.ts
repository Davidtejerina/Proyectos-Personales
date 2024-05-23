import { Component, OnInit } from '@angular/core'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common'
import { UserService } from '../services/user/user.service'
import { User } from '../classes (interfaces)/user'
import { LoginService } from '../services/auth/login.service'
import { Router } from '@angular/router'


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [MenuNavBarComponent, FormsModule, CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})


export class ProfileComponent implements OnInit {
  user: User = new User()


  constructor(
    private userService: UserService,
    private loginService: LoginService,
    private router: Router
  ) {}


  ngOnInit(): void {
    this.userService.getUser().subscribe(userData => {
      this.user = userData
    })
  }

  logOut() {
    this.loginService.logout()
    this.router.navigate(['login'])
  }


  updateUser() {
    this.router.navigate(['profileU'])
  }
}
