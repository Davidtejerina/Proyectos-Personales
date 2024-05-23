import { Component } from '@angular/core'
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterLink } from '@angular/router'
import { LoginRequest } from '../services/auth/loginRequest'
import { LoginService } from '../services/auth/login.service'
import { CommonModule } from '@angular/common'


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})


export class LoginComponent {
  loginError: string = ""
  loginForm = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  })


  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }


  login() {
    this.loginError = ""  

    this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
      next: userData => {
        console.log('Token aceptado')
      },
      error: errorData => {         
        this.loginError = errorData
      },
      complete: () => {
        this.router.navigate(['all-categories'])
        this.loginForm.reset()
      }
    })
  }


  get email() {
    return this.loginForm.controls.email;
  }

  get password() {
    return this.loginForm.controls.password;
  }
}
