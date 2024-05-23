import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs'
import { LoginRequest } from './loginRequest'
import { environment } from '../../../environments/environment'
import { SignUpRequest } from './SignUpRequest'
import { UserService } from '../user/user.service'


@Injectable({
  providedIn: 'root'
})


export class LoginService {
  currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("")



  constructor(
    private httpClient: HttpClient,
    private userService: UserService
  ) {
    this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem("token") || "")
  }



  login(credentials: LoginRequest): Observable<any> {
    return this.httpClient.post<any>(environment.urlAuth + "/login", credentials).pipe(
      tap(userData => {
        sessionStorage.setItem("token", userData.token)
        
        this.currentUserData.next(userData.token)
        return userData
      }),
      map(userData => userData.token),
      catchError(this.handleError)
    )
  }

  signUp(credentials: SignUpRequest): Observable<any> {
    return this.httpClient.post<any>(environment.urlAuth + "/signup", credentials).pipe(
      tap(userData => {
        sessionStorage.setItem("token", userData.token)

        this.currentUserData.next(userData.token)
        return userData
      }),
      map(userData => userData.token),
      catchError(this.handleError)
    )
  }


  logout(): void {
    sessionStorage.removeItem("token")
  }


  private handleError(response: HttpErrorResponse) {
    console.error('Error en la solicitud:', response);
    return throwError(() => new Error('Datos de cuenta no existen o son erróneos'));
  }


  get userToken(): String {
    return this.currentUserData.value
  }
}
