import { Routes } from '@angular/router'
import { ListCategoriesComponent } from './list-categories/list-categories.component'
import { SaveCategoryComponent } from './save-category/save-category.component'
import { NotFoundComponent } from './not-found/not-found.component'
import { UpdateCategoryComponent } from './update-category/update-category.component'
import { ListProductsComponent } from './list-products/list-products.component'
import { ProductDetailComponent } from './product-detail/product-detail.component'
import { CarritoDetailsComponent } from './carrito-details/carrito-details.component'
import { PayMethodComponent } from './pay-method/pay-method.component'
import { IndexComponent } from './index/index.component'
import { LoginComponent } from './login/login.component'
import { ProfileComponent } from './profile/profile.component'
import { RegisterComponent } from './register/register.component'
import { ProfileEditComponent } from './profile-edit/profile-edit.component'
import { WishesListComponent } from './wishes-list/wishes-list.component'


export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },  
    {
        path: 'home',
        component: IndexComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'all-categories',
        component: ListCategoriesComponent
    },
    {
        path: 'profile',
        component: ProfileComponent
    },
    {
        path: 'profileU',
        component: ProfileEditComponent
    },
    {
        path: 'wishes-list',
        component: WishesListComponent
    },
    {
        path: 'save-category',
        component: SaveCategoryComponent
    },
    {
        path: 'update-category/:id',
        component: UpdateCategoryComponent
    },
    {
        path: 'products-by-category/:id',
        component: ListProductsComponent
    },
    {
        path: 'product-details/:id',
        component: ProductDetailComponent
    },
    {
        path: 'carrito-details',
        component: CarritoDetailsComponent
    },
    {
        path: 'pay-method',
        component: PayMethodComponent
    },
    {
        path: 'not-found',
        component: NotFoundComponent
    },
    {
        path: '**',                            // ejemplo: http://localhost:4200/nijcwncpwcmwo 
        redirectTo: 'not-found'
    }
];


//EL ORDEN ES MUY IMPORTANTE

