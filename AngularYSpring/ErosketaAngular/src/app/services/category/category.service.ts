import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { Category } from '../../classes (interfaces)/category'
import { environment } from '../../../environments/environment'


@Injectable({
  providedIn: 'root'
})


export class CategoryService {

  
  constructor(private httpCliente : HttpClient) { }


  //Devuelve todas las categorias
  getCategoryList(): Observable<Category[]> {
    return this.httpCliente.get<Category[]>(environment.urlCategory+"/all")
  }


  //Devuelve una categoria segun su id
  getCategoryListById(id : number): Observable<Category> {
    return this.httpCliente.get<Category>(environment.urlCategory+"/getBy/"+id)
  }


  //Crea una categoria
  postCategory(category : Category): Observable<Object> {
    return this.httpCliente.post(environment.urlCategory+"/create", category)
  }


  //Actualizar una categoria segun su id
  putCategory(id : number, category : Category): Observable<Object> {
    return this.httpCliente.put(environment.urlCategory+"/update/"+id, category)
  }


  //Eliminar una categoria segun su id
  deleteCategory(id : number) : Observable<Object>{
    return this.httpCliente.delete(environment.urlCategory+"/delete/"+id)
  }
}
