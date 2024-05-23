import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { NavbarCollapseComponent } from '../navbar-collapse/navbar-collapse.component';


@Component({
  selector: 'app-menu-navbar-sin-loggear',
  standalone: true,
  imports: [RouterLink, CommonModule, LoginComponent, NavbarCollapseComponent],
  templateUrl: './menu-navbar.component.html',
  styleUrl: './menu-navbar.component.css'
})


export class MenuNavbarSinLoggearComponent {


}
