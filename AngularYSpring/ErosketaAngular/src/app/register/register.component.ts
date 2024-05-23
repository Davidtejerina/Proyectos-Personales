import { CommonModule } from '@angular/common'
import { Component } from '@angular/core'
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterLink } from '@angular/router'
import { SignUpRequest } from '../services/auth/SignUpRequest'
import { LoginService } from '../services/auth/login.service'


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})


export class RegisterComponent {
  signupError: string = ""
  signupForm = this.formBuilder.group({
    firstname: ['', [Validators.required]],
    lastname: ['', Validators.required],
    biography: ['', [Validators.required]],
    city: ['', Validators.required],
    postal_code: ['', [Validators.required]],
    country: ['', Validators.required],
    phone: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  })


  constructor(
    private formBuilder: FormBuilder, 
    private router: Router, 
    private loginService: LoginService
  ) { }


  register() {
    this.signupError = ""  

    console.log('Solicitud HTTP con token:', this.signupForm.value as SignUpRequest)

    this.loginService.signUp(this.signupForm.value as SignUpRequest).subscribe({
      next: userData => {
        console.log('Token JWT recibido:', userData)
      },
      error: errorData => {         
        this.signupError = errorData
      },
      complete: () => {
        this.router.navigate(['all-categories'])
        this.signupForm.reset()
      }
    })
  }


  get email() {
    return this.signupForm.controls.email;
  }

  get password() {
    return this.signupForm.controls.password;
  }
}
