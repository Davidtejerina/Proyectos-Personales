import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Order } from '../../classes (interfaces)/order'
import { environment } from '../../../environments/environment'
import { Observable } from 'rxjs'
import { OrderRequest } from '../../classes (interfaces)/order-request'


@Injectable({
  providedIn: 'root'
})


export class OrderService {

  constructor(private httpCliente: HttpClient) {}

  getOrderByUser(email: string): Observable<Order> {
    return this.httpCliente.get<Order>(environment.urlOrder + "/" + email)
  }

  addOrder(order: OrderRequest): Observable<Object> {
    return this.httpCliente.post(environment.urlOrder, order)
  }

  removeOrder(email: string): Observable<Object> {
    return this.httpCliente.delete(environment.urlOrder + "/clean/" + email)
  }
}
