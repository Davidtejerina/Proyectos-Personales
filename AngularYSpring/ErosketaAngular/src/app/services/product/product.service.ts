import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable, map } from 'rxjs'
import { Product } from '../../classes (interfaces)/product'
import { environment } from '../../../environments/environment'


@Injectable({
  providedIn: 'root'
})


export class ProductService {

  constructor(private httpCliente : HttpClient) { }


  //Devuelve los productos de una determinada categoria segun su id
  getProductsByCategoryId(id : number): Observable<Product[]> {
    return this.httpCliente.get<Product[]>(environment.urlProduct+"/category/"+id)
  }

  getRandomProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.httpCliente.get<Product[]>(environment.urlProduct+"/generate-products/"+categoryId)
  }
 

  //Devuelve un producto segun su id
  getProductById(id : number): Observable<Product> {
    return this.httpCliente.get<Product>(environment.urlProduct+"/getBy/"+id)
  }

  
  //Devuelve si un determinado producto tiene o no stock
  hasStock(id: number): Observable<boolean> {
    return this.getProductById(id).pipe(
      map(product => product.stock >= 1)     
    );
  }
}
