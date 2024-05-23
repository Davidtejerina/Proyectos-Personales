import { Component } from '@angular/core'
import { User } from '../classes (interfaces)/user'
import { Router } from '@angular/router'
import { UserService } from '../services/user/user.service'
import { UserRequest } from '../classes (interfaces)/user-request'
import { MenuNavBarComponent } from '../menu-nav-bar/menu-nav-bar.component'
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import Swal from 'sweetalert2'


@Component({
  selector: 'app-profile-edit',
  standalone: true,
  imports: [MenuNavBarComponent, ReactiveFormsModule],
  templateUrl: './profile-edit.component.html',
  styleUrl: './profile-edit.component.css'
})


export class ProfileEditComponent {
  user: User = new User()
  userForm: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.userForm = this.formBuilder.group({
      postal_code: [''],
      city: [''],
      country: [''],
      phone: [''],
    });
  }


  ngOnInit(): void {
    this.userService.getUser().subscribe(userData => {
      this.user = userData

      this.userForm.patchValue({
        postal_code: this.user.postal_code,
        city: this.user.city,
        country: this.user.country,
        phone: this.user.phone
      });
    })
  }


  updateUser() {
    if (Object.values(this.userForm.value).some(value => (String(value).trim()) === "")) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Por favor, rellene todos los campos.',
        showConfirmButton: true
      })
      return
    }

    if (String(this.userForm.value.phone).trim() === "" || String(this.userForm.value.phone).length != 9) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Por favor, introduzca un número de teléfono válido.',
        showConfirmButton: true
      })
      return
    }

    const userRequest: UserRequest = {
      firstname: this.user.firstname,
      lastname: this.user.lastname,
      biography: this.user.biography,
      email: this.user.email,
      role: this.user.role,
      postal_code: this.userForm.value.postal_code || this.user.postal_code,
      city: this.userForm.value.city || this.user.city,
      country: this.userForm.value.country || this.user.country,
      phone: this.userForm.value.phone || this.user.phone
    };

    this.userService.updateUser(userRequest).subscribe(userData => {
      this.user = userData
    })

    this.router.navigate(['profile']).then(() => {
      window.location.reload()
    })
  }
}
