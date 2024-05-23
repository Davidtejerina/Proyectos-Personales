//ESTE ARCHIVO SERIA EL EQUIVALENTE AL APP.MODULE.TS EN VERSIONES ANTERIORES DE ANGULAR

import { ApplicationConfig } from '@angular/core'
import { provideRouter } from '@angular/router'

import { importProvidersFrom } from '@angular/core'
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'

import { routes } from './app.routes'
import { JwtInterceptorService } from './services/auth/jwt-interceptor.service'
import { ErrorInterceptorService } from './services/auth/error-interceptor.service'


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),      //configuración de enrutamiento
    importProvidersFrom(HttpClientModule),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true },
  ]
}
