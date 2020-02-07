import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'add', component: AddCustomerComponent },
  { path: 'update/:id', component: UpdateCustomerComponent },
  { path: 'details/:id', component: CustomerDetailsComponent },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
