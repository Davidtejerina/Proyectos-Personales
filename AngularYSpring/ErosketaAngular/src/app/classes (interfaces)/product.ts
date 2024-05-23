import { Category } from "./category"


export class Product {
    id:number
    name:string
    description:string
    price:number
    stock:number
    image:string
    category: Category
    quantityToBuy: number
    isLiked: Boolean
}
