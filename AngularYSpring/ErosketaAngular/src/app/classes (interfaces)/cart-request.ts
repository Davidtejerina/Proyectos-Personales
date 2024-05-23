import { Product } from "./product"
import { User } from "./user"

export class CartRequest {
  user: User
  product: Product
  amount: number
}
