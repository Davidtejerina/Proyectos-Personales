import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { environment } from '../../../environments/environment'
import { Observable } from 'rxjs'
import { WishList } from '../../classes (interfaces)/wish-list'
import { WishListRequest } from '../../classes (interfaces)/wish-list-request'


@Injectable({
  providedIn: 'root'
})


export class WishListService {

  constructor(private httpCliente: HttpClient) { }

  getProductsLiked(email: string): Observable<WishList[]> {
    return this.httpCliente.get<WishList[]>(environment.urlWish+"/"+email)
  }

  isProductLiked(email: string, id: number): Observable<Boolean> {
    return this.httpCliente.get<Boolean>(environment.urlWish+"/"+email+"/"+id)
  }

  addProductToWishList(wishList: WishListRequest): Observable<any> {
    return this.httpCliente.post<any>(environment.urlWish+"/add", wishList)
  }

  deleteProductFromWishList(email: string, id: number): Observable<any>{
    return this.httpCliente.delete<any>(environment.urlWish+"/clean/"+email+"/"+id)
  }

  deleteAll(email: string): Observable<any>{
    return this.httpCliente.delete<any>(environment.urlWish+"/cleanAll/"+email)
  }
}
